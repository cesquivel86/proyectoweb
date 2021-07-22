package cursojava.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class DaoGenerico< T extends Serializable> {

    private Class< T > clazz;

    @PersistenceContext
    EntityManager em;

    public void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    @Transactional(readOnly = true)
    public T findOne( Integer id ){
        return em.find( clazz, id );
    }

    @Transactional(readOnly = true)
    public List< T > queryAll(){
        return em.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    @Transactional
    public <T> T save( T entity ){
        T newRecord = null;
        newRecord = em.merge(entity);
        em.flush();
        return newRecord;
    }

    @Transactional
    public void update( T entity ){
        em.merge( entity );
    }



    @Transactional
    public <T> void delete(T t) {

        try {
            Method getId = clazz.getMethod("getId", null);
            try {
                Integer id = (Integer) getId.invoke(t,null);
                T tBorrar = (T) this.findOne(id);
                em.remove(em.merge(tBorrar));
                em.flush();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }




}