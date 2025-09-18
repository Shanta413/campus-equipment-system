package edu.cit.cantiller.christianjayson.campusequipmentloan.service.impl;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.*;
import edu.cit.cantiller.christianjayson.campusequipmentloan.exception.LoanLimitExceededException;
import edu.cit.cantiller.christianjayson.campusequipmentloan.repository.*;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.LoanService;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.penalty.PenaltyStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final EquipmentRepository equipmentRepository;
    private final StudentRepository studentRepository;
    private final PenaltyStrategy penaltyStrategy;

    @Override
    public Loan createLoan(Long studentId, Long equipmentId, LocalDate borrowDate, LocalDate dueDate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        // Rule 1: Max 2 active loans
        List<Loan> activeLoans = loanRepository.findByStudentAndStatus(student, Loan.Status.ACTIVE);
        if (activeLoans.size() >= 2) {
            throw new LoanLimitExceededException("Student already has 2 active loans");
        }

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment not available");
        }

        equipment.setAvailable(false);
        equipmentRepository.save(equipment);

        Loan loan = Loan.builder()
                .student(student)
                .equipment(equipment)
                .startDate(borrowDate != null ? borrowDate : LocalDate.now())
                .dueDate(dueDate != null ? dueDate : (borrowDate != null ? borrowDate.plusDays(7) : LocalDate.now().plusDays(7))) // 👈 default 7 days
                .status(Loan.Status.ACTIVE)
                .penalty(0.0) // 👈 always initialize with 0
                .build();

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnLoan(Long loanId, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        LocalDate actualReturnDate = returnDate != null ? returnDate : LocalDate.now();
        loan.setReturnDate(actualReturnDate);

        if (actualReturnDate.isAfter(loan.getDueDate())) {
            loan.setStatus(Loan.Status.OVERDUE);
            long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(
                    loan.getDueDate(), actualReturnDate
            );
            double penaltyAmount = penaltyStrategy.calculatePenalty(overdueDays);
            loan.setPenalty(penaltyAmount);
        } else {
            loan.setStatus(Loan.Status.RETURNED);
            loan.setPenalty(0.0); // 👈 always set penalty to 0 if not overdue
        }

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }
}
