package edu.cit.cantiller.christianjayson.campusequipmentloan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 👈 only include non-null fields
public class LoginResponse {
    private String message;
    private String studentNo;
    private String name;

    // Constructor for failure
    public LoginResponse(String message) {
        this.message = message;
    }
}
