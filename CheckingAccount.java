public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, Customer accountHolder, double overdraftLimit) {
        super(accountNumber, accountHolder);
        this.overdraftLimit = overdraftLimit;
    }

    public boolean checkOverdraft(double amount) {
        return (balance + overdraftLimit) >= amount;
    }

    @Override
    public void withdraw(double amount) {
        if (checkOverdraft(amount)) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println();
        } else {
            System.out.println("Overdraft limit exceeded");
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "CheckingAccount [accountNumber=" + accountNumber + ", balance=" + balance + ", overdraftLimit=" + overdraftLimit + ", user=" + accountHolder + "]";
    }
}