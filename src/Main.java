import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Guessing Game!");
        Game game = new Game(6);

        while (!game.isGameWon() && !game.isGameLost()) {
            System.out.println("\n\nCurrent guess: " + game.getCurrentGuess());
            System.out.print("Enter a letter to guess: ");
            char guess = scanner.nextLine().charAt(0);

            if (game.makeGuess(guess)) {
                System.out.println("Correct guess!");
            } else {
                System.out.println("Incorrect guess.");
            }

            System.out.println("You have " + (game.getMaxAttempts() - game.getWrongAttempts()) + " attempts left.");
        }

        if (game.isGameWon()) {
            System.out.println("Congratulations, you won! The word was: " + game.getSecretWord());
        } else {
            System.out.println("Game over. The correct word was: " + game.getSecretWord());
        }

        scanner.close();
    }
}
