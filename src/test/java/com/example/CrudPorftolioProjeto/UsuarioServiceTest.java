package com.example.CrudPorftolioProjeto;

import com.example.CrudPorftolioProjeto.model.Usuario;
import com.example.CrudPorftolioProjeto.repository.UsuarioRepository;
import com.example.CrudPorftolioProjeto.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveSalvarUsuarioComSenhaCriptografada() {
        Usuario usuario = new Usuario();
        usuario.setSenha("exemplo@gmail.com");
        usuario.setSenha("22413");

        when(passwordEncoder.encode("22413"))
                .thenReturn("senha_criptografada");

        when(usuarioRepository.save(any(Usuario.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Usuario resultado = usuarioService.SalvaUsuario(usuario);


        assertNotNull(resultado);
        assertEquals("senha_criptografada", resultado.getSenha());

        verify(usuarioRepository).save(any(Usuario.class));
        verify(passwordEncoder).encode("22413");

    }

}
