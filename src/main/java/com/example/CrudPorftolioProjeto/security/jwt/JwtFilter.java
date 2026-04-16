package com.example.CrudPorftolioProjeto.security.jwt;

import com.example.CrudPorftolioProjeto.service.UsuarioDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        if(request.getServletPath().startsWith("/auth")){
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            String email = jwtUtil.extrairEmail(token);

            UserDetails user = service.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken Auth =
                    new UsernamePasswordAuthenticationToken(
                            user,null,user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(Auth);
        }

        chain.doFilter(request, response);

    }
}