package com.example.CrudPorftolioProjeto.service;

import com.example.CrudPorftolioProjeto.dto.AutorResponseDTO;
import com.example.CrudPorftolioProjeto.dto.AutorRequestDTO;
import com.example.CrudPorftolioProjeto.model.Autor;
import com.example.CrudPorftolioProjeto.repository.AutorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AutorService {

    private AutorRepository autorRepository;
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorResponseDTO salvarAutor(@Valid AutorRequestDTO dto) {

        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        Autor autorsalvo = autorRepository.save(autor);
        return converteDTO(autorsalvo);

    }

    public List<AutorResponseDTO> todosAutores(){

        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .map(this::converteDTO)
                .toList();
    }

    private  AutorResponseDTO converteDTO(Autor autor){
       return new AutorResponseDTO(
               autor.getId(),
               autor.getNome(),
               autor.getNacionalidade());
    }
}
