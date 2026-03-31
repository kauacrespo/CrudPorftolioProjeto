package com.example.CrudPorftolioProjeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record LivroRequestDTO(
        @NotNull(message = "Id do autor e obrigatorio!")
        long id,

        @NotBlank(message = "O titulo nao pode ser vazio")
        String tituloLivro,

        @NotBlank(message = "O ISBN é obrigatorio!")
        String isbn,
        @NotNull
        LocalDate dataLancamento
){}
