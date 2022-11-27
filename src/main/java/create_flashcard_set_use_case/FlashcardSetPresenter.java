package create_flashcard_set_use_case;

// Interface adapters (Green) layer

public class FlashcardSetPresenter implements FlashcardSetOutputBoundary {
    @Override
    public FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs) {
        return fs;
    }

    @Override
    public FlashcardSetResponseModel prepareFailView(String error) {
        throw new FlashcardSetCreationFailed(error);
    }
}
