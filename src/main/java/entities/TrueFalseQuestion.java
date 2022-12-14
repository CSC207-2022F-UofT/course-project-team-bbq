package entities;

import java.util.List;
import java.util.Random;

/**
 * TrueFalseQuestion
 * @author Anthony
 */
public class TrueFalseQuestion extends QuizQuestion {
    private String term;
    private String potentialDefinition; // this could be correct or incorrect
    private final boolean pranked;

    private static final Random rand = new Random();

    /**
     * Constructs a true/false question.
     * @param flashcards the list of flashcards
     * @param index the index of a specific flashcard
     */
    public TrueFalseQuestion(List<Flashcard> flashcards, int index) {
        this.pranked = rand.nextBoolean();
        generateQuestion(flashcards, index);
    }

    /**
     * Generates a true/false question.
     * @param flashcards a list of flashcards
     * @param index      the index of a specific flashcard in which the question will be based on
     */
    @Override
    public void generateQuestion(List<Flashcard> flashcards, int index) {
        Flashcard flashcard = flashcards.get(index);

        this.term = flashcard.getTerm();
        this.setActualAnswer(Boolean.toString((!pranked)));

        if (pranked) {
            // take a random flashcard definition from flashcard set
            int newIndex = randomExcluded(flashcards.size(), index);
            flashcard = flashcards.get(newIndex);
        }

        this.potentialDefinition = flashcard.getDefinition();
    }

    /**
     * Returns the string representation of the true/false question.
     * @return string representation
     */
    @Override
    public String toString() {
        return ("TRUE OR FALSE?\nTerm: " + this.term + "\nDefinition: " + this.potentialDefinition);
    }

    /**
     * Returns a random integer less than "max" that is not equal to "excluded".
     * @param max the maximum value
     * @param excluded some integer to be excluded
     * @return a random integer that is not equal to excluded
     */
    public static int randomExcluded(int max, int excluded) {
        int n = rand.nextInt(max - 1);
        if (n >= excluded) {
            n++;
        }
        return n;
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
     * Gets the potential definition.
     * @return the potential definition
     */
    public String getPotentialDefinition() {
        return potentialDefinition;
    }
}
