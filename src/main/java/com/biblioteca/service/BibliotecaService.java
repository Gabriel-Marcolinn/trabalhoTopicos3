package com.biblioteca.service;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.AutorRepository;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BibliotecaService {

    @Inject
    private AutorRepository autorRepository;

    @Inject
    private LivroRepository livroRepository;

    @Inject
    private EmprestimoRepository emprestimoRepository;

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Integer contarTotalLivros() {
        return livroRepository.count();
    }

    public Integer contarLivrosDisponiveis() {
        return livroRepository.countByDisponivel(true);
    }

    public Integer contarEmprestimosAtivos() {
        return emprestimoRepository.countAtivos();
    }

    public Integer contarTotalAutores() {
        return autorRepository.count();
    }
}
