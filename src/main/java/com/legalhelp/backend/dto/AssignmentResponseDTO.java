package com.legalhelp.backend.dto;

import com.legalhelp.backend.entity.Assignments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponseDTO {
    private Assignments assignments;
    private boolean existing;
}
