package create_flashcard_use_case.fcCScreens;
/**
 * Failure of flashcard creator.
 * Interface Adaptors.
 * @author Junyu Chen
 */
public class FcCFailure extends RuntimeException{
    public FcCFailure(String error){
        super(error);
    }
}
