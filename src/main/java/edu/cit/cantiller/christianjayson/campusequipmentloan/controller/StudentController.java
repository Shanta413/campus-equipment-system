package edu.cit.cantiller.christianjayson.campusequipmentloan.controller;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Student;
import edu.cit.cantiller.christianjayson.campusequipmentloan.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    // Create student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // List all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}