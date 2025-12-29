package com.legalhelp.backend.service;

import com.legalhelp.backend.dto.CaResponseDTO;
import com.legalhelp.backend.dto.ConsultantResponseDTO;
import com.legalhelp.backend.dto.LawyerResponseDTO;
import com.legalhelp.backend.dto.RegistrationRequest;
import com.legalhelp.backend.entity.*;
import com.legalhelp.backend.mapper.CaMapper;
import com.legalhelp.backend.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CADetailsRepository caDetailsRepository;
    @Autowired
    private LawyerDetailsRepository lawyerDetailsRepository;
    @Autowired
    private ConsultantDetailsRepository consultantDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CaMapper caMapper;

    public void registerUser(RegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());




        if(request.getRole() == Role.CA && request.getCaDetailsDTO() != null) {
            CADetails ca = new CADetails();
            ca.setRegistrationNumber(request.getCaDetailsDTO().getRegistrationNumber());
            ca.setExperience(request.getCaDetailsDTO().getExperience());
            ca.setSpecialization(request.getCaDetailsDTO().getSpecialization());
            ca.setUser(user);
            user.setCaDetails(ca);
//            caDetailsRepository.save(ca);
        }

        if(request.getRole() == Role.LAWYER && request.getLawyerDetailsDTO() != null) {
            LawyerDetails lawyer = new LawyerDetails();
            lawyer.setLicenseNumber(request.getLawyerDetailsDTO().getLicenseNumber());
            lawyer.setExperience(request.getLawyerDetailsDTO().getExperience());
            lawyer.setCourt(request.getLawyerDetailsDTO().getCourt());
            lawyer.setUser(user);
            user.setLawyerDetails(lawyer);
//            lawyerDetailsRepository.save(lawyer);
        }

        if(request.getRole() == Role.CONSULTANT && request.getConsultantDetailsDTO() != null) {
            ConsultantDetails consultant = new ConsultantDetails();
            consultant.setExperience(request.getConsultantDetailsDTO().getExperience());
            consultant.setField(request.getConsultantDetailsDTO().getField());
            consultant.setFirmName(request.getConsultantDetailsDTO().getField());
            consultant.setUser(user);
            user.setConsultantDetails(consultant);
//            consultantRepository.save(consultant);
        }
        userRepository.save(user);
    }

    // getting ca from the data base
    public List<CaResponseDTO> getAllCAs(Long clientId){
        return caDetailsRepository.findAll().stream().map(ca -> {
            CaResponseDTO response = new CaResponseDTO();
            response.setCaId(ca.getCaId());
            response.setUserId(ca.getUser().getUserId());
            response.setName(ca.getUser().getName());
            response.setEmail(ca.getUser().getEmail());
            response.setExperience(ca.getExperience());
            response.setSpecialization(ca.getSpecialization());

            boolean exist = assignmentRepository
                    .findByClint_UserIdAndProfessional_UserId(clientId,ca.getUser().getUserId())
                    .isPresent();

            response.setAssigned(exist);

            return response;
        }).collect(Collectors.toList());
    }
    

    //Fetching all the lawyer and sending necessary property for Lawyer to frontend
    public List<LawyerResponseDTO> getAllLawyers(Long clientId){
        return lawyerDetailsRepository.findAll().stream().map(lawyer -> {
            LawyerResponseDTO response = new LawyerResponseDTO();
            response.setLawyerId(lawyer.getId());
            response.setUserId(lawyer.getUser().getUserId());
            response.setName(lawyer.getUser().getName());
            response.setEmail(lawyer.getUser().getEmail());
            response.setExperience(lawyer.getExperience());
            response.setCourt(lawyer.getCourt());

            //check if client is already to this lawyer
            boolean exist = assignmentRepository
                    .findByClint_UserIdAndProfessional_UserId(clientId,lawyer.getUser().getUserId())
                    .isPresent();

            response.setAssigned(exist);

            return response;
        }).collect(Collectors.toList());
    }


    //Fetching all the lawyer and sending necessary property for Consultant to frontend
    public List<ConsultantResponseDTO> getAllConsultant(Long clientId) {
        return consultantDetailsRepository.findAll().stream().map(consultant -> {
            ConsultantResponseDTO response = new ConsultantResponseDTO();
            response.setConsultantId(consultant.getId());
            response.setUserId(consultant.getUser().getUserId());
            response.setName(consultant.getUser().getName());
            response.setEmail(consultant.getUser().getEmail());
            response.setExperience(consultant.getExperience());
            response.setField(consultant.getField());

            //checking if consultant is already to this user
            boolean exist = assignmentRepository
                    .findByClint_UserIdAndProfessional_UserId(clientId,consultant.getUser().getUserId())
                    .isPresent();

            response.setAssigned(exist);

            return response;
        }).collect(Collectors.toList());
    }
}
