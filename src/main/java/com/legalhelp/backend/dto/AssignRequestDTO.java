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
public class AssignRequestDTO {
    private Long clientId;
    private Long professionalId;

}
