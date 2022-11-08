package entities.quiz;

import java.util.List;
import java.util.Random;

import entities.flashcard.Flashcard;
import entities.flashcard.FlashcardSet;

public class TrueFalseQuestion extends QuizQuestion {
    private String definition;
    private final boolean pranked;
    private static final Random rand = new Random();

    public TrueFalseQuestion(FlashcardSet flashcardSet, int index) {
        super(flashcardSet, index);
        this.pranked = rand.nextBoolean();
    }

    public void generateQuestion(FlashcardSet flashcardSet, int index){
        List<Flashcard> flashcards = flashcardSet.getFlashcards();
        Flashcard flashcard = flashcards.get(index);

        this.setQuestion(flashcard.getTerm());
        this.setActualAnswer(Boolean.toString((!pranked)));

        if (pranked) {
            // take a random flashcard definition from flashcard set
            int newIndex = randomExcluded(flashcards.size(), index);
            flashcard = flashcards.get(newIndex);
        }

        this.definition = flashcard.getDefinition();
    }

    public static int randomExcluded(int max, int excluded) {
        int n = rand.nextInt(max-1);
        if (n >= excluded) {
            n++;
        }
        return n;
    }
}
