package com.legalhelp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private CADetails caDetails;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private LawyerDetails lawyerDetails;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ConsultantDetails consultantDetails;

//    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "clint",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Assignments> clintAssignments;

    @OneToMany(mappedBy = "professional",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Assignments> professionalAssignments;




}
