package edu.cit.cantiller.christianjayson.campusequipmentloan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String studentNo;   // ✅ keep student number

    @NotBlank
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;       // ✅ used for login

    @NotBlank
    private String password;

}
