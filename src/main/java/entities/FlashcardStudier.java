package entities;

import entities.Flashcard;
import entities.FlashcardSet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// entity layer

public class FlashcardStudier extends FlashcardSet {
    private final List<Flashcard> flashcards;

    private int numFlashcards;
    private boolean displayingTerm;

    private final boolean termIsDefault;
    private int counter = 0;
    private Flashcard currFlashcard;

    public FlashcardStudier(String title, String description, boolean isPrivate,
                            int flashcardSetId, String ownerUsername, boolean termIsDefault){
        super(title, description, isPrivate, flashcardSetId, ownerUsername);

        this.flashcards = this.getFlashcards();
        this.displayingTerm = termIsDefault;
        this.termIsDefault = termIsDefault;
    }

    /**
     * @return the flashcard's definition if displayingTerm is true, and
     * the flashcard's term if displayingTerm is false. Negates displayingTerm.
     */
    public String flipCard(){
        if (displayingTerm){
            displayingTerm = false;
            return currFlashcard.getDefinition();
        }
        else {
            displayingTerm = true;
            return currFlashcard.getTerm();
        }
    }

    /**
     * Sets currFlashcard to the next flashcard in flashcards. If currFlashcard is the
     * last flashcard, sets currFlashcard to the zeroth flashcard in flashcards.
     * @return the output text for the new currFlashcard as defined by getOutputText
     */
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

    /**
     * Sets currFlashcard to the previous flashcard in flashcards. If currFlashcard is the
     * zeroth flashcard, sets currFlashcard to the last flashcard in flashcards.
     * @return the output text for the new currFlashcard as defined by getOutputText
     */
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

    /**
     * @return the term of currFlashcard if termIsDefault and set displayingTerm to be true.
     * Otherwise, return the definition of currFlashcard and set displayingTerm to be false.
     */
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

    /**
     * @param c a flashcard comparator class
     * Sorts flashcards by the rule defined by c and sets currFlashcard to the
     * zeroth flashcard
     */
    public void sort(Comparator<Flashcard> c){
        this.flashcards.sort(c);
        this.resetCurrFlashcard();
    }

    /**
     * @param c a flashcard comparator class
     * Sorts flashcards by the rule defined by c and reverses flashcards
     * sets currFlashcard to the zeroth flashcard
     */
    public void reverse(Comparator<Flashcard> c){
        this.sort(c);
        Collections.reverse(this.flashcards);
        this.resetCurrFlashcard();
    }

    /**
     * Randomly shuffles flashcards and sets currFlashcard to the
     * zeroth flashcard
     */
    public void shuffle(){
        Collections.shuffle(this.flashcards);
        this.resetCurrFlashcard();
    }

    public int getNumFlashcards() {
        return numFlashcards;
    }

    /**
     * Sets currflashcard to the zeroth flashcard in flashcards
     */
    public void resetCurrFlashcard() {
        currFlashcard = flashcards.get(0);
    }

    /**
     * sets numFlashcards to be the size of flashcards
     */
    public void setNumFlashcards() {
        numFlashcards = flashcards.size();
    }
}
