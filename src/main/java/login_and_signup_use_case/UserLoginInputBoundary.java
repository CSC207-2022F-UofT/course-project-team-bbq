package login_and_signup_use_case;
/**
 * Login Input Boundary.
 * Application Business Rules
 * @author Aryan
 */
public interface UserLoginInputBoundary {

    /**
     * Handles logging in a user.
     * @param userLoginRequestModel the login request model for the user required to log them in
     * @return the login response model
     */
    UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel);
}
