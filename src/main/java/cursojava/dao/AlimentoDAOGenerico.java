package cursojava.dao;

import cursojava.entity.Alimento;
import cursojava.entity.Categoria;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class AlimentoDAOGenerico extends DaoGenerico{

    public AlimentoDAOGenerico(){
        this.setClazz(cursojava.entity.Alimento.class);
    }

    @Transactional(readOnly = true)
    public List<Alimento> getByCategory(Categoria category) {
        Query query = this.em.createQuery("SELECT a FROM Alimento a WHERE a.categoria.id="+category.getId());
        List<Alimento> result = query.getResultList();
        return result;
    }
}
