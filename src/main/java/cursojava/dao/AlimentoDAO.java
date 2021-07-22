package cursojava.dao;

import cursojava.entity.Categoria;
import cursojava.entity.Alimento;
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
        List<Alimento> result=null;
        try {
            Query query = em.createQuery("SELECT a FROM Alimento a");
            result = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    @Transactional(readOnly = true)
    public List<Alimento> getByCategory(Categoria category) {
        Query query = em.createQuery("SELECT a FROM Alimento a WHERE a.categoria.id="+category.getId());
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
    public <T> void update( T entity ){
        em.merge( entity );
    }

    @Transactional
    public void delete(Alimento alimento) {
        Alimento r = get(alimento.getId());
        if(r != null) {
            em.remove(r);
        }
    }
}
