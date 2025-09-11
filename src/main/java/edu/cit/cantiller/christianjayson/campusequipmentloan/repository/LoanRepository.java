package edu.cit.cantiller.christianjayson.campusequipmentloan.repository;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;
import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStudentAndStatus(Student student, Loan.Status status);
}