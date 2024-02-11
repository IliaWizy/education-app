package com.wizy.educationapp.security.jwt;

import com.wizy.educationapp.security.jwt.entity.JwtAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  @SneakyThrows
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain) {

    String token = extractJwtFromRequest(request);

    if (token != null) {
      if (jwtService.validateToken(token)) {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtService.extractUsername(token));

        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String extractJwtFromRequest(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      return null;
    }

    return authorizationHeader.substring(7);
  }
}
