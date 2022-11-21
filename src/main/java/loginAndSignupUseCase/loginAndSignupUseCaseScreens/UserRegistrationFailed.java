package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

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