package delete_flashcard_set_use_case;


import create_flashcard_set_use_case.function_testing.InMemoryFlashcardSet;
import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import delete_flashcard_set_use_case.function_testing.InMemoryFlashcard;
import interface_adapters.presenters.DeleteFlashcardSetPresenter;
import interface_adapters.presenters.exceptions.DeleteFlashcardSetFailed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * [Feature: Deleting a Flashcard Set]
 * UI:
 * - user provides the ID of the flashcard set to be deleted
 * - user is alerted if the specified flashcard set doesn't exist
 * - if the set exists, then user clicks on button to confirm deletion
 * User case:
 * - check that the flashcard set exists in the database (if not alert the user)
 * - if it exists, ask for confirmation to delete from the user
 * - when a flashcard set is deleted, all flashcards contained within are deleted
 * - notify successful deletion of flashcard set to the user
 *
 * @author Edward Ishii
 */
public class DeleteFlashcardSetInteractorTest {

    IFlashcardSetDataAccess flashcardSetRepo;
    IFlashcardDataAccess flashcardRepo;

    /**
     * The setup for testing deletion, i.e., create and populate mock flashcard set and flashcard databases.
     */
    @BeforeEach
    public void setup() {
        // Create a mock flashcard set repository
        flashcardSetRepo = new InMemoryFlashcardSet();

        // Create a mock flashcard repository
        flashcardRepo = new InMemoryFlashcard();

        // Populate flashcardRepo
        FlashcardDsRequestModel fc1 = new FlashcardDsRequestModel("SOLID", "Single responsibility, " +
                "Open/closed, Liskov substitution, Interface segragation, Dependency inversion", LocalDateTime.now(),
                10, 1);

        FlashcardDsRequestModel fc2 = new FlashcardDsRequestModel("Bloaters", "too much code",
                LocalDateTime.now(), 20, 1);

        FlashcardDsRequestModel fc3 = new FlashcardDsRequestModel("Language",
                "any set of strings", LocalDateTime.now(), 30, 2);

        flashcardRepo.saveFlashcard(fc1);
        flashcardRepo.saveFlashcard(fc2);
        flashcardRepo.saveFlashcard(fc3);

        // Create empty lists of flashcard ids for the flashcard sets and add the flashcard ids
        List<Integer> flashcardIds1 = new ArrayList<>();
        flashcardIds1.add(fc1.getFlashcardId());
        flashcardIds1.add(fc2.getFlashcardId());
        List<Integer> flashcardIds2 = new ArrayList<>();
        flashcardIds2.add(fc3.getFlashcardId());


        // Create some flashcard sets to put into repo
        FlashcardSetDsRequestModel fs1 = new FlashcardSetDsRequestModel("CSC207", "Final Exam Review",
                false, 1, "uncle_bob69", flashcardIds1);
        FlashcardSetDsRequestModel fs2 = new FlashcardSetDsRequestModel("CSC236",
                "Definitions and Theorems", true, 2, "user123", flashcardIds2);

        // Populate repository
        flashcardSetRepo.saveFlashcardSet(fs1);
        flashcardSetRepo.saveFlashcardSet(fs2);

        // Check that the mock repositories are properly intialized
        Assertions.assertEquals("CSC207", flashcardSetRepo.getFlashcardSet(1).getTitle());
        Assertions.assertEquals("CSC236", flashcardSetRepo.getFlashcardSet(2).getTitle());
        Assertions.assertEquals("SOLID", flashcardRepo.getFlashcard(10).getTerm());
        Assertions.assertEquals("Bloaters", flashcardRepo.getFlashcard(20).getTerm());
        Assertions.assertEquals(2, flashcardSetRepo.getFlashcardSet(1).getFlashcardIds().size());
        Assertions.assertEquals(1, flashcardSetRepo.getFlashcardSet(2).getFlashcardIds().size());
    }

