package org.example.dao;

import org.example.entity.Persona;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PersonaDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Persona guardar(Persona p) {
        em.persist(p);
        em.flush();
        return p;
    }

}
