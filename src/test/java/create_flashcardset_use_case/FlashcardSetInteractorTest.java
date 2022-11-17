package create_flashcardset_use_case;

import dataAccess.IFlashcardSetDataAccess;
import entities.FlashcardSetFactory;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class FlashcardSetInteractorTest {

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
     */

    @Test
    public void testCreate() {
        // Create a mock user input
        FlashcardSetRequestModel requestModel = new FlashcardSetRequestModel("CSC207",
                "Final Exam Review", false, "uncle_bob69", "create");


        // Create the arguments for FlashcardSetInteractor

        // Argument 1: Repository
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();

        // Argument 2: Output Boundary
        // Creating an anonymous implementing class for the Output Boundary.
        FlashcardSetOutputBoundary outputBoundary = new FlashcardSetOutputBoundary() {
            @Override
            public FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel responseModel) {

                assertEquals("CSC207", responseModel.getFs().getTitle());
                assertEquals("CSC207", flashcardSetRepository.getFlashcardSet(0).getTitle());
                return null;
            }

            @Override
            public FlashcardSetResponseModel prepareFailView(String error) {
                fail("Something went wrong since we are testing for successful creation.");
                return null;
            }
        };

        // Argument 3: Factory
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();

        // Construct interactor
        FlashcardSetInputBoundary interactor = new FlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Create and store the flashcard set with the interactor
        interactor.create(requestModel);

    }

    @Test
    public void testMissingTitleException() throws FlashcardSetCreationFailed {
        // Create a mock user input with no title
        FlashcardSetRequestModel requestModel = new FlashcardSetRequestModel("",
                "Final Exam Review", false, "uncle_bob69", "create");

        // Setup is same as testCreate, but we implement Output Boundary to throw an exception
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();
        FlashcardSetOutputBoundary outputBoundary = new FlashcardSetOutputBoundary() {
            @Override
            public FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs) {
                fail("Something went wrong since we are testing for failing creation.");
                return null;
            }
            @Override
            public FlashcardSetResponseModel prepareFailView(String error) {
                throw new FlashcardSetCreationFailed("Title is missing!");
            }
        };
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();
        FlashcardSetInputBoundary interactor = new FlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Test part
        try {
            interactor.create(requestModel);  // no title should go immediately to catch block (i.e., pass test)
            assert(false);  // if title is included, then this line is reached and test fails
        } catch (FlashcardSetCreationFailed e) {

        }

    }

    @Test
    public void testMissingDescriptionException() throws FlashcardSetCreationFailed {
        // Create a mock user input with no description
        FlashcardSetRequestModel requestModel = new FlashcardSetRequestModel("CSC207",
                "", false, "uncle_bob69", "create");

        // Setup is same as testCreate, but we implement Output Boundary to throw an exception
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();
        FlashcardSetOutputBoundary outputBoundary = new FlashcardSetOutputBoundary() {
            @Override
            public FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs) {
                fail("Something went wrong since we are testing for failing creation.");
                return null;
            }
            @Override
            public FlashcardSetResponseModel prepareFailView(String error) {
                throw new FlashcardSetCreationFailed("Description is missing!");
            }
        };
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();
        FlashcardSetInputBoundary interactor = new FlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Test part
        try {
            interactor.create(requestModel);
            assert(false);
        } catch (FlashcardSetCreationFailed e) {

        }

    }

    @Test
    public void testMissingUsernameException() throws FlashcardSetCreationFailed {
        // Create a mock user input with no username
        FlashcardSetRequestModel requestModel = new FlashcardSetRequestModel("CSC207",
                "Final Exam Review", false, "", "create");

        // Setup is same as testCreate, but we implement Output Boundary to throw an exception
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();
        FlashcardSetOutputBoundary outputBoundary = new FlashcardSetOutputBoundary() {
            @Override
            public FlashcardSetResponseModel prepareSuccessView(FlashcardSetResponseModel fs) {
                fail("Something went wrong since we are testing for failing creation.");
                return null;
            }
            @Override
            public FlashcardSetResponseModel prepareFailView(String error) {
                throw new FlashcardSetCreationFailed("Username is missing!");
            }
        };
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();
        FlashcardSetInputBoundary interactor = new FlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Test part
        try {
            interactor.create(requestModel);
            assert(false);
        } catch (FlashcardSetCreationFailed e) {

        }

    }

}
