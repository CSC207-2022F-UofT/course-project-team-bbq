package entities;

import java.util.List;
import java.util.Random;

/**
 * TextEntryQuestion
 * @author Anthony
 */
public class TextEntryQuestion extends QuizQuestion {
    private String term = null;
    private String definition = null;
    private final boolean missingTerm;

    private static final Random rand = new Random();

    /**
     * Constructs a text entry question.
     * @param flashcards the list of flashcards
     * @param index the index of a specific flashcard
     */
    public TextEntryQuestion(List<Flashcard> flashcards, int index) {
        this.missingTerm = rand.nextBoolean();
        generateQuestion(flashcards, index);
    }

    /**
     * Generates a text entry question.
     * @param flashcards a list of flashcards
     * @param index      the index of a specific flashcard in which the question will be based on
     */
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

    /**
     * Returns the string representation of the text entry question.
     * @return string representation
     */
    @Override
    public String toString() {
        if (this.missingTerm) {
            return "Term: ???\nDefinition: " + this.definition;
        } else {
            return "Term: " + this.term + "\nDefinition: ???";
        }
    }

    /**
     * Gets the term.
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the term.
     * @param term the term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Gets the definition.
     * @return the definition.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Sets the definition.
     * @param definition the definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
