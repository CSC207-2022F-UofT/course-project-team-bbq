package studyMode;

import entities.Flashcard;
import entities.FlashcardSet;
import entities.FlashcardSetFactory;

import java.util.Comparator;
import java.util.List;

// entity layer

public class FlashcardStudier extends FlashcardSet {
    private List<Flashcard> flashcards;

    private final int numFlashcards;
    private boolean displayingTerm;

    private boolean termIsDefault;
    private int counter = 0;
    private Flashcard currFlashcard;

    public FlashcardStudier(String title, String description, boolean isPrivate,
                            int flashcardSetId, String ownerUsername, boolean termIsDefault){
        super(title, description, isPrivate, flashcardSetId, ownerUsername);

        this.flashcards = this.getFlashcards();
        this.displayingTerm = termIsDefault;
        this.termIsDefault = termIsDefault;

        this.numFlashcards = flashcards.size();
        this.currFlashcard = this.flashcards.get(0);
    }

    public String flipCard(){
        if (displayingTerm){
            displayingTerm = !displayingTerm;
            return currFlashcard.getDefinition();
        }
        else {
            displayingTerm = !displayingTerm;
            return currFlashcard.getTerm();
        }
    }

    public String getNextCard(){
        if (counter < numFlashcards - 1){
            counter += 1;
        }
        else {
            counter = 0;
        }
        currFlashcard = flashcards.get(counter);
        return this.getOutputText();
    }

    public String getPrevCard(){
        if (counter > 0){
            counter -= 1;
        }
        else {
            counter = numFlashcards - 1;
        }
        currFlashcard = flashcards.get(counter);
        return this.getOutputText();
    }

    public int getCounter() {
        return counter;
    }

    public String getOutputText() {
        if (termIsDefault) {
            displayingTerm = true;
            return currFlashcard.getTerm();
        }
        else {
            displayingTerm = false;
            return currFlashcard.getDefinition();
        }
    }

    public void sort(Comparator<Flashcard> c){
        this.flashcards.sort(c);
    }

    public int getNumFlashcards() {
        return numFlashcards;
    }
}
