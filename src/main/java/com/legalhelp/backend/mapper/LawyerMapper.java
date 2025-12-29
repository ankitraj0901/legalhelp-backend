package com.legalhelp.backend.mapper;

import com.legalhelp.backend.dto.LawyerResponse;
import com.legalhelp.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LawyerMapper {
    public LawyerResponse toDTO(User user){
        if(user == null) return null;
        return new LawyerResponse(
          user.getUserId(),
          user.getName(),
          user.getEmail(),
          user.getLawyerDetails() != null ? user.getLawyerDetails().getExperience(): null,
                user.getLawyerDetails() != null ? user.getLawyerDetails().getCourt():null
        );
    }
}
