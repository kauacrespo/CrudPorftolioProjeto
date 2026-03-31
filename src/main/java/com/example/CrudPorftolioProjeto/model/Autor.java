package com.example.CrudPorftolioProjeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nacionalidade;


}



