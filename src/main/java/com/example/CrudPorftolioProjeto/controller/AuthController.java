package com.example.CrudPorftolioProjeto.controller;

import com.example.CrudPorftolioProjeto.dto.AuthRequestDTO;
import com.example.CrudPorftolioProjeto.model.Usuario;
import com.example.CrudPorftolioProjeto.security.jwt.JwtUtil;
import com.example.CrudPorftolioProjeto.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class  AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Registra um novo usuario")
    @PostMapping("/register")
    public Usuario criarConta(@RequestBody Usuario usuario) {
        return usuarioService.SalvaUsuario(usuario);
    }

    @Operation(summary = "Faz login por usuario")
    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );

        return jwtUtil.gerarToken(request.email());
    }
}
