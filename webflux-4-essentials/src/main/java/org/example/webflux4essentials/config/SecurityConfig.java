package org.example.webflux4essentials.config;


import org.example.webflux4essentials.service.ProjectUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf().disable()
                .authorizeExchange()
                    .pathMatchers(HttpMethod.POST, "/animes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.PUT, "/animes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.DELETE, "/animes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.GET, "/animes/**").hasRole("USER")
                    .pathMatchers("/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**")
                    .permitAll()
                .anyExchange().authenticated()
                .and()
                    .formLogin()
                .and()
                    .httpBasic()
                .and()
                    .build();

    }

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails user = User.withUsername("rodrigo2")
//                .password(passwordEncoder.encode("senha123"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("senha123"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new MapReactiveUserDetailsService(user, admin);
//    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(ProjectUserDetailsService projectUserDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(projectUserDetailsService);
    }
}
