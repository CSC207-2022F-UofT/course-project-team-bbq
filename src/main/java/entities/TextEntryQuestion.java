package entities;

import java.util.List;
import java.util.Random;

public class TextEntryQuestion extends QuizQuestion {
    private String term = null;
    private String definition = null;
    private final boolean missingTerm;

    private static final Random rand = new Random();

    public TextEntryQuestion(List<Flashcard> flashcards, int index) {
        this.missingTerm = rand.nextBoolean();
        generateQuestion(flashcards, index);
    }

    @Override
    public void generateQuestion(List<Flashcard> flashcards, int index) {
        Flashcard flashcard = flashcards.get(index);
        if (missingTerm) {
            this.definition = flashcard.getDefinition();
            this.setActualAnswer(flashcard.getTerm());
        } else {
            this.term = flashcard.getTerm();
            this.setActualAnswer(flashcard.getDefinition());
        }
    }

    @Override
    public String toString() {
        if (this.missingTerm) {
            return "Term: ???\nDefinition: " + this.definition;
        } else {
            return "Term: " + this.term + "\nDefinition: ???";
        }
    }
}
