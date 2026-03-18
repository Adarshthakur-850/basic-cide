import java.util.Random;
import java.util.Scanner;

public class CoinFlipGame {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;

        System.out.println("Welcome to the Coin Flip-Flop Game!");
        System.out.println("Try to guess whether the next flip will be Heads (h) or Tails (t).");
        System.out.println("Type 'quit' to exit the game at any time.\n");

        while (true) {
            String flip = flipCoin(random);
            System.out.print("What's your guess? (h/t): ");
            String guess = scanner.nextLine().trim().toLowerCase();

            if (guess.equals("quit")) {
                break;
            }

            if (!guess.equals("h") && !guess.equals("t")) {
                System.out.println("Invalid guess. Please enter 'h' or 't'.");
                continue;
            }

            System.out.println("The coin flip result is: " + flip);

            if (guess.equals(flip.equals("Heads") ? "h" : "t")) {
                score++;
                System.out.println("Correct guess! Your current score is: " + score);
            } else {
                System.out.println("Incorrect guess. Your current score is: " + score);
            }

            System.out.println();
        }

        System.out.println("\nThanks for playing! Your final score is: " + score);
        scanner.close();
    }

    public static String flipCoin(Random random) {
        return random.nextBoolean() ? "Heads" : "Tails";
    }
}
