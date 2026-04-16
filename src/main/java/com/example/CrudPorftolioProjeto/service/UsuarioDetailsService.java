package com.example.CrudPorftolioProjeto.service;

import com.example.CrudPorftolioProjeto.exception.RegraNegocioException;
import com.example.CrudPorftolioProjeto.model.Usuario;
import com.example.CrudPorftolioProjeto.repository.UsuarioRepository;
import com.example.CrudPorftolioProjeto.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuariorepository;


    @Override
    public UserDetails loadUserByUsername(String email) {

        Usuario usuario = usuariorepository.findByEmail(email)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return new UsuarioDetails(usuario);
    }

}
