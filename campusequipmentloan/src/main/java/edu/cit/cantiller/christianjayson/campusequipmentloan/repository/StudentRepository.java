package edu.cit.cantiller.christianjayson.campusequipmentloan.repository;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}