package entities.quiz;

import entities.flashcard.FlashcardSet;

public class QuizQuestionFactory {
    public QuizQuestion createQuizQuestion(FlashcardSet flashcardSet, int index, String type) {
        switch (type) {
            case "MC":
                return new MultipleChoiceQuestion(flashcardSet, index);
            case "TE":
                return new TextEntryQuestion(flashcardSet, index);
            case "TF":
                return new TrueFalseQuestion(flashcardSet, index);
            default:
                throw new IllegalArgumentException("Invalid type.");
        }
    }
}
