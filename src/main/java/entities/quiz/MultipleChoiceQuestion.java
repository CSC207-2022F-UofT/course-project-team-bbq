package entities.quiz;

import entities.flashcard.Flashcard;
import entities.flashcard.FlashcardSet;

public class MultipleChoiceQuestion extends QuizQuestion {
    public MultipleChoiceQuestion(FlashcardSet flashcardSet, int index) {
        super(flashcardSet, index);
    }
    @Override
    public void generateQuestion(FlashcardSet flashcardSet, int index) {

    }

    public boolean isCorrect() {
        return false;
    }
}
