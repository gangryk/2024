// Made by Gabi Angryk on 12/1/2024
import java.util.Scanner;
import java.util.Random;

public class WaltonHackathonQuizBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] terms = new String[100];
        String[] definitions = new String[100];
        boolean[] answeredCorrectly = new boolean[100];
        int termCount = 0;

        // Input phase: Collect terms and definitions
        System.out.println("Enter terms and definitions (or type 'quiz' to start the quiz):");
        while (true) {
            System.out.print("Term: ");
            String term = scanner.nextLine().trim();
            if (term.equalsIgnoreCase("quiz")) break;

            System.out.print("Definition: ");
            String definition = scanner.nextLine().trim();
            terms[termCount] = term;
            definitions[termCount] = definition;
            answeredCorrectly[termCount] = false;
            termCount++;
        }

        if (termCount == 0) {
            System.out.println("No terms entered. Exiting...");
            return;
        }

        // Quiz phase
        System.out.println("\nQuiz starting! Type 'exit' to stop.");
        Random random = new Random();
        int correctAnswers = 0;
        int totalQuestions = 0;

        while (true) {
            int randomIndex = random.nextInt(termCount);
            if (allAnsweredCorrectly(answeredCorrectly, termCount)) {
                System.out.println("\nCongratulations! You've answered all terms correctly.");
                break;
            }

            String randomTerm = terms[randomIndex];
            String correctDefinition = definitions[randomIndex];

            if (answeredCorrectly[randomIndex]) {
                continue;  // Skip terms already answered correctly
            }

            System.out.println("\nWhat is the definition of: " + randomTerm);
            String userAnswer = scanner.nextLine().trim();

            if (userAnswer.equalsIgnoreCase("exit")) break;

            totalQuestions++;
            if (userAnswer.equalsIgnoreCase(correctDefinition)) {
                System.out.println("Correct!");
                answeredCorrectly[randomIndex] = true;
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct definition is: " + correctDefinition);
            }
        }

        // Final Results
        System.out.println("\nQuiz Ended!");
        System.out.println("You got " + correctAnswers + " out of " + totalQuestions + " correct.");
    }

    // Helper method to check if all terms have been answered correctly
    private static boolean allAnsweredCorrectly(boolean[] answeredCorrectly, int termCount) {
        for (int i = 0; i < termCount; i++) {
            if (!answeredCorrectly[i]) {
                return false;
            }
        }
        return true;
    }
}
