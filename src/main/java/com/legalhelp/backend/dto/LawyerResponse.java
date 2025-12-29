package com.legalhelp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawyerResponse {
    public Long id;
    private String name;
    private String Email;
    private String experience;
    private String court;
}
