package edu.cit.cantiller.christianjayson.campusequipmentloan.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateLoanRequest {
    private Long studentId;
    private Long equipmentId;
    private LocalDate dueDate; // optional, can be null
}
