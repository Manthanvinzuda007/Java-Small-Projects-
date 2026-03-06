// By Manthan Vinzuda 
// Base Class (The Parent)
class BankAccount {
    protected String owner; // 'protected' allows child classes to access it
    protected double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(owner + " withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds for " + owner);
        }
    }

    // A method that can be overridden (Polymorphism)
    public void displayAccountType() {
        System.out.println("Standard Checking Account");
    }
}

// Child Class (Inheritance)
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String owner, double balance, double interestRate) {
        super(owner, balance); // Calls the Parent constructor
        this.interestRate = interestRate;
    }

    // Overriding the parent method (Polymorphism)
    @Override
    public void displayAccountType() {
        System.out.println("High-Yield Savings Account (" + interestRate + "% Interest)");
    }

    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest applied: $" + interest);
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating a SavingsAccount object
        SavingsAccount mySavings = new SavingsAccount("Alex", 1000.00, 5.0);

        mySavings.displayAccountType(); 
        mySavings.withdraw(200.00);
        mySavings.applyInterest(); // Unique to SavingsAccount
        
        System.out.println("Final Balance: $" + mySavings.balance);
    }
}
