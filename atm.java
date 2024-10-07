import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<String, String> accounts; // Account number to PIN mapping
    private HashMap<String, Double> balances; // Account number to balance mapping
    private String currentAccount;

    public ATM() {
        accounts = new HashMap<>();
        balances = new HashMap<>();
        // Sample accounts: account number -> PIN and balance
        accounts.put("123456", "1234");
        balances.put("123456", 1000.0);
        accounts.put("654321", "4321");
        balances.put("654321", 2000.0);
    }

    public boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (accounts.containsKey(accountNumber) && accounts.get(accountNumber).equals(pin)) {
            currentAccount = accountNumber;
            System.out.println("Authentication successful!");
            return true;
        } else {
            System.out.println("Authentication failed. Please try again.");
            return false;
        }
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void checkBalance() {
        double balance = balances.get(currentAccount);
        System.out.printf("Your balance is: $%.2f%n", balance);
    }

    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balances.put(currentAccount, balances.get(currentAccount) + amount);
            System.out.printf("$%.2f deposited successfully.%n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        double balance = balances.get(currentAccount);
        if (amount > 0 && amount <= balance) {
            balances.put(currentAccount, balance - amount);
            System.out.printf("$%.2f withdrawn successfully.%n", amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void run() {
        if (authenticate()) {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                displayMenu();
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}


