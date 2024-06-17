import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Takes in words from the words.csv file
 */
public class WordList {
    private List<String> words;

    /**
     * Constructs a new WordList and populates it
     * with words from the specified file.
     *
     * @param filename the file to read words from
     */
    public WordList(String filename) {
        words = new ArrayList<>();
        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file " + filename, e);
        }
    }

    /**
     * Returns a random word from the word list.
     *
     * @return a randomly selected word
     */
    public String getRandomWord() {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }
}
