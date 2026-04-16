package com.example.CrudPorftolioProjeto.service;

import com.example.CrudPorftolioProjeto.exception.RegraNegocioException;
import com.example.CrudPorftolioProjeto.model.Usuario;
import com.example.CrudPorftolioProjeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public  Usuario SalvaUsuario(Usuario usuario){

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RegraNegocioException("Email já cadastrado");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }
}
