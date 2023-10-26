package org.example.webflux4essentials.service;

import lombok.RequiredArgsConstructor;
import org.example.webflux4essentials.repository.ProjectUserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ProjectUserDetailsService implements ReactiveUserDetailsService {

    private final ProjectUserRepository projectUserRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return projectUserRepository.findByUsername(username)
                .cast(UserDetails.class);
    }
}
