package com.biblioteca.presentation;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Livro;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped; // Ou ViewScoped se tiver interações complexas
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@RequestScoped
public class LivroCadastroBean {

    @Inject
    EntityManager em;

    private Livro livro = new Livro();
    private Long autorIdSelecionado; // Para capturar o ID do selectOneMenu
    private List<Autor> listaAutores;

    @PostConstruct
    public void init() {
        // Carrega todos os autores para preencher o dropdown
        this.listaAutores = em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
    }

    @Transactional
    public String salvar() {
        try {
            // Busca o autor pelo ID selecionado e associa ao livro
            if (autorIdSelecionado != null) {
                Autor autor = em.find(Autor.class, autorIdSelecionado);
                livro.setAutor(autor);
            } else {
                throw new RuntimeException("Selecione um Autor.");
            }

            em.persist(livro);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Livro cadastrado com sucesso!"));

            // Limpa o formulário para novo cadastro
            livro = new Livro();
            autorIdSelecionado = null;

            return null; // Fica na mesma página
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao salvar livro: " + e.getMessage()));
            return null;
        }
    }

    // Getters e Setters
    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public Long getAutorIdSelecionado() { return autorIdSelecionado; }
    public void setAutorIdSelecionado(Long autorIdSelecionado) { this.autorIdSelecionado = autorIdSelecionado; }

    public List<Autor> getListaAutores() { return listaAutores; }
}