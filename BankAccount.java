// By Manthan Vinzuda 
import java.util.Scanner;

// The Blueprint (Class)
class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor to initialize the account
    public BankAccount(String name, double initialDeposit) {
        this.accountHolder = name;
        this.balance = initialDeposit;
    }

    // Method to add money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        }
    }

    // Method to take money out
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
        } else {
            System.out.println("Error: Insufficient funds or invalid amount.");
        }
    }

    // Getter to check the balance safely
    public void displayStatus() {
        System.out.println("\n--- Account Update ---");
        System.out.println("Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
    }
}

// The Main Execution
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name to open an account: ");
        String name = input.nextLine();

        // Creating the Object 
        BankAccount myAccount = new BankAccount(name, 500.00);

        myAccount.displayStatus();
        
        System.out.print("\nEnter amount to deposit: ");
        double dep = input.nextDouble();
        myAccount.deposit(dep);

        System.out.print("Enter amount to withdraw: ");
        double wit = input.nextDouble();
        myAccount.withdraw(wit);

        myAccount.displayStatus();
        
        input.close();
    }
}
