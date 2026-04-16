package com.example.CrudPorftolioProjeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
         @NotBlank(message = "login obrigatoria")
         @Email
        String email,
         @NotBlank(message = "Senha obrigatoria")
        String senha
) {
}
