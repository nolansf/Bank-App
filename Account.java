public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Customer accountHolder;

    public Account(String accountNumber, Customer accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
            System.out.println();
        } else {
            System.out.println("Invalid deposit amount");
            System.out.println();
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println();
        } else {
            System.out.println("Insufficient funds or invalid amount");
            System.out.println();
        }
    }

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public Customer getAccountHolder() { return accountHolder; }
}