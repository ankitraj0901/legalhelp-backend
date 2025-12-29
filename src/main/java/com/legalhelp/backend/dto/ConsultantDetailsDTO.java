package com.legalhelp.backend.dto;

public class ConsultantDetailsDTO {
    private String field;
    private String firmName;
    private String experience;

    public ConsultantDetailsDTO() {
    }

    public ConsultantDetailsDTO(String field, String firmName, String experience) {
        this.field = field;
        this.firmName = firmName;
        this.experience = experience;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
