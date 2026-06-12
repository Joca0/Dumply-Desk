package com.dumplydesk.shared.security.filter;

import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

@Component
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;
    private final EntityManagerFactory entityManagerFactory;

    public CustomUserDetails(UserRepository userRepository, EntityManagerFactory entityManagerFactory) {
        this.userRepository = userRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Session session = entityManagerFactory.unwrap(Session.class);

        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                new ArrayList<>());
    }
}
