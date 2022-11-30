package interface_adapters.presenters.exceptions;
/**
 * Failure of flashcard creation.
 * Interface Adaptors.
 * @author Junyu Chen
 */
public class CreateFlashcardFailed extends RuntimeException{
    public CreateFlashcardFailed(String error){
        super(error);
    }
}
