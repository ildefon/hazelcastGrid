package grid.model;

import java.util.List;
import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * @author ilde
 *
 */
public final class CustomerParser {
	
	public static CustomerDTO parseCustomer(Customer c){
		CustomerDTO customer = new CustomerDTO();
		customer.setCompany(c.getCompany());
		customer.setIBAN(c.getIBAN());
		customer.setName(c.getName());
		customer.setPhone(c.getPhone());
		customer.setId(c.getId().intValue());
		return customer;
	}
	
	
	public static void loadCustomers(List<Customer> list){
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(new Config());
	    Map<Integer, CustomerDTO> customers = hzInstance.getMap("customers");
		for(Customer c: list){
			customers.put(c.getId().intValue(), parseCustomer(c));
		}
	}

}
