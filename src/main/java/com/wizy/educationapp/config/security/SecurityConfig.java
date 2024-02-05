package com.wizy.educationapp.config.security;

import com.wizy.educationapp.config.security.jwt.JwtAuthenticationFilter;
import com.wizy.educationapp.config.security.jwt.UserAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserAuthProvider userAuthProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(AbstractHttpConfigurer::disable);

    http.csrf(AbstractHttpConfigurer::disable);

    http.addFilterBefore(new JwtAuthenticationFilter(userAuthProvider),
        BasicAuthenticationFilter.class);

    http.sessionManagement(session -> {
      session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    });

    http.authorizeHttpRequests((request) -> {
      request.requestMatchers(HttpMethod.POST,
              "/v1/auth/login",
              "/v1/auth/refresh",
              "/v1/auth/register")
          .permitAll();

      request.requestMatchers(HttpMethod.GET,
              "/v1/auth/verification")
          .permitAll();

      request.requestMatchers("/v1/admin/**").hasRole("ADMIN");
      request.requestMatchers("/v1/tutor/**").hasRole("TUTOR");
      request.requestMatchers("/v1/user/**").hasRole("USER");

      request.anyRequest().authenticated();
    });

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
