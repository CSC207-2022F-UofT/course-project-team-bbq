package login_and_signup_use_case;

/**
 * Login Output Boundary.
 * Application Business Rules
 * @author Aryan Chablani
 */
public interface UserLoginOutputBoundary {

    /**
     * Confirm whether the user was successful in logging in
     * @param user UserLoginResponseModel with the necessary data for the homepage
     * @return UserLoginResponseModel for the user having logged in.
     */
    UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user);

    /**
     * Show an error if the user was unable to login
     * @param error a string with the reason for the error
     * @return UserLoginResponseModel with an error suggesting the inability to log in
     */
    UserLoginResponseModel prepareFailView(String error);
}
