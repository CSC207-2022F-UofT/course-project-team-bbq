package study_mode_use_case;
import data_access_use_case.*;
import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import interface_adapters.controllers.StudySessionController;
import interface_adapters.presenters.StudySessionPresenter;
import org.junit.jupiter.api.Test;
import interface_adapters.presenters.exceptions.StudySettingsFailed;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StudySessionUseCaseUnitTest {

    /**
     * the id of the study test set in the test database
     */
    final int testSetId = 0;

    /**
     * the id of the empty study test set in the test database
     */
    final int emptyTestSetId = 1;

    /**
     * a request model corresponding to a user who wants to view the next flashcard
     */
    final StudySessionRequestModel next = new StudySessionRequestModel();

    /**
     * a request model corresponding to a user who wants to view the previous flashcard
     */
    final StudySessionRequestModel prev = new StudySessionRequestModel();

    /**
     * a request model corresponding to a user who wants to flip the current flashcard
     */
    final StudySessionRequestModel flip = new StudySessionRequestModel();

    /**
     * the controller for the study mode use case
     */
    StudySessionController controller;

    /**
     * a function which sets up the test database, test request models, and controller
     * @param flashcardSetId the id of the flashcard set to be studied
     * @param sortingOrder a string specifying the order the user wants to sort the flashcard set
     * @param termIsDefault a boolean specifying if term or definition is displayed by default
     * @param isReverse a boolean specifying if the set is sorted in order or in reverse
     * @return a StudySettingsResponseModel corresponding to the flashcard set to be studied
     * @throws IOException if there is unexpected database behaviour
     */
    StudySettingsRequestModel setup(int flashcardSetId, String sortingOrder,
                                     boolean termIsDefault, boolean isReverse) throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(
                "src/test/java/study_mode_use_case/test_data/Flashcards.csv");
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(
                "src/test/java/study_mode_use_case/test_data/FlashcardSets.csv");
        IUserDataAccess userGateway = new CommonUserDataAccess(
                "src/test/java/study_mode_use_case/test_data/Users.csv");

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

        StudySessionOutputBoundary presenter = new StudySessionPresenter();
        StudySessionInputBoundary interactor = new StudySessionInteractor(gateway, presenter);
        controller = new StudySessionController(interactor);

        next.setCommand(StudySessionController.next);
        prev.setCommand(StudySessionController.prev);
        flip.setCommand(StudySessionController.flip);

        return new StudySettingsRequestModel(flashcardSetId, sortingOrder, termIsDefault, isReverse);
    }

    /**
     * Tests a flashcard set sorted by creation date by iterating forward through the flashcard set
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testTimeSort() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);

        assertEquals("test card 1", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(next);
        assertEquals("test card 2", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 3", curr.getOutputText());
        assertEquals(3, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("alphabetical order checker", curr.getOutputText());
        assertEquals(4, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 1", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());
    }

    /**
     * Tests a flashcard set sorted by alphabetical order by iterating forward through the flashcard set
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testAlphSort() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.alphSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);

        assertEquals("alphabetical order checker", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(next);
        assertEquals("test card 1", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 2", curr.getOutputText());
        assertEquals(3, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 3", curr.getOutputText());
        assertEquals(4, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("alphabetical order checker", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());
    }

    /**
     * Tests the "previous" button by iterating backward through the flashcard set
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testPrevious() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);

        assertEquals("test card 1", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(prev);
        assertEquals("alphabetical order checker", curr.getOutputText());
        assertEquals(4, curr.getCardNumber());

        curr = controller.study(prev);
        assertEquals("test card 3", curr.getOutputText());
        assertEquals(3, curr.getCardNumber());

        curr = controller.study(prev);
        assertEquals("test card 2", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());

        curr = controller.study(prev);
        assertEquals("test card 1", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());
    }

    /**
     * Tests the flipping functionality by flipping the first flashcard and then
     * iterating to the next flashcard
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testFlip() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);

        assertEquals("test card 1", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(flip);
        assertEquals("the first test card", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 2", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());
    }

    /**
     * Tests study mode with definitions set to default by flipping the first flashcard and then
     * iterating to the next flashcard
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testsTermNotDefault() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, false, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);

        assertEquals("the first test card", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(flip);
        assertEquals("test card 1", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("the second test card", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());
    }

    /**
     * Tests reverse functionality by iterating forward through a reversed flashcard set
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testReverse() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, true);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);

        assertEquals("alphabetical order checker", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(next);
        assertEquals("test card 3", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 2", curr.getOutputText());
        assertEquals(3, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 1", curr.getOutputText());
        assertEquals(4, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("alphabetical order checker", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());
    }

    /**
     * Checks that a StudySettingsFailed response is thrown upon attempting to study an
     * empty flashcard set
     * @throws IOException if there is unexpected database behaviour
     */
    @Test
    void testEmpty() throws IOException {
        StudySettingsRequestModel request = this.setup(emptyTestSetId,
                StudySessionController.timeSort, true, true);

        assertThrows(StudySettingsFailed.class, () -> controller.getSetToStudy(request));
    }
}
