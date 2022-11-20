package studyMode;
import dataAccess.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StudySessionUseCaseUnitTest {

    int testSetId = 0;
    int emptyTestSetId = 1;

    StudySessionRequestModel next = new StudySessionRequestModel();
    StudySessionRequestModel prev = new StudySessionRequestModel();
    StudySessionRequestModel flip = new StudySessionRequestModel();

    StudySessionController controller;

    StudySettingsRequestModel setup(int flashcardSetId, String sortingOrder,
                                     boolean termIsDefault, boolean isReverse) throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess("src/test/java/studyMode/testData/Flashcards.csv");
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess("src/test/java/studyMode/testData/FlashcardSets.csv");
        IUserDataAccess userGateway = new CommonUserDataAccess("src/test/java/studyMode/testData/Users.csv");

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

        StudySessionOutputBoundary presenter = new StudySessionPresenter();
        StudySessionInputBoundary interactor = new StudySessionInteractor(gateway, presenter);
        controller = new StudySessionController(interactor);

        next.setCommand(StudySessionController.next);
        prev.setCommand(StudySessionController.prev);
        flip.setCommand(StudySessionController.flip);

        return new StudySettingsRequestModel(flashcardSetId, sortingOrder, termIsDefault, isReverse);
    }
    @Test
    void testTimeSort() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertFalse(settings.hasFailed());

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

    @Test
    void testAlphSort() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.alphSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertFalse(settings.hasFailed());

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

    @Test
    void testPrevious() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertFalse(settings.hasFailed());

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

    @Test
    void testFlip() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertFalse(settings.hasFailed());

        assertEquals("test card 1", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(flip);
        assertEquals("the first test card", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("test card 2", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());
    }

    @Test
    void testsTermNotDefault() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, false, false);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertFalse(settings.hasFailed());

        assertEquals("the first test card", settings.getOutputText());
        assertEquals(1, settings.getCardNumber());

        StudySessionResponseModel curr = controller.study(flip);
        assertEquals("test card 1", curr.getOutputText());
        assertEquals(1, curr.getCardNumber());

        curr = controller.study(next);
        assertEquals("the second test card", curr.getOutputText());
        assertEquals(2, curr.getCardNumber());
    }

    @Test
    void testReverse() throws IOException {
        StudySettingsRequestModel request = this.setup(testSetId,
                StudySessionController.timeSort, true, true);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertFalse(settings.hasFailed());

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

    @Test
    void testEmpty() throws IOException {
        StudySettingsRequestModel request = this.setup(emptyTestSetId,
                StudySessionController.timeSort, true, true);

        StudySettingsResponseModel settings = controller.getSetToStudy(request);
        assertTrue(settings.hasFailed());
    }
}
