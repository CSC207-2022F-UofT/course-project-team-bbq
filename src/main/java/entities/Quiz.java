package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Main quiz class for the application's quiz mode.
 * @author Anthony
 */
public class Quiz {
    private final List<QuizQuestion> quizQuestions;
    private final QuizSettings quizSettings;
    private final List<Flashcard> flashcards;

    private int score;

    private static final Random rand = new Random(); // used for calculating random numbers

    /**
     * Precondition: flashcards contains at least 4 flashcards, and at least 1 question type is enabled in quizSettings.
     * @param quizSettings the quiz settings
     * @param flashcards the list of flashcards
     */
    public Quiz(QuizSettings quizSettings, List<Flashcard> flashcards) {
        this.quizQuestions = new ArrayList<>();
        this.quizSettings = quizSettings;
        this.flashcards = new ArrayList<>(flashcards); // simple copy of references
        this.score = 0;
        this.generateQuestions();
    }

    /**
     * Based on the quiz settings and list of flashcards, generates a list of questions of varying types.
     */
    public void generateQuestions() {
        int numQuestions = this.quizSettings.getNumQuestions();

        List<String> types = new ArrayList<>();
        if (this.quizSettings.isTrueFalseOn()) {
            types.add("TF");
        }
        if (this.quizSettings.isMultipleChoiceOn()) {
            types.add("MC");
        }
        if (this.quizSettings.isTextEntryOn()) {
            types.add("TE");
        }
        int numTypes = types.size();

        Collections.shuffle(this.flashcards);

        QuizQuestionFactory qqFactory = new QuizQuestionFactory();
        for (int i = 0; i < numQuestions; i++) {
            int randomNum = rand.nextInt(numTypes);
            String type = types.get(randomNum);
            QuizQuestion question = qqFactory.create(this.flashcards, i, type);
            this.quizQuestions.add(question);
        }
    }

    /**
     * Evaluates the quiz and updates the score value based on how many questions are correct.
     */
    public void evaluate() {
        this.score = 0;
        for (QuizQuestion q : this.quizQuestions) {
            if (q.isCorrect()) {
                score++;
            }
        }
    }

    /**
     * Sets the user answers.
     * @param userAnswers the user answers
     */
    public void setUserAnswers(List<String> userAnswers) {
        for (int i = 0; i < userAnswers.size(); i++) {
            QuizQuestion q = this.quizQuestions.get(i);
            String a = userAnswers.get(i);
            q.setUserAnswer(a);
        }
    }

    /**
     * Gets the actual answers.
     * @return the actual answers
     */
    public List<String> getActualAnswers() {
        List<String> actualAnswers = new ArrayList<>();
        for (QuizQuestion q : this.quizQuestions) {
            actualAnswers.add(q.getActualAnswer());
        }
        return actualAnswers;
    }

    /**
     * Gets the quiz questions.
     * @return the quiz questions
     */
    public List<QuizQuestion> getQuizQuestions() {
        return this.quizQuestions;
    }

    /**
     * Gets the quiz score.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the number of questions.
     * @return the number of questions
     */
    public int getNumQuestions() {
        return this.quizQuestions.size();
    }
}
