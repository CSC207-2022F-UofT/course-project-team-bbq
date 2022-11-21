package login_and_signup_use_case.login_and_signup_use_case_screens;
/**
 * Display error message if login failed
 *<p>
 * Interface Adapter
 * @author Aryan Chablani
 */
public class UserLoginFailed extends RuntimeException{
    public UserLoginFailed(String error) {
        super(error);
    }
}
