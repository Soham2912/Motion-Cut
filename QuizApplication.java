import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Question {
    private String questionText;
    private ArrayList<String> options;
    private int correctAnswer;

    public Question(String questionText, ArrayList<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private ArrayList<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            ArrayList<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Enter your answer: ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == question.getCorrectAnswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + options.get(question.getCorrectAnswer() - 1));
            }
        }

        System.out.println("Quiz completed! Your score is: " + score + "/" + questions.size());
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        // Create quiz questions
        Question question1 = new Question("What is the capital of France?",
                new ArrayList<>(List.of("London", "Paris", "Berlin", "Rome")), 2);
        Question question2 = new Question("Which planet is known as the Red Planet?",
                new ArrayList<>(List.of("Mars", "Venus", "Jupiter", "Mercury")), 1);

        // Create quiz
        Quiz quiz = new Quiz();
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        // Start quiz
        quiz.startQuiz();
    }
}
