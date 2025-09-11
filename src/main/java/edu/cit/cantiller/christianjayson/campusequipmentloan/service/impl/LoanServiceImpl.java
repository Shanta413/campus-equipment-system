package edu.cit.cantiller.christianjayson.campusequipmentloan.service.impl;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.*;
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
    public Loan createLoan(Long studentId, Long equipmentId, LocalDate dueDate) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        // Rule 1: Max 2 active loans
        List<Loan> activeLoans = loanRepository.findByStudentAndStatus(student, Loan.Status.ACTIVE);
        if (activeLoans.size() >= 2) {
            throw new RuntimeException("Student already has 2 active loans");
        }

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment not available");
        }

        equipment.setAvailable(false);
        equipmentRepository.save(equipment);

        Loan loan = Loan.builder()
                .student(student)
                .equipment(equipment)
                .startDate(LocalDate.now())
                .dueDate(dueDate) // flexible from request
                .status(Loan.Status.ACTIVE)
                .build();

        return loanRepository.save(loan);
    }

    @Override
    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            loan.setStatus(Loan.Status.OVERDUE);
            int penalty = penaltyStrategy.calculatePenalty(loan);
            loan.setPenalty(penalty);  // 👈 store penalty
            System.out.println("Penalty: ₱" + penalty);
        } else {
            loan.setStatus(Loan.Status.RETURNED);
            loan.setPenalty(0);        // 👈 no penalty
        }

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }
}