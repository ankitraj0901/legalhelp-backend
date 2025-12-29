package com.legalhelp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignment")
public class Assignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long assignmentId;
    //title of the case
    private String title;

    //detail of the case
    private String description;
    // For understanding the category of the problem like Tax filing, Gst Registration, Tax Audit,Property Case,Divorce Consultation,Legal Notice
    @Enumerated(value = EnumType.STRING)
    private ServiceType serviceType;


    @Enumerated(value = EnumType.STRING)
    private AssignmentStatus assignmentStatus;

    private LocalDateTime updatedAt;
    private LocalDate dueDate;
    private final LocalDate assignedDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "clint_id")
    private User clint;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private User professional;

    @PreUpdate
    public void setLastUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
