package studyMode;

public class StudySessionResponseModel {
    private String outputText;
    private int cardNumber;

    public StudySessionResponseModel(String outputText, int cardNumber){
        this.outputText = outputText;
        this.cardNumber = cardNumber;
    }

    public StudySessionResponseModel() {

    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}