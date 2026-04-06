package com.example.CrudPorftolioProjeto.dto;

import java.time.LocalDate;

public record LivroResponseDTO(
        long id,
        String tituloLivro,
        String isbn,
        String nomeAutor,
        LocalDate dataPublicacao
){}
