package delete_flashcardset_use_case;


import create_flashcardset_use_case.InMemoryFlashcardSet;
import dataAccess.IFlashcardSetDataAccess;
import entityRequestModels.FlashcardSetDsRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * [Feature: Deleting a Flashcard Set]
 * UI:
 * - user provides the ID of the flashcard set to be deleted
 * - user is alerted if the specified flashcard set doesn't exist
 * - if the set exists, then user clicks on button to confirm deletion
 * User case:
 * - check that the flashcard set exists in the database (if not alert the user)
 * - if it exists, ask for confirmation to delete from the user
 * - notify successful deletion of flashcard set to the user
 */

public class DelFlashcardSetInteractorTest {

    IFlashcardSetDataAccess flashcardSetRepo;

    @BeforeEach
    public void setup() {
        // Create a mock flashcard set repository
        flashcardSetRepo = new InMemoryFlashcardSet();

        // Create empty list of flashcard ids for flashcard set
        List<Integer> flashcardIds = new ArrayList<>();

        // Create some flashcard sets to put into repo
        FlashcardSetDsRequestModel fs1 = new FlashcardSetDsRequestModel("CSC207", "Final Exam Review",
                false, 1, "uncle_bob69", flashcardIds);
        FlashcardSetDsRequestModel fs2 = new FlashcardSetDsRequestModel("CSC236",
                "Definitions and Theorems", true, 2, "user123", flashcardIds);

        // Populate repository
        flashcardSetRepo.saveFlashcardSet(fs1);
        flashcardSetRepo.saveFlashcardSet(fs2);

        Assertions.assertEquals("CSC207", flashcardSetRepo.getFlashcardSet(1).getTitle());
        Assertions.assertEquals("CSC236", flashcardSetRepo.getFlashcardSet(2).getTitle());

    }

    @Test
    public void testDelete() {
        // Create mock input
        DelFlashcardSetRequestModel inputData = new DelFlashcardSetRequestModel(1);

        // Create the arguments for the interactor
        DelFlashcardSetOutputBoundary presenter = new DelFlashcardSetPresenter();
        DelFlashcardSetInputBoundary interactor = new DelFlashcardSetInteractor(flashcardSetRepo, presenter);

        // Use the interactor to delete fs1
        interactor.delete(inputData);

        // Check that fs1 was successfully deleted and fs2 is still in the database
        Assertions.assertNull(flashcardSetRepo.getFlashcardSet(1));
        Assertions.assertNotNull(flashcardSetRepo.getFlashcardSet(2));
    }

    @Test
    public void testDeleteOnNonExistentFlashcardSet() throws FlashcardSetNotFound {

        // Mock input with id of a flashcard set that doesn't exist
        DelFlashcardSetRequestModel inputData = new DelFlashcardSetRequestModel(3);


        // Implement output boundaries to throw an exception
        DelFlashcardSetOutputBoundary outputBoundary = new DelFlashcardSetOutputBoundary() {
            @Override
            public DelFlashcardSetResponseModel prepareSuccessView(String message) {
                fail("Something went wrong since we are testing for failing deletion.");
                return null;
            }

            @Override
            public DelFlashcardSetResponseModel prepareFailView(String error) throws FlashcardSetNotFound {
                throw new FlashcardSetNotFound("Flashcard set #" + inputData.getFlashcardSetId()
                        + "  does not exist.");
            }
        };

        // Construct the interactor
        DelFlashcardSetInputBoundary interactor = new DelFlashcardSetInteractor(flashcardSetRepo, outputBoundary);


        // Test part
        try {
            interactor.delete(inputData);
            assert(false);
        } catch (FlashcardSetNotFound e) {

        }
    }
}
