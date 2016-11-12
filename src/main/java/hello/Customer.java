package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String ueberName;
    private String stadt;

    protected Customer() {}

    public Customer(String firstName, String lastName, String ueberName, String stadt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ueberName = ueberName;
        this.stadt = stadt;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUeberName() {
    	return ueberName;
    }
    
    public void setUeberName(String ueberName) {
    	this.ueberName = ueberName;
    }
    
    public String getStadt() {
    	return stadt;
    }
    
    public void setStadt(String stadt) {
    	this.stadt = stadt;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', ueberName='%s', stadt='%s']",
                id, firstName, lastName, ueberName, stadt);
    }

}
