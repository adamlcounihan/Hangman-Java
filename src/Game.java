import java.util.HashSet;
import java.util.Set;

/**
 * Manages the game logic, including starting the game
 * processing guesses, and determining win/loss conditions.
 */
public class Game {
    private String secretWord;

    private StringBuilder currentGuess;
    private int maxAttempts;
    private int wrongAttempts;
    private Set<Character> guessedLetters;

    public Game(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.wrongAttempts = 0;
        this.guessedLetters = new HashSet<>();
        chooseWord();
    }

    /**
     * Returns Secret Word.
     * @return secretWord.
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Returns the currentGuess.
     * @return currentGuess.
     */
    public StringBuilder getCurrentGuess() {
        return currentGuess;
    }

    /**
     * Returns the maximum number of attempts to guess.
     * @return maxAttempts.
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Returns the number of incorrect attempts already made.
     * @return wrongAttempts.
     */
    public int getWrongAttempts() {
        return wrongAttempts;
    }

    /**
     * Selects a word from the WordList and initializes the currentGuess variable.
     * The chosen word will be used for further processing in the game.
     * The currentGuess is initialized with underscores to represent each letter in the chosen word.
     */
    private void chooseWord() {
        WordList wordList = new WordList("words.csv");
        this.secretWord = wordList.getRandomWord();
        this.currentGuess = new StringBuilder("_".repeat(secretWord.length()));
    }

    /**
     * Checks if guess is in secretWord and updates currentGuess
     * Updates wrongAttempts if guess is incorrect
     * Adds guess to guessedLetters
     * @param guess The character guessed by the player.
     * @return true if the guess is correct, false otherwise.
     */
    public boolean makeGuess(char guess) {
        if(guessedLetters.contains(guess)) {
            System.out.println("You already guessed that letter.");
            return false;
        }
        guessedLetters.add(guess);

        boolean isCorrect = false;
        for(int i = 0; i < secretWord.length(); i++) {
            if(secretWord.charAt(i) == guess) {
                System.out.println("Correct guess!");
                currentGuess.setCharAt(i, guess);
                isCorrect = true;
            }
        }

        if(!isCorrect) {
            System.out.println("Incorrect guess.");
            wrongAttempts++;
            Display display = new Display();
            display.printHangman(wrongAttempts);
        }

        return isCorrect;
    }

    /**
     * Checks if the game has been won.
     * @return true if the game is won, false otherwise.
     */
    public boolean isGameWon() {
        return currentGuess.toString().equals(secretWord);
    }

    /**
     * Checks if the game has been lost.
     * @return true if the game is lost, false otherwise.
     */
    public boolean isGameLost() {
        return wrongAttempts >= maxAttempts;
    }

}
