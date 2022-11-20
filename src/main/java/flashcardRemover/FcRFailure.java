package flashcardRemover;

public class FcRFailure extends RuntimeException{
    public FcRFailure(String error){
        super(error);
    }
}
