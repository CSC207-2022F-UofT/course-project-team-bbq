package create_flashcard_set_use_case;

import create_flashcard_set_use_case.function_testing.InMemoryFlashcardSet;
import data_access_use_case.IFlashcardSetDataAccess;
import entities.FlashcardSetFactory;
import interface_adapters.presenters.exceptions.CreateFlashcardSetFailed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        CreateFlashcardSetRequestModel requestModel = new CreateFlashcardSetRequestModel("CSC207",
                "Final Exam Review", false, "uncle_bob69");


        // Create the arguments for FlashcardSetInteractor

        // Argument 1: Repository
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();

        // Argument 2: Output Boundary
        // Creating an anonymous implementing class for the Output Boundary.
        CreateFlashcardSetOutputBoundary outputBoundary = new CreateFlashcardSetOutputBoundary() {
            @Override
            public CreateFlashcardSetResponseModel prepareSuccessView(CreateFlashcardSetResponseModel responseModel) {

                assertEquals("CSC207", responseModel.getFs().getTitle());
                assertEquals("CSC207", flashcardSetRepository.getFlashcardSet(-1).getTitle());
                return null;
            }

            @Override
            public CreateFlashcardSetResponseModel prepareFailView(String error) {
                fail("Something went wrong since we are testing for successful creation.");
                return null;
            }
        };

        // Argument 3: Factory
        FlashcardSetFactory flashcardSetFactory = new FlashcardSetFactory();

        // Construct interactor
        CreateFlashcardSetInputBoundary interactor = new CreateFlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactory);

        // Create and store the flashcard set with the interactor
        interactor.create(requestModel);

    }

    @Test
    public void testMissingTitleException() {
        // Create a mock user input with no title
        CreateFlashcardSetRequestModel requestModel = new CreateFlashcardSetRequestModel("",
                "Final Exam Review", false, "uncle_bob69");

        // Setup is same as testCreate, but we implement Output Boundary to throw an exception
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();
        CreateFlashcardSetOutputBoundary outputBoundary = new CreateFlashcardSetOutputBoundary() {
            @Override
            public CreateFlashcardSetResponseModel prepareSuccessView(CreateFlashcardSetResponseModel fs) {
                fail("Something went wrong since we are testing for failing creation.");
                return null;
            }

            @Override
            public CreateFlashcardSetResponseModel prepareFailView(String error) {
                throw new CreateFlashcardSetFailed("Title is missing!");
            }
        };
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();
        CreateFlashcardSetInputBoundary interactor = new CreateFlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Test part
        try {
            interactor.create(requestModel);  // no title should go immediately to catch block (i.e., pass test)
            assert (false);  // if title is included, then this line is reached and test fails
        } catch (CreateFlashcardSetFailed ignored) {

        }

    }

    @Test
    public void testMissingDescriptionException() {
        // Create a mock user input with no description
        CreateFlashcardSetRequestModel requestModel = new CreateFlashcardSetRequestModel("CSC207",
                "", false, "uncle_bob69");

        // Setup is same as testCreate, but we implement Output Boundary to throw an exception
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();
        CreateFlashcardSetOutputBoundary outputBoundary = new CreateFlashcardSetOutputBoundary() {
            @Override
            public CreateFlashcardSetResponseModel prepareSuccessView(CreateFlashcardSetResponseModel fs) {
                fail("Something went wrong since we are testing for failing creation.");
                return null;
            }

            @Override
            public CreateFlashcardSetResponseModel prepareFailView(String error) {
                throw new CreateFlashcardSetFailed("Description is missing!");
            }
        };
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();
        CreateFlashcardSetInputBoundary interactor = new CreateFlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Test part
        try {
            interactor.create(requestModel);
            assert (false);
        } catch (CreateFlashcardSetFailed ignored) {

        }

    }

    @Test
    public void testMissingUsernameException() {
        // Create a mock user input with no username
        CreateFlashcardSetRequestModel requestModel = new CreateFlashcardSetRequestModel("CSC207",
                "Final Exam Review", false, "");

        // Setup is same as testCreate, but we implement Output Boundary to throw an exception
        IFlashcardSetDataAccess flashcardSetRepository = new InMemoryFlashcardSet();
        CreateFlashcardSetOutputBoundary outputBoundary = new CreateFlashcardSetOutputBoundary() {
            @Override
            public CreateFlashcardSetResponseModel prepareSuccessView(CreateFlashcardSetResponseModel fs) {
                fail("Something went wrong since we are testing for failing creation.");
                return null;
            }

            @Override
            public CreateFlashcardSetResponseModel prepareFailView(String error) {
                throw new CreateFlashcardSetFailed("Username is missing!");
            }
        };
        FlashcardSetFactory flashcardSetFactoryfactory = new FlashcardSetFactory();
        CreateFlashcardSetInputBoundary interactor = new CreateFlashcardSetInteractor(flashcardSetRepository, outputBoundary,
                flashcardSetFactoryfactory);

        // Test part
        try {
            interactor.create(requestModel);
            assert (false);
        } catch (CreateFlashcardSetFailed ignored) {

        }

    }

}
