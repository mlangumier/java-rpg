package rpg.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    Path filePath;
    String fileName;
    List<String> lines = new ArrayList<>();

    /**
     * Constructor
     */
    public ScoreManager() {
        this.fileName = "scores.txt";
        this.filePath = Paths.get("src/rpg/resources/" + this.fileName);

        try {
            this.readScores();
        } catch (IOException ignored) {
            // INFO: If the file doesn't exist, automatically creates the file when using method `writeScore()`.
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * Reads all the scores recorded in the file. If the file doesn't exist, automatically creates it.
     * @throws IOException If the file doesn't exist
     */
    public void readScores() throws IOException {
        setLines(Files.readAllLines(this.filePath));
    }

    /**
     * Displays the scores present in the file
     */
    public void displayScores() {
        for (String line : this.getLines()) {
            System.out.println(line);
        }
    }

    /**
     * Add a new score to the current list
     * @param playerName The player whose score we're recording
     * @param score Number of enemies the player managed to kill
     */
    public void addScore(String playerName, int score) {
        this.lines.add(String.format("%s: %s enemies killed! (%s)", playerName, score, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    /**
     * Write the current scores { lines } in the score file
     */
    public void writeScores() throws IOException {
        Files.write(this.getFilePath(), this.getLines());
    }

    // public void resetScores() throws IOException {}

    @Override
    public String toString() {
        return "ScoreManager{" +
                "filePath=" + filePath +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
