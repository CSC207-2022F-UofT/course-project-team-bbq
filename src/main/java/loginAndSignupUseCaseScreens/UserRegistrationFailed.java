package loginAndSignupUseCaseScreens;

public class UserRegistrationFailed extends RuntimeException {
    public UserRegistrationFailed(String error) {
        super(error);
    }
}