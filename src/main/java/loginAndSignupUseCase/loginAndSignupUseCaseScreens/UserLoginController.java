package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserLoginInputBoundary;
import loginAndSignupUseCase.UserLoginRequestModel;
import loginAndSignupUseCase.UserLoginResponseModel;

/**
 * Create controller for the login user case
 *<p>
 * Interface Adapter
 * @author Aryan Chablani
 */

public class UserLoginController {

    /**
     * Introduces the necessary input boundary
     */
    final UserLoginInputBoundary userInput;

    /**
     * Creates a UserLoginController
     * @param loginGateway the LoginInputBoundary
     */
    public UserLoginController(UserLoginInputBoundary loginGateway) {
        this.userInput = loginGateway;
    }

    /**
     * Provides ability to login given a certain username and password.
     * @param username The username used to log in
     * @param password The password used to log in
     * @return The response given by the response model
     */
    UserLoginResponseModel create(String username, String password) {
        UserLoginRequestModel requestModel = new UserLoginRequestModel(username, password);
        return userInput.login(requestModel);
    }

}
