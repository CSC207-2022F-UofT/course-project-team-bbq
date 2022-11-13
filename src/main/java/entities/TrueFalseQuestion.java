package entities;

import java.util.List;
import java.util.Random;

public class TrueFalseQuestion extends QuizQuestion {
    private String term;
    private String potentialDefinition; // this could be correct or incorrect
    private final boolean pranked;

    private static final Random rand = new Random();

    public TrueFalseQuestion(List<Flashcard> flashcards, int index) {
        this.pranked = rand.nextBoolean();
        generateQuestion(flashcards, index);
    }

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

    @Override
    public String toString() {
        return ("TRUE OR FALSE?\nTerm: " + this.term + "\nDefinition: " + this.potentialDefinition);
    }

    public static int randomExcluded(int max, int excluded) {
        int n = rand.nextInt(max - 1);
        if (n >= excluded) {
            n++;
        }
        return n;
    }
}
