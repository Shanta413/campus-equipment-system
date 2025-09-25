package edu.cit.cantiller.christianjayson.campusequipmentloan.repository;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email); // ✅ login with email
}
