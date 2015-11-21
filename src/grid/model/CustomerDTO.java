/**
 * 
 */
package grid.model;

import java.io.Serializable;

/**
 * @author ilde
 */
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 4974238111698682393L;
	public String name;
	public String phone;
	public String company;
	public String IBAN;
    private Integer id;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object customer){
		return this.getId().equals(((CustomerDTO)customer).getId());
	}
	
	@Override
	public int hashCode(){
		return this.getId().hashCode();
	}
	
}
