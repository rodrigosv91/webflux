package org.example.webflux4essentials.repository;

import org.example.webflux4essentials.domain.ProjectUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProjectUserRepository extends ReactiveCrudRepository<ProjectUser, Integer> {

    Mono<ProjectUser> findByUsername(String username);
}