package com.example.CrudPorftolioProjeto.dto;

import java.time.LocalDate;

public record LivroResponseDTO(
        long id,
        String nomeAutor,
        String tituloLivro,
        String isbn,
        LocalDate dataPublicacao
){}
