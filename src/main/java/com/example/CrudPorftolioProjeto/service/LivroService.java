package com.example.CrudPorftolioProjeto.service;

import com.example.CrudPorftolioProjeto.dto.LivroRequestDTO;
import com.example.CrudPorftolioProjeto.dto.LivroResponseDTO;
import com.example.CrudPorftolioProjeto.exception.RegraNegocioException;
import com.example.CrudPorftolioProjeto.model.Autor;
import com.example.CrudPorftolioProjeto.model.Livro;
import com.example.CrudPorftolioProjeto.repository.AutorRepository;
import com.example.CrudPorftolioProjeto.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    public LivroRepository livrorepositorio;

    @Autowired
    public AutorRepository autorrepository;

    public LivroResponseDTO salvaLivro(LivroRequestDTO dto){

        Autor autor = autorrepository.findById(dto.id())
                .orElseThrow(() -> new RegraNegocioException("Autor nao encontrado com o ID: " + dto.id()));

        Livro livro = new Livro();
        livro.setTitulo(dto.tituloLivro());
        livro.setAutor(autor);
        livro.setIsbn(dto.isbn());
        livro.setDataLancamento(dto.dataLancamento());
        livro.setDisponivel(true);
        Livro livroSalvo = livrorepositorio.save(livro);

        return converterParaDTO(livroSalvo);
    }

    public List<LivroResponseDTO> todosLivros(){

        List<Livro> livros = livrorepositorio.findBydisponivelTrue();
        System.out.println("Quantidade livros" + livros.size());
        return livros.stream()
                .map(this::converterParaDTO)
                .toList();

    }

    public void desativaLivro(Long id){
        Livro livro = livrorepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Livro nao encontrado no sistema"));
        livro.setDisponivel(false);
        livrorepositorio.save(livro);
    }

    public void ativaLivro(Long id){
        Livro livro = livrorepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Livro nao encontrado no sistema"));
        if (livro.isDisponivel()) {
            throw new RegraNegocioException("Este Livro ja esta ativo no sistema.");
        }
        livro.setDisponivel(true);
        livrorepositorio.save(livro);
    }

    public LivroResponseDTO buscarPorID(Long id){
        Livro livro = livrorepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Livro nao encontrado no sistema."));

        return converterParaDTO(livro);
    }

    private LivroResponseDTO converterParaDTO(Livro livro){
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAutor().getNome(),
                livro.getDataLancamento()
        );
    }
}
