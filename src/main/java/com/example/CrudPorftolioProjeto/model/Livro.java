package com.example.CrudPorftolioProjeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O titulo e obrigatorio")
    private String titulo;

    private String isbn;

    private LocalDate dataLancamento;

    private boolean disponivel = true;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
