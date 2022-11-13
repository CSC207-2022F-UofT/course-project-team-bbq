package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quiz {
    /*
    * Assumption: There are at least 4 flashcards. At least 1 question type is enabled.
    * */
    private List<QuizQuestion> quizQuestions;
    private QuizSettings quizSettings;
    private List<Flashcard> flashcards;
    private static Random rand = new Random();

    public Quiz(QuizSettings quizSettings, List<Flashcard> flashcards) {
        this.quizQuestions = new ArrayList<QuizQuestion>();
        this.quizSettings = quizSettings;
        this.flashcards = new ArrayList<Flashcard>(flashcards); // simple copy of references
        this.generateQuestions();
    }

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

    public List<QuizQuestion> getQuizQuestions() {
        return this.quizQuestions;
    }
}
