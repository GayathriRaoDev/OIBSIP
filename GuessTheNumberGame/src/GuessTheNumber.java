import javax.swing.JOptionPane;
import java.util.Random;

public class GuessTheNumber {

    // ----- Game parameters -----
    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 7;   // Task 1
    private static final int ROUNDS = 3;         // Task 2

    public static void main(String[] args) {

        int totalScore = 0;

        for (int round = 1; round <= ROUNDS; round++) {
            int roundScore = playRound(round);
            totalScore += roundScore;
        }

        JOptionPane.showMessageDialog(
                null,
                String.format("Game over! Your total score: %d", totalScore),
                "Final Score",
                JOptionPane.INFORMATION_MESSAGE
        );
        System.exit(0);
    }

    /**
     * Plays one round and returns the points earned.
     */
    private static int playRound(int roundNumber) {
        Random rand = new Random();
        int secret = rand.nextInt(MAX - MIN + 1) + MIN;

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {

            String input = JOptionPane.showInputDialog(
                    null,
                    String.format("Round %d of %d\nGuess a number (%d-%d)\nAttempt %d of %d:",
                            roundNumber, ROUNDS, MIN, MAX, attempt, MAX_ATTEMPTS),
                    "Guess the Number",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Cancel pressed → abort the program gracefully
            if (input == null) System.exit(0);

            int guess;
            try {
                guess = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a valid integer.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                attempt--;                // Do not count invalid input
                continue;
            }

            if (guess == secret) {
                int points = MAX_ATTEMPTS - attempt + 1;  // Scoring rule
                JOptionPane.showMessageDialog(null,
                        String.format("Correct! You earned %d points.", points),
                        "Well done",
                        JOptionPane.INFORMATION_MESSAGE);
                return points;
            } else if (guess < secret) {
                JOptionPane.showMessageDialog(null,
                        "Too low — try a higher number.",
                        "Hint",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Too high — try a lower number.",
                        "Hint",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

        // Out of attempts
        JOptionPane.showMessageDialog(null,
                String.format("Out of attempts! The number was %d.\nPoints earned: 0", secret),
                "Round Lost",
                JOptionPane.INFORMATION_MESSAGE);
        return 0;
    }
}
