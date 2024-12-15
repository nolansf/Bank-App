import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Branch> branches;
    private List<Customer> customer;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
        this.customer = new ArrayList<>();
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public void removeBranch(Branch branch) {
        branches.remove(branch);
    }

    public void addUser(Customer user) {
        // Check if user already exists to prevent duplicates
        /* 
        if (!customer.stream().anyMatch(c -> c.getCustomerId().equals(user.getCustomerId()))) {
            customer.add(user);
        }
        */
        if (!customer.contains(user)) {
            customer.add(user);
        }
    }

    public void removeUser(Customer user) {
        // Remove user only if they have no active accounts
        if (user.viewAccounts().isEmpty()) {
            customer.remove(user);
        }
    }

    public Customer findCustomerById(String userId) {
        for (Customer c : customer) {
            if (c.getCustomerId().equalsIgnoreCase(userId)) {
                return c; 
            }
        }
        return null;
    }

    public List<Customer> getAllUsers() {
        return new ArrayList<>(customer);
    }

    public double calculateTotalFunds() {
        double totalFunds = 0.0;
        for (Branch branch : branches) {
            totalFunds += branch.calculateBranchFunds();
        }
        return totalFunds;
    }

    public String generateReport() {
        System.out.println("Bank Report for " + name + "\nTotal Branches: " + branches.size() + "\nTotal Funds: $" + this.calculateTotalFunds() + "\nTotal Users: " + customer.size());
        System.out.println();
        return null;
    }

    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {
        return new ArrayList<>(branches);
    }

    public Branch getBranchByCode(String branchCode) {
        for (Branch branch : branches) { 
            if (branch.getBranchCode().equals(branchCode)) {
                return branch;
            }
        }
        return null;
    }
}