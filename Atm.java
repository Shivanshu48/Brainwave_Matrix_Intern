import java.util.*;

class Account {
    private String accountNo;       //to hold the account number of user
    private String pin;             //to hold the pin of user
    private double balance;         //to hold the balance of user
    private List<String> transactionHistory;

    //function for taking input of account details 
    public Account(String accountNumber, String pin, double balance) {
        this.accountNo = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }


    public String getAccountNumber() {
        return accountNo;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    //Function to make amount withdrawl and according calculations
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Withdrawal Successful.");
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    //Fuction to take deposit of amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposit Successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    //function to change pin
    public void changePin(String newPin) {
        if (newPin.length() == 4) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Invalid PIN. It must be 4 digits.");
        }
    }

    //Fuction to display fund transfer status and performing other calculations
    public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;  //currnet bbalance updated
            recipient.deposit(amount);  
            transactionHistory.add("Transferred: " + amount + " to " + recipient.getAccountNumber());
            System.out.println("Transfer Successful.");
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    //Function to print tranction history
    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}



public class Atm {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        // Sample accounts details for testing of the program
        accounts.put("13579", new Account("13579", "1234", 1500.0));
        accounts.put("97543", new Account("97543", "6789", 2000.0));
        accounts.put("02468", new Account("02468", "9876", 2500.0));
        accounts.put("86420", new Account("86420", "4321", 3000.0));

        System.out.println("NAMASTE!");

        while (true) {
            System.out.print("Enter your account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter your PIN: ");
            String pin = scanner.nextLine();

            Account account = authenticate(accountNumber, pin);  //passing details for bank account verification
            if (account != null) {
                performTransactions(account);
            } else {
                System.out.println("Invalid account number or PIN.");  //invalid account details found
            }
        }
    }

    //account details verification
    private static Account authenticate(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            return account;
        }
        return null;
    }

    //function for running atm functionalities
    private static void performTransactions(Account account) {
        while (true) {
            System.out.println("\nSelect a transaction:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Deposit");
            System.out.println("4. Fund Transfer");
            System.out.println("5. Mini Statement");
            System.out.println("6. PIN Change");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  //consume newline

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter the recipient's account number: ");
                    String recipientAccountNumber = scanner.nextLine();
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    Account recipient = accounts.get(recipientAccountNumber);
                    if (recipient != null) {
                        account.transfer(recipient, transferAmount);
                    } else {
                        System.out.println("Recipient account not found.");
                    }
                    break;
                case 5:
                    account.printTransactionHistory();
                    break;
                case 6:
                    System.out.print("Enter your new PIN: ");
                    String newPin = scanner.nextLine();
                    account.changePin(newPin);
                    break;
                case 7:
                    System.out.println("Thank you for using the ATM. NAMASTE!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

