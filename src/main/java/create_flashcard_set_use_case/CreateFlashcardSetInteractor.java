package create_flashcard_set_use_case;

import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import entities.FlashcardSet;
import entities.FlashcardSetFactory;
import interface_adapters.presenters.exceptions.CreateFlashcardSetFailed;

import java.util.ArrayList;
import java.util.Objects;

// Use case (Red) layer

/**
 * [Feature 3: Creating a Flashcard Set]
 * UI:
 * - user enters title and description of flashcard set
 * - user also enters their username (for ownerUsername parameter in data access request model)
 * - user clicks on button to indicate public or private
 * - user clicks on button to either create or discard the flashcard set
 * Use case:
 * - check that the flashcard set contains a title, description, and owner's username (if not, alert user)
 * - create and store newly created flashcard set
 * - notify successful creation of flashcard set to user
 *
 * @author Edward Ishii
 */
public class CreateFlashcardSetInteractor implements CreateFlashcardSetInputBoundary {
    DBGateway dbGateway;
    IFlashcardSetDataAccess flashcardSetDataAccess;  // for testing
    final CreateFlashcardSetOutputBoundary createFlashcardSetOutputBoundary;
    final FlashcardSetFactory flashcardSetFactory;

    /**
     * Constructs the use case interactor.
     *
     * @param dbGateway                  the database to store the flashcard set in.
     * @param createFlashcardSetOutputBoundary the output boundary for preparing success/fail view of saving flashcard sets.
     * @param flashcardSetFactory        the factory that creates flashcard sets.
     */
    public CreateFlashcardSetInteractor(DBGateway dbGateway, CreateFlashcardSetOutputBoundary createFlashcardSetOutputBoundary,
                                        FlashcardSetFactory flashcardSetFactory) {
        this.dbGateway = dbGateway;
        this.createFlashcardSetOutputBoundary = createFlashcardSetOutputBoundary;
        this.flashcardSetFactory = flashcardSetFactory;
    }

    /**
     * Alternative constructor for testing purposes.
     *
     * @param flashcardSetDataAccess     the flashcard set database.
     * @param createFlashcardSetOutputBoundary the output boundary for preparing success/fail view.
     * @param flashcardSetFactory        the factory creating flashcard sets.
     */
    public CreateFlashcardSetInteractor(IFlashcardSetDataAccess flashcardSetDataAccess,
                                        CreateFlashcardSetOutputBoundary createFlashcardSetOutputBoundary,
                                        FlashcardSetFactory flashcardSetFactory) {
        this.flashcardSetDataAccess = flashcardSetDataAccess;
        this.createFlashcardSetOutputBoundary = createFlashcardSetOutputBoundary;
        this.flashcardSetFactory = flashcardSetFactory;
    }

    /**
     * Creates a flashcard set and stores it into the database.
     *
     * @param inputData the request model containing data required for creating flashcard sets.
     * @return the response model containing the newly created flashcard set data.
     * @throws CreateFlashcardSetFailed the error thrown if flashcard set creation fails.
     */
    @Override
    public CreateFlashcardSetResponseModel create(CreateFlashcardSetRequestModel inputData) throws CreateFlashcardSetFailed {

        // I chose to throw an exception when user gives invalid input so that the UI layer or any other caller
        // can use try-catch block to handle when to alert the user that something went wrong during creation.

        // throw exception if title, description, or username is missing
        if (Objects.equals(inputData.getTitle(), "")) {
            return createFlashcardSetOutputBoundary.prepareFailView("Title is missing!");
        } else if (Objects.equals(inputData.getDescription(), "")) {
            return createFlashcardSetOutputBoundary.prepareFailView("Description is missing!");
        } else if (Objects.equals(inputData.getUsername(), "")) {
            return createFlashcardSetOutputBoundary.prepareFailView("Username is missing!");
        }

        int tempFlashcardSetId = -1;  // placeholder id

        // create the flashcard set
        FlashcardSetFactory factory = new FlashcardSetFactory();
        FlashcardSet fs = factory.create(inputData.getTitle(), inputData.getDescription(),
                inputData.isPrivate(), tempFlashcardSetId, inputData.getUsername());

        // store the flashcard set into database
        ArrayList<Integer> flashcardIds = new ArrayList<>();  // set starts with empty list of flashcard ids
        FlashcardSetDsRequestModel dsRequestModel = new FlashcardSetDsRequestModel(fs.getTitle(), fs.getDescription(),
                fs.getIsPrivate(), tempFlashcardSetId, fs.getOwnerUsername(), flashcardIds);

        try {
            int savedFlashcardSetId = dbGateway.saveFlashcardSet(dsRequestModel);

            // create the saved flashcard set with its proper id
            FlashcardSet savedFs = factory.create(inputData.getTitle(), inputData.getDescription(),
                    inputData.isPrivate(), savedFlashcardSetId, inputData.getUsername());

            CreateFlashcardSetResponseModel responseModel = new CreateFlashcardSetResponseModel(savedFs);

            return createFlashcardSetOutputBoundary.prepareSuccessView(responseModel);
        } catch (NullPointerException e) {
            return null;
        }

    }


}
