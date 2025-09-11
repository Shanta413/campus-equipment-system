package edu.cit.cantiller.christianjayson.campusequipmentloan.controller;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.LoanService;
import edu.cit.cantiller.christianjayson.campusequipmentloan.dto.CreateLoanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public Loan createLoan(@RequestBody CreateLoanRequest request) {
        return loanService.createLoan(request.getStudentId(), request.getEquipmentId(), request.getDueDate());
    }

    @PostMapping("/{loanId}/return")
    public Loan returnLoan(@PathVariable Long loanId) {
        return loanService.returnLoan(loanId);
    }
}
