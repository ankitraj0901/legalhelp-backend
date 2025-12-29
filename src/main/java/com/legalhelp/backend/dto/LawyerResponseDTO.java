package com.legalhelp.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawyerResponseDTO {
    private Long lawyerId;
    private Long userId;
    private String name;
    private String email;
    private String experience;
    private String court;
    private boolean assigned;
}
