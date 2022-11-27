package login_and_signup_use_case;

/**
 * Register Output Boundary.
 * Application Business Rules
 * @author Aryan Chablani
 */
public interface UserRegisterOutputBoundary {

    /**
     * Confirm whether the user was successful in registering
     * @param user UserRegisterResponseModel with the necessary data for the system
     * @return UserRegisterResponseModel for the user having registered
     */
    UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user);

    /**
     * Show an error if the user was unable to register
     * @param error a string with the reason for the error
     * @return UserRegisterResponseModel with an error suggesting the inability to register
     */
    UserRegisterResponseModel prepareFailView(String error);
}
