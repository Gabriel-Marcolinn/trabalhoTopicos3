package com.biblioteca.repository;

import com.biblioteca.entity.Autor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class AutorRepository {

    @PersistenceContext
    @Inject
    private EntityManager em;

    public List<Autor> findAll() {
        List<Autor> autores = em.createQuery("select a from Autor a", Autor.class).getResultList();
        return !autores.isEmpty() ? autores : null;
    }

    public Integer count () {
        return em.createQuery("select count(a) from Autor a", Integer.class).getSingleResult();
    }
}
