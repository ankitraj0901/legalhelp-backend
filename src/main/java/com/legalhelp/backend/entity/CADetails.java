package com.legalhelp.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ca_details")
public class CADetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caId;

    private String registrationNumber;
    private String experience;
    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
