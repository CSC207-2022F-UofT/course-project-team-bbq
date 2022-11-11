package studyMode;

import entities.Flashcard;
import entities.FlashcardSet;
import java.util.List;

// entity layer

public class FlashcardStudier {
    private List<Flashcard> flashcards;

    private int numFlashcards;
    private boolean displayingTerm;

    private boolean termIsDefault;
    private int counter = 0;
    private Flashcard currFlashcard;

    public FlashcardStudier(FlashcardSet flashcardSet, boolean termIsDefault){
        this.flashcards = flashcardSet.getFlashcards();
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

    private String getOutputText() {
        if (termIsDefault) {
            displayingTerm = true;
            return currFlashcard.getTerm();
        }
        else {
            displayingTerm = false;
            return currFlashcard.getDefinition();
        }
    }
}
