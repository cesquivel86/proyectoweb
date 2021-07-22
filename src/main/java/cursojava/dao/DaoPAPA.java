package cursojava.dao;

import cursojava.entity.Alimento;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class DaoPAPA<T> {

    @PersistenceContext
    private EntityManager em;

    Class<T> claseDelObjetoARecibir;

    @Transactional(readOnly = true)
    public <T> List<T> queryAll() {
        List result=null;
        try {
            Query query = em.createQuery("FROM "+claseDelObjetoARecibir+ "t");
            result = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

}
