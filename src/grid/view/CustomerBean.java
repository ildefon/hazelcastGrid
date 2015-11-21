/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grid.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import grid.controller.CustomerDataModel;
import grid.model.CustomerDTO;

/**
 *
 * @author ilde
 */
@ManagedBean(name = "customerBean")
@ViewScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = -1569840483572379312L;
	
	private LazyDataModel<CustomerDTO> lazyModel;
 
     
    @PostConstruct
    public void init() {
        lazyModel = new CustomerDataModel();
    }
 
    public LazyDataModel<CustomerDTO> getLazyModel() {
        return lazyModel;
    }
     
}
