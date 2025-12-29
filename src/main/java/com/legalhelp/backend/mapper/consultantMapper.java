package com.legalhelp.backend.mapper;

import com.legalhelp.backend.dto.ConsultantResponse;
import com.legalhelp.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class consultantMapper {
    public ConsultantResponse toDTO(User user) {
        return new ConsultantResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getConsultantDetails() != null ? user.getConsultantDetails().getField(): null,
                user.getConsultantDetails() != null ? user.getConsultantDetails().getExperience(): null
        );
    }
}