    /**
     * Test that when a flashcard set is deleted, the set is deleted from the flashcard set database, AND all
     * the associated flashcards within that set is also deleted from the flashcard database.
     */
    @Test
    public void testDelete() {
        // Create mock input
        DeleteFlashcardSetRequestModel inputData = new DeleteFlashcardSetRequestModel(1);

        // Create the arguments for the interactor
        DeleteFlashcardSetOutputBoundary presenter = new DeleteFlashcardSetPresenter();
        DeleteFlashcardSetInputBoundary interactor = requestModel -> {
            if (flashcardSetRepo.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
                return presenter.prepareFailView("Flashcard set #"
                        + requestModel.getFlashcardSetId() + " doesn't exist.");
            } else {
                // First delete all the flashcards associated with this flashcard set in Flashcards.csv
                for (int id : flashcardSetRepo.getFlashcardSet(requestModel.getFlashcardSetId()).getFlashcardIds()) {
                    flashcardRepo.deleteFlashcard(id);
                }
                // Then delete the flashcard set in FlashcardSets.csv
                flashcardSetRepo.deleteFlashcardSet(requestModel.getFlashcardSetId());

                return presenter.prepareSuccessView("Flashcard set #" + requestModel.getFlashcardSetId()
                        + " has been deleted.");
            }
        };

        // Use the interactor to delete fs1
        interactor.delete(inputData);

        // Check that fs1 was successfully deleted and fs2 is still in the database
        Assertions.assertNull(flashcardSetRepo.getFlashcardSet(1));
        Assertions.assertNotNull(flashcardSetRepo.getFlashcardSet(2));

        // Check that all the flashcards in fs1 (fc1 and fc2) were deleted, but not fc3
        Assertions.assertNull(flashcardRepo.getFlashcard(10));
        Assertions.assertNull(flashcardRepo.getFlashcard(20));
        Assertions.assertEquals("Language", flashcardRepo.getFlashcard(30).getTerm());
    }

    /**
     * Test that the proper exception is thrown when the id of the flashcard set to be deleted does not exist
     * within the database.
     */
    @Test
    public void testDeleteOnNonExistentFlashcardSet() {

        // Mock input with id of a flashcard set that doesn't exist
        DeleteFlashcardSetRequestModel inputData = new DeleteFlashcardSetRequestModel(3);


        // Implement output boundaries to throw an exception
//        DelFlashcardSetOutputBoundary outputBoundary = new DelFlashcardSetOutputBoundary() {
//            @Override
//            public DelFlashcardSetResponseModel prepareSuccessView(String message) {
//                fail("Something went wrong since we are testing for failing deletion.");
//                return null;
//            }
//
//            @Override
//            public DelFlashcardSetResponseModel prepareFailView(String error) throws FlashcardSetNotFound {
//                throw new FlashcardSetNotFound("Flashcard set #" + inputData.getFlashcardSetId()
//                        + "  does not exist.");
//            }
//        };

        DeleteFlashcardSetOutputBoundary presenter = new DeleteFlashcardSetPresenter();

        // Construct the interactor
        DeleteFlashcardSetInputBoundary interactor = requestModel -> {
            if (flashcardSetRepo.getFlashcardSet(requestModel.getFlashcardSetId()) == null) {
                return presenter.prepareFailView("Flashcard set #"
                        + requestModel.getFlashcardSetId() + " doesn't exist.");
            } else {
                // First delete all the flashcards associated with this flashcard set in Flashcards.csv
                for (int id : flashcardSetRepo.getFlashcardSet(requestModel.getFlashcardSetId()).getFlashcardIds()) {
                    flashcardRepo.deleteFlashcard(id);
                }
                // Then delete the flashcard set in FlashcardSets.csv
                flashcardSetRepo.deleteFlashcardSet(requestModel.getFlashcardSetId());

                return presenter.prepareSuccessView("Flashcard set #" + requestModel.getFlashcardSetId()
                        + " has been deleted.");
            }
        };


        // Test part
        try {
            interactor.delete(inputData);  // invalid id should go immediately to catch block (i.e., pass test)
            assert (false);  // if id exists, then this line is reached and test fails
        } catch (DeleteFlashcardSetFailed ignored) {

        }
    }
}
