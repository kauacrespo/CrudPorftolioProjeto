package com.example.CrudPorftolioProjeto.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(
        @NotBlank(message = "Nome do Autor obrigatorio")
        String nome,
        @NotBlank(message = "Nacionalidade obrigatoria")
        String nacionalidade
) {
}
