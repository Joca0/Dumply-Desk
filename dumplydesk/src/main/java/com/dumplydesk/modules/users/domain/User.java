package com.dumplydesk.modules.users.domain;

import com.dumplydesk.shared.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String email;
    private String document;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User() {

    }
}
