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

    private static final Random rand = new Random();

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

        ArrayList<String> types = new ArrayList<String>();
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

    public void evaluate() {
        for (QuizQuestion q : this.quizQuestions) {
            if (q.isCorrect()) {
                score++;
            }
        }
    }

    /** GETTERS AND SETTERS **/
    public List<QuizQuestion> getQuizQuestions() {
        return this.quizQuestions;
    }

    public int getScore() {
        return this.score;
    }

    public int getNumQuestions() {
        return this.quizQuestions.size();
    }
}
