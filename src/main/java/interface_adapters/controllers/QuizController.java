package interface_adapters.controllers;

import quiz_use_case.*;

/**
 * Quiz Controller.
 * Interface Adapters
 * @author Anthony
 */
public class QuizController {
    private final QuizInputBoundary userInput;

    /**
     * Creates a quiz controller.
     * @param quizSettingsInput the quiz settings input boundary
     */
    public QuizController(QuizInputBoundary quizSettingsInput) {
        this.userInput = quizSettingsInput;
    }

    /**
     * I want to start the quiz.
     * @param requestModel the request model
     * @return a response model that starts the quiz
     */
    public QuizSettingsResponseModel startQuiz(QuizSettingsRequestModel requestModel) {
        return this.userInput.startQuiz(requestModel);
    }

    /**
     * I want to submit my quiz and receive my results.
     * @param requestModel the request model
     * @return a response model that displays the results
     */
    public QuizResponseModel getResults(QuizRequestModel requestModel) {
        return this.userInput.getResults(requestModel);
    }

    /**
     * I want to get the number of flashcards.
     * @param requestModel the request model
     * @return the number of flashcards
     */
    public QuizSettingsResponseModel getNumFlashcards(QuizSettingsRequestModel requestModel) {
        return this.userInput.getNumFlashcards(requestModel);
    }
}
