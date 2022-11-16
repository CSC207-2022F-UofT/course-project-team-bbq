package loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserLoginInputBoundary;
import loginAndSignupUseCase.UserLoginRequestModel;
import loginAndSignupUseCase.UserLoginResponseModel;

public class UserLoginController {
    final UserLoginInputBoundary userInput;

    public UserLoginController(UserLoginInputBoundary loginGateway) {
        this.userInput = loginGateway;
    }

    /**
     * Provides ability to login given a certain username and password.
     * @param username The username used to log in
     * @param password The password used to log in
     * @return The response given by the interactor object.
     */
    UserLoginResponseModel create(String username, String password) {
        UserLoginRequestModel requestModel = new UserLoginRequestModel(username, password);
        return userInput.login(requestModel);
    }

}
