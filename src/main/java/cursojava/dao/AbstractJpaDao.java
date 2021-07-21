package cursojava.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDao< T extends Serializable> {

    private Class< T > clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    public T findOne( Long id ){
        return entityManager.find( clazz, id );
    }
    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public <T> T save( T entity ){
        T newRecord = null;
        newRecord = entityManager.merge(entity);
        entityManager.flush();
        return newRecord;
    }

    public void update( T entity ){
        entityManager.merge( entity );
    }

    public <T> void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( Long entityId ){
        T entity = this.findOne( entityId );
        delete( entity );
    }



}