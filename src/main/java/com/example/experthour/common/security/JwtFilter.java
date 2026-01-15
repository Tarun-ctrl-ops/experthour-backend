package com.example.experthour.common.security;

import com.example.experthour.auth.JwtService;
import com.example.experthour.user.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsServiceImpl uds) {
        this.jwtService = jwtService;
        this.userDetailsService = uds;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/api/auth")
                || path.startsWith("/actuator")
                || path.startsWith("/error")
                || path.equals("/favicon.ico");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        try {
            String authHeader = req.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                chain.doFilter(req, res);
                return;
            }

            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var userDetails = userDetailsService.loadUserByUsername(email);

                if (jwtService.validateToken(token)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            // If token is invalid, expired, user not found, etc
            // DO NOT crash the app — just continue without authentication
            chain.doFilter(req, res);
            return;
        }

        chain.doFilter(req, res);
    }
}
