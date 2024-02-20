package com.wizy.educationapp.security.config;

import com.wizy.educationapp.security.jwt.JwtFilter;
import com.wizy.educationapp.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Autowired
  @SneakyThrows
  void registerProvider(AuthenticationManagerBuilder auth) {
    auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
  }

  @Bean
  @SneakyThrows
  public AuthenticationManager authenticationManager(
          final AuthenticationConfiguration authenticationConfiguration
  ) {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  @SneakyThrows
  public SecurityFilterChain configure(final HttpSecurity http) {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(configurer ->
                    configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests((request) -> {
              request.requestMatchers(HttpMethod.POST, "/v1/auth/login", "/v1/auth/refresh")
                      .permitAll();
              request.requestMatchers(HttpMethod.GET, "/v1/auth/verification")
                      .permitAll();
              request.requestMatchers(HttpMethod.POST, "/v1/auth/register")
                      .permitAll();
              request.anyRequest().authenticated();
            })
            .exceptionHandling(configurer ->
                    configurer.authenticationEntryPoint(
                                    (request, response, exception) -> {
                                      response.setStatus(
                                              HttpStatus.UNAUTHORIZED
                                                      .value()
                                      );
                                      response.getWriter()
                                              .write("Unauthorized");
                                    })
                            .accessDeniedHandler(
                                    (request, response, exception) -> {
                                      response.setStatus(
                                              HttpStatus.FORBIDDEN
                                                      .value()
                                      );
                                      response.getWriter()
                                              .write("Forbidden");
                                    }))
            .addFilterBefore(
                    new JwtFilter(
                            jwtService,
                            userDetailsService
                    ), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
