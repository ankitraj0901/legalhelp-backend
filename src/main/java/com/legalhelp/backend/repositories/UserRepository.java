package com.legalhelp.backend.repositories;


import com.legalhelp.backend.entity.Role;
import com.legalhelp.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail (String email);
    List<User> findByRole(Role role);
}
