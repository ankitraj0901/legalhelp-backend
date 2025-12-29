package com.legalhelp.backend.repositories;

import com.legalhelp.backend.entity.Assignments;
import com.legalhelp.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignments,Long> {
    List<Assignments> findByClint_UserId(Long clientId);
    List<Assignments> findByProfessional_UserId(Long professionalId);
    List<Assignments> findByClint(User clint);
    List<Assignments> findByProfessional(User professional);
    Optional<Assignments> findByClint_UserIdAndProfessional_UserId(Long clientId, Long professionalId);
    long countByProfessional_UserId(Long professionalId);

}
