package loginAndSignupUseCase;
/**
 * Login Input Boundary.
 * Application Business Rules
 * @author Aryan
 */
public interface UserLoginInputBoundary {
    UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel);
}
