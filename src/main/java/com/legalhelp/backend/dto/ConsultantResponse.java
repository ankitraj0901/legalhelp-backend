package com.legalhelp.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultantResponse {
    private Long id;
    private String name;
    private String Email;
    private String field;
    private String experience;
}
