package edu.cit.cantiller.christianjayson.campusequipmentloan.controller;

import edu.cit.cantiller.christianjayson.campusequipmentloan.dto.LoginRequest;
import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Student;
import edu.cit.cantiller.christianjayson.campusequipmentloan.repository.StudentRepository;
import edu.cit.cantiller.christianjayson.campusequipmentloan.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    // ✅ Register student
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean success = studentService.login(request.getEmail(), request.getPassword());

        if (success) {
            return ResponseEntity.ok().body("{\"message\":\"You are now logged in\"}");
        } else {
            return ResponseEntity.ok().body("{\"message\":\"Invalid email or password\"}");
        }
    }
}
