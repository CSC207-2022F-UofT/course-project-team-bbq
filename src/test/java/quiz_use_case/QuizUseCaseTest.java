package quiz_use_case;

import data_access_use_case.*;
import static org.junit.jupiter.api.Assertions.*;

import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import interface_adapters.controllers.QuizController;
import interface_adapters.presenters.QuizPresenter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains various test cases for the quiz use case.
 * @author Anthony
 */
public class QuizUseCaseTest {
    QuizController controller;

    /**
     * This method sets up my tests.
     * @param numQuestions the number of questions
     * @param timerOn is the timer on
     * @param multipleChoiceOn is the multiple choice on
     * @param textEntryOn is the text entry on
     * @param trueFalseOn is the true false on
     * @return the quiz settings request model
     * @throws IOException if I can't access the test data
     */
    QuizSettingsRequestModel setup(int numQuestions,
                                   boolean timerOn,
                                   boolean multipleChoiceOn,
                                   boolean textEntryOn,
                                   boolean trueFalseOn) throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(
                "src/test/java/quiz_use_case/test_data/Flashcards.csv");
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(
                "src/test/java/quiz_use_case/test_data/FlashcardSets.csv");
        IUserDataAccess userGateway = new CommonUserDataAccess(
                "src/test/java/quiz_use_case/test_data/Users.csv");

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

        QuizOutputBoundary presenter = new QuizPresenter();
        QuizInputBoundary interactor = new QuizInteractor(gateway, presenter);
        this.controller = new QuizController(interactor);

        return new QuizSettingsRequestModel(numQuestions, timerOn,
                0, multipleChoiceOn, textEntryOn, trueFalseOn, 0);
    }

    /**
     * Tests the quiz use case when only multiple choice questions are enabled.
     * @throws IOException if I can't access the test data
     */
    @Test
    void testOnlyMultipleChoice() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(10, false,
                true, false, false);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertFalse(quizSettingsResponseModel.isFailed());

        List<String> types = quizSettingsResponseModel.getTypes();
        List<List<String>> outputText = quizSettingsResponseModel.getOutputText();

        assertEquals(10, types.size());
        assertEquals(10, outputText.size());
        assertTrue(types.contains("MC"));
        assertFalse(types.contains("TE"));
        assertFalse(types.contains("TF"));

        List<String> userAnswers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userAnswers.add("incorrect answer");
        }
        QuizRequestModel quizRequestModel = new QuizRequestModel(userAnswers);
        QuizResponseModel quizResponseModel = controller.getResults(quizRequestModel);

        assertFalse(quizResponseModel.isFailed());

        assertEquals(0, quizResponseModel.getScore());
        assertEquals(10, quizResponseModel.getNumQuestions());
    }

    /**
     * Tests the quiz use case when all question types are enabled.
     * @throws IOException if I can't access the test data
     */
    @Test
    void testAllOptionsEnabled() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(12, false,
                true, true, true);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertFalse(quizSettingsResponseModel.isFailed());

        List<String> types = quizSettingsResponseModel.getTypes();
        List<List<String>> outputText = quizSettingsResponseModel.getOutputText();

        assertEquals(12, types.size());
        assertEquals(12, outputText.size());
        assertTrue(types.contains("MC") || types.contains("TE") || types.contains("TF"));

        List<String> userAnswers = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            userAnswers.add("incorrect answer");
        }
        QuizRequestModel quizRequestModel = new QuizRequestModel(userAnswers);
        QuizResponseModel quizResponseModel = controller.getResults(quizRequestModel);

        assertFalse(quizResponseModel.isFailed());

        assertEquals(0, quizResponseModel.getScore());
        assertEquals(12, quizResponseModel.getNumQuestions());
    }

    /**
     * Tests the quiz use case when no question types are enabled.
     * @throws IOException if I can't access the test data
     */
    @Test
    void testNoOptionsEnabled() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(12, false,
                false, false, false);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertTrue(quizSettingsResponseModel.isFailed());
    }

    /**
     * Tests the quiz use case when the timer is set to a ridiculous value.
     * @throws IOException if I can't access the test data
     */
    @Test
    void testRidiculousTimer() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(12, true,
                true, true, false);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertTrue(quizSettingsResponseModel.isFailed());
    }
}
