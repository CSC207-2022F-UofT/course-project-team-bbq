package entities;

import java.util.List;

/**
 * Quiz question factory that produces quiz questions.
 * @author Anthony
 */
public class QuizQuestionFactory {
    /**
     * Creates a quiz question based on the flashcard located at index of flashcards.
     * @param flashcards the list of flashcards
     * @param index the index of a specific flashcard
     * @param type the type of question to generate
     * @return a quiz question
     */
    public QuizQuestion create(List<Flashcard> flashcards, int index, String type) {
        switch (type) {
            case "TF":
                return new TrueFalseQuestion(flashcards, index);
            case "MC":
                return new MultipleChoiceQuestion(flashcards, index);
            case "TE":
                return new TextEntryQuestion(flashcards, index);
            default:
                throw new IllegalArgumentException("Invalid type.");
        }
    }
}
