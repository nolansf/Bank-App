import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String location;
    private String branchCode;
    private List<Account> accounts;
    private List<Transaction> transactions;

    public Branch(String location, String branchCode) {
        this.location = location;
        this.branchCode = branchCode;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public double calculateBranchFunds() {
        double totalBalance = 0.0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }

    public String getLocation() {
        return location;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public Account getAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}