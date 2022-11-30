package interface_adapters.presenters.exceptions;
/**
 * A screen returning a message to the user that their search was unsuccessful
 * <p>
 * Frameworks & Drivers
 * @author Winston Chieng
 */

public class SearchScreenFailed extends RuntimeException{

    /**
     * Creates a SearchFailureScreen
     */
    public SearchScreenFailed(String error){
        super(error);
    }
}
