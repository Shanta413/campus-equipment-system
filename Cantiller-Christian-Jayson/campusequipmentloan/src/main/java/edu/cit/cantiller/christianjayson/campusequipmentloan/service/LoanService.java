package edu.cit.cantiller.christianjayson.campusequipmentloan.service;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;
import java.time.LocalDate;   // 👈 add this

public interface LoanService {
    Loan createLoan(Long studentId, Long equipmentId, LocalDate borrowDate, LocalDate dueDate);
    Loan returnLoan(Long loanId, LocalDate returnDate);

}
