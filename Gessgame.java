import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        
        int secretNumber = random.nextInt(10) + 1;
        int attempts = 0;
        boolean won = false;

        System.out.println("I'm thinking of a number between 1 and 10.");

        while (attempts < 3) {
            System.out.print("Guess #" + (attempts + 1) + ": ");
            int guess = input.nextInt();
            attempts++;

            if (guess == secretNumber) {
                won = true;
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
        }

        if (won) {
            System.out.println("Correct! You won in " + attempts + " tries.");
        } else {
            System.out.println("Game Over. The number was " + secretNumber);
        }

        input.close();
    }
}
