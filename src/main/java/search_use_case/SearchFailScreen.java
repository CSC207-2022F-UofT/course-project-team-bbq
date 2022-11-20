package search_use_case;
/**
 * A screen returning a message to the user that their search was unsuccessful
 * <p>
 * Frameworks & Drivers
 * @author Winston Chieng
 */

public class SearchFailScreen extends RuntimeException{

    /**
     * Creates a SearchFailureScreen
     */
    public SearchFailScreen(String error){
        super(error);
    }
}
