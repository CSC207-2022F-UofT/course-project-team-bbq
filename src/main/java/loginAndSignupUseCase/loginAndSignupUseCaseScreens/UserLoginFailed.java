package loginAndSignupUseCase.loginAndSignupUseCaseScreens;
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
