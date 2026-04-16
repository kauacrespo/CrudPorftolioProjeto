package com.example.CrudPorftolioProjeto.controller;

import com.example.CrudPorftolioProjeto.dto.LivroRequestDTO;
import com.example.CrudPorftolioProjeto.dto.LivroResponseDTO;
import com.example.CrudPorftolioProjeto.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @Operation(summary = "Criar um novo livro")
    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody @Valid LivroRequestDTO dto){
        LivroResponseDTO novoLivro = livroService.salvaLivro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @Operation(summary = "Listar todos os livros")
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> buscarTodosLivros(){
        List<LivroResponseDTO> listaLivros = livroService.todosLivros();
        return ResponseEntity.ok(listaLivros);
    }

    @Operation(summary = "Desativa livro pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativaLivro(@PathVariable Long id){
        livroService.desativaLivro(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Habilita livro pelo id")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> restaurar(@PathVariable Long id){
        livroService.ativaLivro(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Busca livro pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscaID(@PathVariable Long id){
        LivroResponseDTO livroBuscado = livroService.buscarPorID(id);
        return ResponseEntity.ok(livroBuscado);
    }

}
