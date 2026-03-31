package com.example.CrudPorftolioProjeto.controller;

import com.example.CrudPorftolioProjeto.dto.AutorResponseDTO;
import com.example.CrudPorftolioProjeto.dto.AutorRequestDTO;
import com.example.CrudPorftolioProjeto.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> novoAutor(@RequestBody @Valid AutorRequestDTO dto) {
        AutorResponseDTO autorSalvo = autorService.salvarAutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listarAutores() {
        List<AutorResponseDTO> listaAutor = autorService.todosAutores();
        return ResponseEntity.ok().body(listaAutor);
    }
}

