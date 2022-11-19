package studyMode;

/**
 * A data structure which contains the updated view data
 * for the study session.
 * <p>
 * Interface Adapters.
 * @author Lucas Prates
 */
public class StudySessionResponseModel {
    private String outputText;
    private int cardNumber;

    /**
     * Creates a StudySessionResponseModel
     * @param outputText the text to be displayed on the current flashcard
     * @param cardNumber the position of the current flashcard in the flashcard
     *                   set (count starting at 1)
     */
    public StudySessionResponseModel(String outputText, int cardNumber){
        this.outputText = outputText;
        this.cardNumber = cardNumber;
    }

    /**
     * Creates a StudySessionResponseModel with null values.
     */
    public StudySessionResponseModel() {

    }

    /**
     * @return the output text
     */
    public String getOutputText() {
        return outputText;
    }

    /**
     * @return the card number
     */
    public int getCardNumber() {
        return cardNumber;
    }

}
