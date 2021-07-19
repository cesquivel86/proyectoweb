package cursojava.dao;

import cursojava.entity.Categoria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoriaDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Categoria> queryAll() {
        Query query = em.createQuery("SELECT c FROM Categoria c");
        List<Categoria> result = query.getResultList();
        return result;
    }


    @Transactional(readOnly = true)
    public Categoria get(Integer id) {
        return em.find(Categoria.class, id);
    }

    @Transactional
    public Categoria save(Categoria categoria) {
        em.persist(categoria);
        em.flush();
        return categoria;
    }

    @Transactional
    public void delete(Categoria categoria) {
        Categoria r = get(categoria.getId());
        if(r != null) {
            em.remove(r);
        }
    }
}
