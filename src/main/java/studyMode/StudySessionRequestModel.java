package studyMode;

/**
 * A data structure which specifies if the user wants to flip the current
 * flashcard, or go to the next/previous flashcard.
 * <p>
 * Application Business Rules.
 * @author Lucas Prates
 */
public class StudySessionRequestModel {
    private String command;

    /**
     * @return get the user's input
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command sets the user's input
     */
    public void setCommand(String command) {
        this.command = command;
    }

}
