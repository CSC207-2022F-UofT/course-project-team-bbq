package FlashcardRemover;

public class FcRFailure extends RuntimeException{
    public FcRFailure(String error){
        super(error);
    }
}
