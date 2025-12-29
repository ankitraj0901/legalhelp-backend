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
@Table(name = "consultant_details")
public class ConsultantDetails {
    @Id
//    @Column(name = "consultantId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String field;
    private String firmName;
    private String experience;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
