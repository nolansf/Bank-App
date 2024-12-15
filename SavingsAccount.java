public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, Customer accountHolder, double interestRate) {
        super(accountNumber, accountHolder);
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return balance * interestRate;
    }

    public void applyInterest() {
        double interest = calculateInterest();
        deposit(interest);
        System.out.println("Interest applied: $" + interest);
        System.out.println();
    }

    @Override
    public String toString() {
        return "SavingsAccount [accountNumber=" + accountNumber + ", balance=" + balance + ", user=" + accountHolder + "]";
    }
}