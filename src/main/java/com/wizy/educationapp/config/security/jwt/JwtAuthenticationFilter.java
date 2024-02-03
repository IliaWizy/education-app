package com.wizy.educationapp.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserAuthProvider userAuthProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (header != null) {
      String[] elements = header.split(" ");

      if (elements.length == 2 && "Bearer".equals(elements[0])) {
        try {
          SecurityContextHolder.getContext().setAuthentication(
              userAuthProvider.validateToken(elements[1])
          );
        } catch (RuntimeException ex) {
          SecurityContextHolder.clearContext();
          log.error(ex.getMessage());
          throw ex;
        }
      }
    }

    filterChain.doFilter(request, response);
  }
}
