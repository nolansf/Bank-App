import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BankingApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank;
    public static void main(String[] args) {
        System.out.print("Name of your Bank: ");
        String name = scanner.nextLine();
        bank = new Bank(name);
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("[ Main Menu ]");
            System.out.println("---------------------------");
            System.out.println("[1] Manage Branches");
            System.out.println("[2] Manage Accounts");
            System.out.println("[3] Manage Customers");
            System.out.println("[4] Generate Report");
            System.out.println("[0] Quit");
            System.out.println("---------------------------");
            System.out.println("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1: 
                    manageBranches();
                    break;
                case 2: 
                    manageAccounts();
                    break;
                case 3: 
                    manageCustomersMenu();
                    break;
                case 4: 
                    generateReportMenu();
                    break;
                case 0: {
                    System.out.println("Exiting application...");
                    System.exit(0);
                }
                default: System.out.println("Invalid option. Please try again.");
                System.out.println();
            }
        }
    }

    private static void manageBranches() {
        while (true) {
            System.out.println("[ Main Menu > Manage Branches ]");
            System.out.println("---------------------------");
            System.out.println("[1] List All Branches");
            System.out.println("[2] View Branch Details");
            System.out.println("[3] Add a Branch");
            System.out.println("[4] Remove a Branch");
            System.out.println("[0] Back to Main Menu");
            System.out.println("---------------------------");
            System.out.println("Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1: 
                    listAllBranches();
                    break;
                case 2: 
                    viewBranchDetails();
                    break;
                case 3: 
                    addBranch();
                    break;
                case 4: 
                    removeBranch();
                    break;
                case 0: 
                    mainMenu();
                default: 
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }

    private static void addBranch() {
        // Prompting user for branch details
        System.out.print("Enter Branch Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();

        // Creating and adding a new branch
        Branch newBranch = new Branch(location, branchCode);
        bank.addBranch(newBranch);
        System.out.println("Branch added successfully!");
        System.out.println();
    }

    private static void viewBranchDetails() {
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist.");
            System.out.println();
            return;
        }  

        // Prompting user to select a branch
        listAllBranches();
        System.out.print("Enter Branch Code to view details: ");
        String branchCode = scanner.nextLine();

        Branch branch = bank.getBranchByCode(branchCode);
        if (branch != null) {
            System.out.println("Branch Location: " + branch.getLocation());
            System.out.println("Total Accounts: " + branch.getAccounts().size());
            System.out.println("Total Funds: $" + branch.calculateBranchFunds());
            System.out.println();
        } else {
            System.out.println("Branch not found.");
            System.out.println();
        }
    }

    private static void listAllBranches() {
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist.");
            System.out.println();
            return;
        }

        // Displaying all existing branches
        System.out.println("Existing Branches:");
        for (Branch branch : bank.getBranches()) {
            System.out.println("Branch Code: " + branch.getBranchCode() + ", Location: " + branch.getLocation());
        }
        System.out.println();
    }

    private static void removeBranch() {
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist.");
            System.out.println();
            return;
        }

        // Prompting user to select a branch to remove
        listAllBranches();
        System.out.print("Enter Branch Code to remove: ");
        String branchCode = scanner.nextLine();

        Branch branch = bank.getBranchByCode(branchCode);
        if (branch != null) {
            // Removing the branch
            bank.removeBranch(branch);
            System.out.println("Branch removed successfully!");
            System.out.println();
        } else {
            System.out.println("Branch not found.");
            System.out.println();
        }
    }




    /* ============================================================= */




    private static void manageAccounts() {
        while (true) {
            System.out.println("[ Main Menu > Manage Accounts ]");
            System.out.println("---------------------------");
            System.out.println("[1] Open a New Account");
            System.out.println("[2] View Account Details");
            System.out.println("[3] Deposit Funds");
            System.out.println("[4] Withdraw Funds");
            System.out.println("[5] Close an Account");
            System.out.println("[0] Back to Main Menu");
            System.out.println("---------------------------");
            System.out.println("Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: 
                    openNewAccount();
                    break;
                case 2: 
                    viewAccountDetails();
                    break;
                case 3: 
                    depositFunds();
                    break;
                case 4: 
                    withdrawFunds();
                    break;
                case 5: 
                    closeAccount();
                    break;
                case 0: 
                    mainMenu();
                default: 
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }

    private static void openNewAccount() {
        // First, ensure we have at least one branch
        if (bank.getBranches().isEmpty()) {
            System.out.println("Error: Create a branch first!");
            System.out.println();
            return;
        }

        // Create or select a user
        Customer user = createOrSelectUser();

        // Choose account type
        System.out.println("Select Account Type:");
        System.out.println("[1] Savings Account");
        System.out.println("[2] Checking Account");
        System.out.print("Choice: ");
        int accountTypeChoice = scanner.nextInt();
        scanner.nextLine();

        // Select branch
        listAllBranches();
        System.out.print("Enter Branch Code for the account: ");
        String branchCode = scanner.nextLine();
        Branch selectedBranch = bank.getBranchByCode(branchCode);

        if (selectedBranch == null) {
            System.out.println("Invalid branch. Account creation failed.");
            System.out.println();
            return;
        }

        // Generate unique account number
        String accountNumber = UUID.randomUUID().toString().substring(0, 8);

        // Create account based on type
        Account newAccount;
        if (accountTypeChoice == 1) {
            newAccount = new SavingsAccount(accountNumber, user, 0.05);
        } else if (accountTypeChoice == 2) {
            newAccount = new CheckingAccount(accountNumber, user, 500.0);
        } else {
            System.out.println("Invalid account type. Account creation failed.");
            System.out.println();
            return;
        }

        // Add account to user and branch
        user.addAccount(newAccount);
        selectedBranch.addAccount(newAccount);

        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println();
    }

    private static Customer createOrSelectUser() {
        System.out.println("Select User Creation Method:");
        System.out.println("---------------------------");
        System.out.println("[1] Create New User");
        System.out.println("[2] Use Existing User");
        System.out.println("---------------------------");
        System.out.print("Choice: ");
        int userChoice = scanner.nextInt();
        System.out.println();

        if (userChoice == 2) {
            System.out.print("Enter Customer ID: ");
            scanner.nextLine(); 
            String userId = scanner.nextLine();

            if (bank.findCustomerById(userId) != null) {
                return bank.findCustomerById(userId);
            } else {
                System.out.println("Not existing customer. Creating a new customer.");
                System.out.print("Enter User Name: ");
                scanner.nextLine(); 
                String name = scanner.nextLine();
                System.out.print("Enter User Email: ");
                String email = scanner.nextLine();
                System.out.println();

                // Creates new Customer
                Customer newUser = new Customer(userId, name, email);
                bank.addUser(newUser);
                return newUser;
            }
        } else {
            System.out.print("Enter Customer ID: ");
            scanner.nextLine();
            String customerId = scanner.nextLine();

            while (bank.findCustomerById(customerId) != null) {
                System.out.print("Enter a differnt Customer ID: ");
                customerId = scanner.nextLine();
                System.out.println();
            }

            System.out.println("Creating a new customer.");
            System.out.print("Enter User Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter User Email: ");
            String email = scanner.nextLine();
            System.out.println();

            // Creates new Customer
            Customer newUser = new Customer(customerId, name, email);
            bank.addUser(newUser);
            return newUser;
        }
    }

    private static void withdrawFunds() {
        // Ensure branches and accounts exist
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist. Create a branch first.");
            System.out.println();
            return;
        }

        // Select branch
        listAllBranches();
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        Branch branch = bank.getBranchByCode(branchCode);
        System.out.println();

        if (branch == null) {
            System.out.println("Invalid branch.");
            System.out.println();
            return;
        }

        // List accounts in the branch
        if (branch.getAccounts().isEmpty()) {
            System.out.println("No accounts exist in this branch.");
            System.out.println();
            return;
        }

        System.out.println("Accounts in Branch:");
        for (Account account : branch.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
        System.out.println();

        // Select account
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        Account selectedAccount = branch.getAccountByNumber(accountNumber);

        if (selectedAccount == null) {
            System.out.println("Invalid account.");
            System.out.println();
            return;
        }

        // Perform withdrawal
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Create transaction
        String transactionType = "Withdrawal";
        String transactionId = UUID.randomUUID().toString().substring(0, 8);
        Transaction transaction = new Transaction(transactionId, amount, transactionType, selectedAccount);

        branch.addTransaction(transaction);
        selectedAccount.withdraw(amount);
    }

    private static void depositFunds() {
        // Ensure branches and accounts exist
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist. Create a branch first.");
            System.out.println();
            return;
        }

        // Select branch
        listAllBranches();
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        Branch branch = bank.getBranchByCode(branchCode);

        if (branch == null) {
            System.out.println("Invalid branch.");
            System.out.println();
            return;
        }

        // List accounts in the branch
        if (branch.getAccounts().isEmpty()) {
            System.out.println("No accounts exist in this branch.");
            System.out.println();
            return;
        }

        System.out.println("Accounts in Branch:");
        for (Account account : branch.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
        System.out.println();

        // Select account
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        Account selectedAccount = branch.getAccountByNumber(accountNumber);

        if (selectedAccount == null) {
            System.out.println("Invalid account.");
            System.out.println();
            return;
        }

        // Perform deposit
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        // Create transaction
        String transactionType = "Deposit";
        String transactionId = UUID.randomUUID().toString().substring(0, 8);
        Transaction transaction = new Transaction(transactionId, amount, transactionType, selectedAccount);

        branch.addTransaction(transaction);
        selectedAccount.deposit(amount);
    }

    private static void viewAccountDetails() {
        // Ensure branches and accounts exist
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist. Create a branch first.");
            System.out.println();
            return;
        }

        // Select branch
        listAllBranches();
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        Branch branch = bank.getBranchByCode(branchCode);

        if (branch == null) {
            System.out.println("Invalid branch.");
            System.out.println();
            return;
        }

        // List accounts in the branch
        if (branch.getAccounts().isEmpty()) {
            System.out.println("No accounts exist in this branch.");
            System.out.println();
            return;
        }

        System.out.println("Accounts in Branch:");
        for (Account account : branch.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
        System.out.println();

        // Select account
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        Account selectedAccount = branch.getAccountByNumber(accountNumber);

        if (selectedAccount == null) {
            System.out.println("Invalid account.");
            System.out.println();
            return;
        }

        // Display detailed account information
        System.out.println("Account Details:");
        System.out.println("Account Number: " + selectedAccount.getAccountNumber());
        System.out.println("Current Balance: $" + selectedAccount.getBalance());
        System.out.println("Account Holder: " + selectedAccount.accountHolder.getName());

        // Additional details for specific account types
        if (selectedAccount instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) selectedAccount;
            System.out.println("Potential Interest: $" + savingsAccount.calculateInterest());
            System.out.println();
        } else if (selectedAccount instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) selectedAccount;
            System.out.println("Overdraft Limit: $" + (checkingAccount.checkOverdraft(1000) ? "500.0" : "0"));
            System.out.println();
        }
    }

    private static void closeAccount() {
        // Ensure branches and accounts exist
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist. Create a branch first.");
            System.out.println();
            return;
        }

        // Select branch
        listAllBranches();
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        Branch branch = bank.getBranchByCode(branchCode);
        System.out.println();
        if (branch == null) {
            System.out.println("Invalid branch.");
            System.out.println();
            return;
        }

        // List accounts in the branch
        if (branch.getAccounts().isEmpty()) {
            System.out.println("No accounts exist in this branch.");
            System.out.println();
            return;
        }

        System.out.println("Accounts in Branch:");
        for (Account account : branch.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
        System.out.println();

        // Select account
        System.out.print("Enter Account Number to close: ");
        String accountNumber = scanner.nextLine();
        Account selectedAccount = branch.getAccountByNumber(accountNumber);

        if (selectedAccount == null) {
            System.out.println("Invalid account.");
            System.out.println();
            return;
        }

        // Check account balance
        if (selectedAccount.getBalance() > 0) {
            System.out.println("Cannot close account with remaining balance. " + "Please withdraw funds first.");
            System.out.println();
            return;
        }

        // Remove account from user and branch
        Customer accountHolder = selectedAccount.accountHolder;
        accountHolder.removeAccount(selectedAccount);
        branch.removeAccount(selectedAccount);

        System.out.println("Account closed successfully!");
        System.out.println();
    }




    /* ============================================================= */




    private static void manageCustomersMenu() {
        while (true) {
            System.out.println("[ Manage Customers ]");
            System.out.println("---------------------------");
            System.out.println("[1] Add a Customer");
            System.out.println("[2] View Customer Details");
            System.out.println("[3] List All Customers");
            System.out.println("[4] Remove a Customer");
            System.out.println("[0] Back to Main Menu");
            System.out.println("---------------------------");
            System.out.print("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addCustomer();
                        break;
                case 2: viewCustomerDetails();
                        break;
                case 3: listAllCustomers();
                        break;
                case 4: removeCustomer();
                        break;
                case 0: {
                    return;
                }
                default: System.out.println("Invalid option. Please try again.");
                System.out.println();
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        String userId = scanner.nextLine();
        
        // Check if user already exists
        if (bank.findCustomerById(userId) != null) {
            System.out.println("Customer with this ID already exists.");
            System.out.println();
            return;
        }

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer newUser = new Customer(userId, name, email);
        bank.addUser(newUser);
        System.out.println("Customer added successfully!");
        System.out.println();
    }

    private static void viewCustomerDetails() {
        System.out.print("Enter customer ID: ");
        String userId = scanner.nextLine();
        
        Customer user = bank.findCustomerById(userId);
        if (user != null) {
            System.out.println("Customer Details:");
            System.out.println("ID: " + user.getCustomerId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Accounts:");
            for (Account account : user.viewAccounts()) {
                System.out.println("- Account Number: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
            }
            System.out.println();
        } else {
            System.out.println("Customer not found.");
            System.out.println();
        }
    }

    private static void listAllCustomers() {
        System.out.println("All Customers:");
        for (Customer user : bank.getAllUsers()) {
            System.out.println("- " + user.getCustomerId() + ": " + user.getName());
        }
        System.out.println();
    }

    private static void removeCustomer() {
        System.out.print("Enter customer ID: ");
        String userId = scanner.nextLine();
        
        Customer user = bank.findCustomerById(userId);
        if (user != null) {
            if (user.viewAccounts().isEmpty()) {
                bank.removeUser(user);
                System.out.println("Customer removed successfully!");
                System.out.println();
            } else {
                System.out.println("Cannot remove customer with active accounts.");
                System.out.println();
            }
        } else {
            System.out.println("Customer not found.");
            System.out.println();
        }
    }




    /* ============================================================= */




    private static void generateReportMenu() {
        System.out.println("[ Main Menu > Generate Report ]");
        System.out.println("---------------------------");
        System.out.println("[1] Bank Overview Report");
        System.out.println("[2] Detailed Branch Report");
        System.out.println("[3] View Transaction History");
        System.out.println("---------------------------");
        System.out.print("Option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                bank.generateReport();
                break;
            case 2:
                generateDetailedBranchReport();
                break;
            case 3:
                viewTransactionHistory();
                break;
            default:
                System.out.println("Invalid option.");
                System.out.println();
        }
    }

    private static void generateDetailedBranchReport() {
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist.");
            System.out.println();
            return;
        }

        listAllBranches();
        System.out.print("Enter Branch Code for detailed report: ");
        String branchCode = scanner.nextLine();

        Branch branch = bank.getBranchByCode(branchCode);
        if (branch == null) {
            System.out.println("Branch not found.");
            System.out.println();
            return;
        }

        System.out.println("Detailed Branch Report for " + branch.getLocation());
        System.out.println("---------------------------");
        System.out.println("Branch Code: " + branch.getBranchCode());
        System.out.println("Total Accounts: " + branch.getAccounts().size());
        System.out.println("Total Branch Funds: $" + branch.calculateBranchFunds());
        System.out.println();

        System.out.println("\nAccount Details:");
        for (Account account : branch.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
        System.out.println();

        System.out.println("\nRecent Transactions:");
        List<Transaction> transactions = branch.getTransactionHistory();
        int displayCount = Math.min(transactions.size(), 5);
        for (int i = 0; i < displayCount; i++) {
            System.out.println(transactions.get(i).getTransactionDetails());
        }
        System.out.println();
    }

    private static void viewTransactionHistory() {
        // Ensure branches exist
        if (bank.getBranches().isEmpty()) {
            System.out.println("No branches exist. Create a branch first.");
            System.out.println();
            return;
        }

        // Select branch
        listAllBranches();
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        Branch branch = bank.getBranchByCode(branchCode);

        if (branch == null) {
            System.out.println("Invalid branch.");
            System.out.println();
            return;
        }

        // Get and display transaction history
        List<Transaction> transactions = branch.getTransactionHistory();
        
        if (transactions.isEmpty()) {
            System.out.println("No transaction history available.");
            System.out.println();
            return;
        }

        System.out.println("Transaction History for " + branch.getLocation() + " Branch:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getTransactionDetails());
        }
        System.out.println();
    }
}