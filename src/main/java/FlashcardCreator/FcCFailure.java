package FlashcardCreator;

public class FcCFailure extends RuntimeException{
    public FcCFailure(String error){
        super(error);
    }
}
