package create_flashcard_set_use_case;

// Interface adapters (Green) layer

/**
 * The controller for flashcard set creation.
 *
 * @author Edward Ishii
 */
public class FlashcardSetController {

    FlashcardSetInputBoundary interactor;

    /**
     * Constructs a new FlashcardSetController.
     *
     * @param interactor the use case interactor for creating flashcard sets.
     */
    public FlashcardSetController(FlashcardSetInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Creates a new flashcard set and stores it into the database.
     *
     * @param title       the title of the flashcard set.
     * @param description the description of the flashcard set.
     * @param isPrivate   the privacy of the flashcard set.
     * @param username    the owner's username of the flashcard set.
     * @return the response model containing the newly created flashcard set data.
     */
    FlashcardSetResponseModel create(String title, String description, boolean isPrivate, String username) {
        FlashcardSetRequestModel requestModel = new FlashcardSetRequestModel(title, description,
                isPrivate, username);

        return interactor.create(requestModel);
    }
}
