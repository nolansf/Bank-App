import java.util.Date;

public class Transaction {
    private String transactionId;
    private double amount;
    private String transactionType;
    private Account account;
    private Date date;

    public Transaction(String transactionId, double amount, String transactionType, Account account) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.account = account;
        this.date = new Date();
    }

    public String getTransactionDetails() {
        return "Transaction ID: " + transactionId + " AccountID: " + account.getAccountNumber() +  ", Amount: $" + amount + ", Type: " + transactionType + ", Timestamp: " + date;
    }
}