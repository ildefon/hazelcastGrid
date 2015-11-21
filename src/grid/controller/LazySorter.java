package grid.controller;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import grid.model.CustomerDTO;
 
public class LazySorter implements Comparator<CustomerDTO> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
   
 
    public int compare(CustomerDTO customer1, CustomerDTO customer2) {
        try {
            Object value1 = CustomerDTO.class.getField(this.sortField).get(customer1);
            Object value2 = CustomerDTO.class.getField(this.sortField).get(customer2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
