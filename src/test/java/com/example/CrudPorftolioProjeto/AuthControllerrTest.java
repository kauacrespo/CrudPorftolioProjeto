package com.example.CrudPorftolioProjeto;

import com.example.CrudPorftolioProjeto.model.Usuario;
import com.example.CrudPorftolioProjeto.security.jwt.JwtUtil;
import com.example.CrudPorftolioProjeto.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerrTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void deveRegistrarUsuario() throws Exception {
        String json = """
        {
          "email":"teste@email.com" ,
          "senha": "123456"
        }
        """;

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("123456");

        when(usuarioService.SalvaUsuario(any(Usuario.class)))
                .thenReturn(usuario);


        when(jwtUtil.gerarToken("teste@email.com"))
                .thenReturn("token_fake");

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deveFazerLoginERetornarToken() throws Exception {

        String json = """
        {
          "email": "teste@email.com",
          "senha": "123456"
        }
        """;

        when(jwtUtil.gerarToken("teste@email.com"))
                .thenReturn("token_fake");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("token_fake"));
    }
}
