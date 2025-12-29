package com.legalhelp.backend.dto;

import com.legalhelp.backend.entity.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDetailsDTO {
    private String title;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private ServiceType serviceType;
    private LocalDate dueDate;
}
