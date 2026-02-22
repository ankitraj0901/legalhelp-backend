package com.legalhelp.backend.dto;

import com.legalhelp.backend.entity.AssignmentStatus;
import lombok.Data;

@Data
public class UpdateAssignmentStatusRequest {
    private Long assignmentId;
    private AssignmentStatus status;
}
