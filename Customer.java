import java.util.*;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private List<Account> accounts;

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public List<Account> viewAccounts() {
        return new ArrayList<>(accounts);
    }

    public void updateContactInfo(String email) {
        this.email = email;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + "]";
    }
}