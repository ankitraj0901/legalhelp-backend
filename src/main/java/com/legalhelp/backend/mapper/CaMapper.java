package com.legalhelp.backend.mapper;

import com.legalhelp.backend.dto.CaResponseDTO;
import com.legalhelp.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class CaMapper {
//    public CaResponseDTO toDTO(User user) {
//        if (user == null) return null;
//        return new CaResponseDTO(
//                user.getUserId(),
//                user.getName(),
//                user.getEmail(),
//                user.getCaDetails() != null ? user.getCaDetails().getExperience() : null,
//                user.getCaDetails() != null ? user.getCaDetails().getSpecialization() : null
//        );
//    }
}
