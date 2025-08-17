package com.shelfwise.backend.modules.auth.repository;

import com.shelfwise.backend.modules.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserEmailAndPassword(String userEmail, String password);
}
