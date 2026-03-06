// By Manthan Vinzuda 
// 1. The Interface (The Contract)
interface Transferable {
    void transfer(BankAccount destination, double amount);
}

// 2. The Abstract Class (The Template)
abstract class BankAccount {
    protected String owner;
    protected double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // Every account MUST have a way to show info, but the logic varies
    public abstract void printStatement();

    public void deposit(double amount) {
        balance += amount;
    }
}

// 3. The Concrete Implementation
class PremiumAccount extends BankAccount implements Transferable {
    
    public PremiumAccount(String owner, double balance) {
        super(owner, balance);
    }

    @Override
    public void printStatement() {
        System.out.println(">>> PREMIUM STATEMENT <<<");
        System.out.println("Client: " + owner + " | Balance: $" + balance);
    }

    @Override
    public void transfer(BankAccount destination, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            destination.deposit(amount);
            System.out.println("Transfer successful: $" + amount + " sent to " + destination.owner);
        } else {
            System.out.println("Transfer failed: Insufficient funds.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PremiumAccount userA = new PremiumAccount("Alice", 2000.00);
        PremiumAccount userB = new PremiumAccount("Bob", 500.00);

        userA.printStatement();
        userA.transfer(userB, 350.00); // Using the Interface method
        
        userB.printStatement();
    }
}
