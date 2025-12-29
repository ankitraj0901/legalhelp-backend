package com.legalhelp.backend.dto;




public class LawyerDetailsDTO {
    private String licenseNumber;
    private String experience;
    private String court;


    public LawyerDetailsDTO() {
    }

    public LawyerDetailsDTO(String licenseNumber, String experience, String court) {
        this.licenseNumber = licenseNumber;
        this.experience = experience;
        this.court = court;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }
}
