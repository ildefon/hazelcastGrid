package grid.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import grid.model.CustomerDTO;
import grid.model.CustomerFacade;
import grid.model.CustomerParser;

@ManagedBean(name = "customerDataModel")
@ApplicationScoped
public class CustomerDataModel extends LazyDataModel<CustomerDTO> {
	
	private static final long serialVersionUID = 2326133093045425068L;

    private CustomerFacade facade = new CustomerFacade();
    public List<CustomerDTO> savedData;
    
    public CustomerDataModel(){
    	getCustomers();
    }
    
    @SuppressWarnings (value= "unchecked")
    public void getCustomers(){
    	CustomerParser.loadCustomers(facade.findAll());
    	ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap customers = client.getMap("customers");
    	savedData =  (List<CustomerDTO>) customers.values();
    }
    
    @Override
    public CustomerDTO getRowData(String rowKey) {
    	
        for(CustomerDTO customer : savedData) {
            if(customer.getId().equals(Integer.valueOf(rowKey)))
                return customer;
        }
        return null;
    }
    
    @Override
    public Object getRowKey(CustomerDTO customer) {
        return customer.getId();
    }
    
    @Override
    public List<CustomerDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	
     	List<CustomerDTO> data = new ArrayList<CustomerDTO>();
    	
    	// filter
        for(CustomerDTO customer :savedData) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(customer.getClass().getField(filterProperty).get(customer));
 
                        String valueLower = fieldValue.toLowerCase();
                        String filterLower = filterValue.toString().toLowerCase();
                        if(filterValue == null || valueLower.contains(filterLower.toString())) {
                            match = true;
	                    }
	                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(customer);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
    
}
