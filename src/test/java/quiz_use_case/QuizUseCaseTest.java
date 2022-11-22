package quiz_use_case;

import dataAccess.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class QuizUseCaseTest {
    QuizController controller;

    QuizSettingsRequestModel setup(int numQuestions, boolean timerOn, int timerDuration,
                                   boolean multipleChoiceOn, boolean textEntryOn, boolean trueFalseOn,
                                   int flashcardSetID) throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(
                "src/test/java/quiz_use_case/testData/Flashcards.csv");
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(
                "src/test/java/quiz_use_case/testData/FlashcardSets.csv");
        IUserDataAccess userGateway = new CommonUserDataAccess(
                "src/test/java/quiz_use_case/testData/Users.csv");

        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

        QuizOutputBoundary presenter = new QuizPresenter();
        QuizInputBoundary interactor = new QuizInteractor(gateway, presenter);
        this.controller = new QuizController(interactor);

        return new QuizSettingsRequestModel(numQuestions, timerOn,
                timerDuration, multipleChoiceOn, textEntryOn, trueFalseOn, flashcardSetID);
    }

    @Test
    void testOnlyMultipleChoice() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(10, false, 0,
                true, false, false, 0);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertFalse(quizSettingsResponseModel.isFailed());

        ArrayList<String> types = quizSettingsResponseModel.getTypes();
        ArrayList<ArrayList<String>> outputText = quizSettingsResponseModel.getOutputText();

        assertEquals(10, types.size());
        assertEquals(10, outputText.size());
        assertTrue(types.contains("MC"));
        assertFalse(types.contains("TE"));
        assertFalse(types.contains("TF"));

        ArrayList<String> userAnswers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userAnswers.add("incorrect answer");
        }
        QuizRequestModel quizRequestModel = new QuizRequestModel(userAnswers);
        QuizResponseModel quizResponseModel = controller.getResults(quizRequestModel);

        assertFalse(quizResponseModel.isFailed());

        assertEquals(0, quizResponseModel.getScore());
        assertEquals(10, quizResponseModel.getNumQuestions());
    }

    @Test
    void testAllOptionsEnabled() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(12, false, 0,
                true, true, true, 0);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertFalse(quizSettingsResponseModel.isFailed());

        ArrayList<String> types = quizSettingsResponseModel.getTypes();
        ArrayList<ArrayList<String>> outputText = quizSettingsResponseModel.getOutputText();

        assertEquals(12, types.size());
        assertEquals(12, outputText.size());
        assertTrue(types.contains("MC"));
        assertTrue(types.contains("TE"));
        assertTrue(types.contains("TF"));

        ArrayList<String> userAnswers = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            userAnswers.add("incorrect answer");
        }
        QuizRequestModel quizRequestModel = new QuizRequestModel(userAnswers);
        QuizResponseModel quizResponseModel = controller.getResults(quizRequestModel);

        assertFalse(quizResponseModel.isFailed());

        assertEquals(0, quizResponseModel.getScore());
        assertEquals(12, quizResponseModel.getNumQuestions());
    }

    @Test
    void testNoOptionsEnabled() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(12, false, 0,
                false, false, false, 0);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertTrue(quizSettingsResponseModel.isFailed());
    }

    @Test
    void testRidiculousTimer() throws IOException {
        QuizSettingsRequestModel quizSettingsRequestModel = this.setup(12, true, 0,
                true, true, false, 0);

        QuizSettingsResponseModel quizSettingsResponseModel = controller.startQuiz(quizSettingsRequestModel);
        assertTrue(quizSettingsResponseModel.isFailed());
    }
}
