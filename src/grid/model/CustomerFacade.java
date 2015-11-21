/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grid.model;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author ilde
 */
public class CustomerFacade extends AbstractFacade<Customer> {
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em = Persistence.createEntityManagerFactory("dataGrid").createEntityManager();
    }

    public CustomerFacade() {
        super(Customer.class);
    }

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
}
