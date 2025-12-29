package com.legalhelp.backend.dto;


import com.legalhelp.backend.entity.AssignmentStatus;
import com.legalhelp.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedResponseDTO {
    private Long assignmentId;
    private Long clientId;
    private Long professionalId;
    private String name;
    private String email;
    private Role role;
    private String title;
    private String serviceType;
    private AssignmentStatus assignmentStatus;
    private String description;
    private LocalDate dueDate;
    private LocalDateTime updatedAt;
    private LocalDate assignmentDate;
}
