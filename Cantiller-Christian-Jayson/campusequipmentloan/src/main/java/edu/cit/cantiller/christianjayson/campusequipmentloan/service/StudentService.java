package edu.cit.cantiller.christianjayson.campusequipmentloan.service;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Student;
import edu.cit.cantiller.christianjayson.campusequipmentloan.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public boolean login(String email, String password) {
        return studentRepository.findByEmail(email)
                .filter(student -> student.getPassword().equals(password)) //
                .isPresent();
    }
}
