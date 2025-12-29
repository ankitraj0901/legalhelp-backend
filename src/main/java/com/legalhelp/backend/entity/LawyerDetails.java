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
@Table(name = "lawyer_details")
public class LawyerDetails {

    @Id
//    @Column(name = "lawyerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNumber;
    private String experience;
    private String court;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
