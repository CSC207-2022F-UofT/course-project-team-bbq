package interface_adapters.presenters.exceptions;

/**
 * An exception which is called if the study mode interactor fails to run
 */
public class StudySettingsFailed extends RuntimeException {
    public StudySettingsFailed(String error) {
        super(error);
    }
}

