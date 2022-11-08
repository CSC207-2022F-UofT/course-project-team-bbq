package entities.quiz;

import entities.flashcard.Flashcard;
import entities.flashcard.FlashcardSet;

public class QuizQuestionFactory {
    public QuizQuestion createQuizQuestion(FlashcardSet flashcardSet, int index, String type) {
        return switch (type) {
            case "MC" -> new MultipleChoiceQuestion(flashcardSet, index);
            case "TE" -> new TextEntryQuestion(flashcardSet, index);
            case "TF" -> new TrueFalseQuestion(flashcardSet, index);
            default -> throw new IllegalArgumentException("??? what u doing");
        };
    }
}
