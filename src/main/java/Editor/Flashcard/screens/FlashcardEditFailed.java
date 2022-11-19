package Editor.Flashcard.screens;

public class FlashcardEditFailed extends RuntimeException{
    public FlashcardEditFailed(String error){
        super(error);
    }
}