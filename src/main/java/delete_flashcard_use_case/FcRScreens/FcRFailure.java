package delete_flashcard_use_case.FcRScreens;
/**
 * Failure of flashcard remover.
 * Interface Adaptors.
 * @author Junyu Chen
 */
public class FcRFailure extends RuntimeException{
    public FcRFailure(String error){
        super(error);
    }
}
