package interface_adapters.controllers;

// Interface adapters (Green) layer

import create_flashcard_set_use_case.CreateFlashcardSetInputBoundary;
import create_flashcard_set_use_case.CreateFlashcardSetRequestModel;
import create_flashcard_set_use_case.CreateFlashcardSetResponseModel;

/**
 * The controller for flashcard set creation.
 *
 * @author Edward Ishii
 */
public class CreateFlashcardSetController {

    CreateFlashcardSetInputBoundary interactor;

    /**
     * Constructs a new FlashcardSetController.
     *
     * @param interactor the use case interactor for creating flashcard sets.
     */
    public CreateFlashcardSetController(CreateFlashcardSetInputBoundary interactor) {
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
    public CreateFlashcardSetResponseModel create(String title, String description, boolean isPrivate, String username) {
        CreateFlashcardSetRequestModel requestModel = new CreateFlashcardSetRequestModel(title, description,
                isPrivate, username);

        return interactor.create(requestModel);
    }
}
