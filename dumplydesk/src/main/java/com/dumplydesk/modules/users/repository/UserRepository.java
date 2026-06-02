package com.dumplydesk.modules.users.repository;

import com.dumplydesk.modules.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    boolean existsByEmail(String email);

    Optional<User> findById(UUID id);w
}
