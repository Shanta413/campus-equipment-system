package edu.cit.cantiller.christianjayson.campusequipmentloan.controller;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.LoanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public Object createLoan(@RequestBody CreateLoanRequest request) {
        LocalDate startDate = request.getStartDate() != null ? request.getStartDate() : LocalDate.now();
        LocalDate dueDate = startDate.plusDays(7); // Always 7-day loan

        Loan loan = loanService.createLoan(
                request.getStudentId(),
                request.getEquipmentId(),
                startDate,
                dueDate
        );

        if (loan == null) {
            // Instead of throwing error → return message with 200 OK
            Map<String, String> response = new HashMap<>();
            response.put("message", "Student already has 2 active loans");
            return response;
        }

        return loan;
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
        private LocalDate startDate;  // ✅ changed from borrowDate to startDate
    }

    // DTO for returning loan
    @Getter
    @Setter
    public static class ReturnLoanRequest {
        private LocalDate returnDate;
    }
}
