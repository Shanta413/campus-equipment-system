package edu.cit.cantiller.christianjayson.campusequipmentloan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")   // 👈 change column name
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @Column(unique = true)
    private String serialNumber;

    private boolean available = true;  // default true
}