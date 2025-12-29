package com.legalhelp.backend.dto;
import com.legalhelp.backend.entity.Role;



public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private Role role;

    private CADetailsDTO caDetailsDTO;

    private LawyerDetailsDTO lawyerDetailsDTO;

    private ConsultantDetailsDTO consultantDetailsDTO;


    public RegistrationRequest() {

    }

    public RegistrationRequest(String name, String email, String password, Role role, CADetailsDTO caDetailsDTO, LawyerDetailsDTO lawyerDetailsDTO, ConsultantDetailsDTO consultantDetailsDTO) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.caDetailsDTO = caDetailsDTO;
        this.lawyerDetailsDTO = lawyerDetailsDTO;
        this.consultantDetailsDTO = consultantDetailsDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CADetailsDTO getCaDetailsDTO() {
        return caDetailsDTO;
    }

    public void setCaDetailsDTO(CADetailsDTO caDetailsDTO) {
        this.caDetailsDTO = caDetailsDTO;
    }

    public LawyerDetailsDTO getLawyerDetailsDTO() {
        return lawyerDetailsDTO;
    }

    public void setLawyerDetailsDTO(LawyerDetailsDTO lawyerDetailsDTO) {
        this.lawyerDetailsDTO = lawyerDetailsDTO;
    }

    public ConsultantDetailsDTO getConsultantDetailsDTO() {
        return consultantDetailsDTO;
    }

    public void setConsultantDetailsDTO(ConsultantDetailsDTO consultantDetailsDTO) {
        this.consultantDetailsDTO = consultantDetailsDTO;
    }
}
