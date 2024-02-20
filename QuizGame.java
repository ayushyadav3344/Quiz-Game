import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    private static final int QUESTION_TIME_LIMIT_SECONDS = 10;
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] questions = {
                "1. What is the capital of France?",
                "2. Which planet is known as the Red Planet?",
                "3. What is the largest mammal in the world?",
                // Add more questions as needed
        };

        String[][] options = {
                {"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"},
                {"A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"},
                {"A. Elephant", "B. Giraffe", "C. Blue Whale", "D. Gorilla"},
                // Add more options as needed
        };

        String[] correctAnswers = {"C", "B", "C"}; // Add correct answers corresponding to each question

        System.out.println("Welcome to the Quiz Game!");
        System.out.println("You have " + QUESTION_TIME_LIMIT_SECONDS + " seconds to answer each question.\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);

            for (String option : options[i]) {
                System.out.println(option);
            }

            String userAnswer = getUserAnswer(scanner);

            if (userAnswer.equalsIgnoreCase(correctAnswers[i])) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is " + correctAnswers[i] + ".\n");
            }
        }

        System.out.println("Quiz completed!");
        System.out.println("Your final score is: " + score + "/" + questions.length);

        scanner.close();
    }

    private static String getUserAnswer(Scanner scanner) {
        System.out.print("Your answer: ");
        Timer timer = new Timer();
        UserInputTask userInputTask = new UserInputTask(scanner);
        timer.schedule(userInputTask, QUESTION_TIME_LIMIT_SECONDS * 1000);

        String userAnswer = scanner.nextLine().toUpperCase();

        timer.cancel(); // Cancel the timer as the user has provided an answer
        return userAnswer;
    }

    private static class UserInputTask extends TimerTask {
        private final Scanner scanner;

        public UserInputTask(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public void run() {
            System.out.println("\nTime's up! Your answer will not be considered.");
            scanner.nextLine(); // Consume the remaining newline character
        }
    }
}
