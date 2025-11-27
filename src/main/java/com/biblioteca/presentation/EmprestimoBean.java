package com.biblioteca.presentation;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Livro;
import com.biblioteca.entity.Usuario;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Named
@RequestScoped
public class EmprestimoBean {

    @Inject
    EntityManager em;

    @Inject
    SecurityIdentity securityIdentity;

    @Transactional
    public void realizarEmprestimo(Livro livroSelecionado) {
        try {
            String username = securityIdentity.getPrincipal().getName();

            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.nomeUsuario = :uName", Usuario.class)
                    .setParameter("uName", username)
                    .getSingleResult();

            Emprestimo novoEmprestimo = new Emprestimo();
            novoEmprestimo.setLivro(livroSelecionado);
            novoEmprestimo.setNomeUsuario(usuario.getNomeUsuario());
            novoEmprestimo.setEmailUsuario(usuario.getEmail());
            novoEmprestimo.setDataEmprestimo(LocalDate.now());
            novoEmprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(7));

            Livro livroGerenciado = em.find(Livro.class, livroSelecionado.getId());
            livroGerenciado.setDisponivel(false);

            em.persist(novoEmprestimo);
            em.merge(livroGerenciado);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Empréstimo realizado com sucesso!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao processar empréstimo"));
        }
    }
}