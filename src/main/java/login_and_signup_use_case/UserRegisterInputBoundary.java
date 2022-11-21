package login_and_signup_use_case;

/**
 * Signup Input Boundary.
 * Application Business Rules
 * @author Aryan Chablani
 */
public interface UserRegisterInputBoundary {

    /**
     * Handles and ensures clean architecture for creating a user
     * @param requestModel the signup request model with all the information necessary and preferred for the user
     * @return the UserRegisterResponseModel
     */
    UserRegisterResponseModel create(UserRegisterRequestModel requestModel);
}
