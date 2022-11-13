package entities.quiz;

import entities.flashcard.Flashcard;

import java.util.List;

public class QuizQuestionFactory {
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
