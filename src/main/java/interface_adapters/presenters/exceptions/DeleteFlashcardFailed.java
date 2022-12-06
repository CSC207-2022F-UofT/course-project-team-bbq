package interface_adapters.presenters.exceptions;
/**
 * Failure of flashcard removal.
 * Interface Adaptors.
 * @author Junyu Chen
 */
public class DeleteFlashcardFailed extends RuntimeException{
    public DeleteFlashcardFailed(String error){
        super(error);
    }
}
