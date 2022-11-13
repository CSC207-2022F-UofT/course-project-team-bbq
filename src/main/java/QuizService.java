import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is just a temporary class that I use to test quiz functionality while using scanner user input.
 * Feel free to run and try it out.
 */
public class QuizService {
    public static void main(String[] args) {
        // user input
        Scanner ui = new Scanner(System.in);

        // factories
        FlashcardFactory flashcardFactory = new FlashcardFactory();

        // flashcard creation
        Flashcard f1 = flashcardFactory.create(
                "Mitochondria",
                "the powerhouse of the cell",
                LocalDateTime.now(),
                1,
                0
        );
        Flashcard f2 = flashcardFactory.create(
                "A",
                "A",
                LocalDateTime.now(),
                2,
                0
        );
        Flashcard f3 = flashcardFactory.create(
                "B",
                "B",
                LocalDateTime.now(),
                3,
                0
        );
        Flashcard f4 = flashcardFactory.create(
                "C",
                "C",
                LocalDateTime.now(),
                4,
                0
        );
        Flashcard f5 = flashcardFactory.create(
                "Flashcard",
                "a card containing a small amount of information as an aid to learning",
                LocalDateTime.now(),
                5,
                0
        );
        List<Flashcard> flashcards = new ArrayList<Flashcard>();

        flashcards.add(f1);
        flashcards.add(f2);
        flashcards.add(f3);
        flashcards.add(f4);
        flashcards.add(f5);

        // quiz settings
        System.out.println("Enter the number of questions. This should be a value between 1 and "
                + flashcards.size() + ".");
        int num = Integer.parseInt(ui.nextLine());
        QuizSettings quizSettings = new QuizSettings(num);

        // quiz
        Quiz quiz = new Quiz(quizSettings, flashcards);
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        for (int i = 0; i < quizQuestions.size(); i++) {
            QuizQuestion quizQuestion = quizQuestions.get(i);
            System.out.println("Question #" + (i+1) + ":");
            System.out.println(quizQuestion);
            System.out.println("Enter your answer:");
            // for now, you have to type the actual answer out (or "true" or "false")
            String answer = ui.nextLine();
            quizQuestion.setUserAnswer(answer);
            System.out.println();
        }

        // results
        System.out.println("END OF QUIZ. Time to evaluate your results.");
        for (int i = 0; i < quizQuestions.size(); i++) {
            QuizQuestion quizQuestion = quizQuestions.get(i);
            System.out.println("Question #" + (i+1) + ":");
            if (quizQuestion.isCorrect()) {
                System.out.println("Correct!");
            } else {
                System.out.println("WRONG! The correct answer was: " + quizQuestion.getActualAnswer());
            }
            System.out.println();
        }
    }
}
