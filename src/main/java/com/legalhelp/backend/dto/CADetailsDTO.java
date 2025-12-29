package com.legalhelp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CADetailsDTO {
    private String registrationNumber;
    private String experience;
    private String specialization;

}
