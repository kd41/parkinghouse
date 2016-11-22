package ee.home.parkinghouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public enum CustomerType {
        REGULAR, PREMIUM
    }

    private final long id;
    private String username;
    private CustomerType customerType;
    @JsonIgnore
    private boolean deleted;

    public User(long id, String username) {
        this.id = id;
        this.username = username;
        this.customerType = CustomerType.REGULAR;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}