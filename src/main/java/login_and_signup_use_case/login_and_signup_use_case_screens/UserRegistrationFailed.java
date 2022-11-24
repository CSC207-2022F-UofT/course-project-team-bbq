package login_and_signup_use_case.login_and_signup_use_case_screens;

/**
 * Display error message if signup failed
 *<p>
 * Interface Adapter
 * @author Aryan Chablani
 */
public class UserRegistrationFailed extends RuntimeException {
    public UserRegistrationFailed(String error) {
        super(error);
    }
}