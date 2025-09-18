package edu.cit.cantiller.christianjayson.campusequipmentloan.controller;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.LoanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public Loan createLoan(@RequestBody CreateLoanRequest request) {
        LocalDate startDate = request.getStartDate() != null ? request.getStartDate() : LocalDate.now();
        LocalDate dueDate = startDate.plusDays(7); // 🔹 Always 7-day loan

        return loanService.createLoan(
                request.getStudentId(),
                request.getEquipmentId(),
                startDate,
                dueDate
        );
    }

    @PostMapping("/{loanId}/return")
    public Loan returnLoan(@PathVariable Long loanId, @RequestBody ReturnLoanRequest request) {
        return loanService.returnLoan(loanId, request.getReturnDate());
    }

    // DTO for creating loan
    @Getter
    @Setter
    public static class CreateLoanRequest {
        private Long studentId;
        private Long equipmentId;
        private LocalDate startDate; // 🔹 renamed from borrowDate → startDate
    }

    // DTO for returning loan
    @Getter
    @Setter
    public static class ReturnLoanRequest {
        private LocalDate returnDate;
    }
}
