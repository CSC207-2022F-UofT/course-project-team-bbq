package delete_flashcardset_use_case;

// Interface adapters (Green) layer

public class DelFlashcardSetPresenter implements DelFlashcardSetOutputBoundary {

    @Override
    public DelFlashcardSetResponseModel prepareSuccessView(String message) {
        return new DelFlashcardSetResponseModel(message);
    }

    @Override
    public DelFlashcardSetResponseModel prepareFailView(String error) {
        throw new FlashcardSetNotFound(error);
    }
}
