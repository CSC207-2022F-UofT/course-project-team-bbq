package frameworks_and_drivers.components;

import java.util.List;

/**
 * Factory that produces GUI question cards.
 * Frameworks & Drivers
 * @author Anthony
 */
public class QuestionCardFactory {
    /**
     * Creates a question card.
     * @param type the type of question
     * @param num the question number
     * @param outputText the output text
     * @return the question card
     */
    public QuestionCard createQuestionCard(String type, int num, List<String> outputText) {
        switch(type) {
            case "MC":
                return new MultipleChoiceQuestionCard(num, outputText);
            case "TE":
                return new TextEntryQuestionCard(num, outputText);
            case "TF":
                return new TrueFalseQuestionCard(num, outputText);
            default:
                throw new IllegalArgumentException("Invalid type.");
        }
    }

    /**
     * Creates an answered question card.
     * @param type the type of question
     * @param num the question number
     * @param outputText the output text
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     * @return the answered question card
     */
    public QuestionCard createAnsweredQuestionCard(String type, int num, List<String> outputText,
                                                 String userAnswer, String actualAnswer) {
        switch(type) {
            case "MC":
                return new MultipleChoiceQuestionCard(num, outputText, userAnswer, actualAnswer);
            case "TE":
                return new TextEntryQuestionCard(num, outputText, userAnswer, actualAnswer);
            case "TF":
                return new TrueFalseQuestionCard(num, outputText, userAnswer, actualAnswer);
            default:
                throw new IllegalArgumentException("Invalid type.");
        }
    }
}
