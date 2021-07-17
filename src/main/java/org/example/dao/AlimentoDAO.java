package org.example.dao;

import org.example.entity.Alimento;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AlimentoDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Alimento> queryAll() {
        Query query = em.createQuery("SELECT a FROM Alimento a");
        List<Alimento> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Alimento> getByCategory(String category) {
        Query query = em.createQuery("SELECT a FROM Alimento a WHERE a.categoria="+category);
        List<Alimento> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Alimento get(Integer id) {
        return em.find(Alimento.class, id);
    }

    @Transactional
    public Alimento save(Alimento alimento) {
        em.persist(alimento);
        em.flush();
        return alimento;
    }

    @Transactional
    public void delete(Alimento alimento) {
        Alimento r = get(alimento.getId());
        if(r != null) {
            em.remove(r);
        }
    }
}
