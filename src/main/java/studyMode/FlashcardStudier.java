package studyMode;

import entities.Flashcard;
import entities.FlashcardSet;
import java.util.List;

// entity layer

public class FlashcardStudier {
    private List<Flashcard> flashcards;

    private int numFlashcards;
    private boolean displayingTerm;
    private int counter = 0;
    private Flashcard currFlashcard;

    public FlashcardStudier(FlashcardSet flashcardSet, boolean defaultDisplay){
        this.flashcards = flashcardSet.getFlashcards();
        this.displayingTerm = defaultDisplay;

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

    private String getOutputText() {
        if (displayingTerm){
            return currFlashcard.getTerm();
        }
        else {
            return currFlashcard.getDefinition();
        }
    }
}
