/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grid.model;

import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author ilde
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();


    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        StringBuilder sb = new StringBuilder("select p from ");
        sb.append(entityClass.getName());
        sb.append(" p");
        return getEntityManager().createQuery(sb.toString()).getResultList();
    }
    
}
