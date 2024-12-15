# Banking App

## Overview

The Banking App is a Java-based console application modeling banks that allows users to manage branches, customer accounts, and transactions within a banking system. This project demonstrates core object-oriented programming principles, such as encapsulation, inheritance, and polymorphism.

## Features

Branch Management:
- List, add, view, and remove branches.

Account Management:
- Open new accounts (Savings and Checking).
- Deposit and withdraw funds.
- View and close accounts.

Customer Management:
- Add and remove customers.
- View customer details.

Reporting:
- Generate a bank overview report.
- View detailed branch reports.
- Access transaction history.

## Setup Instructions

Clone this repository:

```
git clone https://github.com/nolansf/banking-app.git
```

Navigate to the project directory:

```
cd banking-app
```

Compile the application:

```
javac *.java
```

Run the application:

```
java BankingApp
```

### File Structure

`BankingApp.java`: Main entry point for the application.

`Bank.java`: Handles core banking operations and data storage.

`Branch.java`: Represents individual bank branches.

`Customer.java`: Represents customers in the system.

`Account.java` (and its subclasses SavingsAccount and CheckingAccount): Represents bank accounts.

`Transaction`.java: Handles transactions, such as deposits and withdrawals.

### Example Usage

Start the application and enter the name of your bank.

Use the main menu to navigate to different functionalities, such as managing branches, accounts, or generating reports.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or feedback, please reach out to nsflynn@utica.edu

