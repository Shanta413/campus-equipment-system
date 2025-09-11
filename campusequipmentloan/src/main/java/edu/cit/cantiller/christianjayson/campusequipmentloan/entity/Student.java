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
    @Column(name = "student_id")   // 👈 change column name
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String studentNo;

    @NotBlank
    private String name;

    @Email
    private String email;
}