package login_and_signup_use_case.login_and_signup_use_case_screens;

import login_and_signup_use_case.UserRegisterInputBoundary;
import login_and_signup_use_case.UserRegisterRequestModel;
import login_and_signup_use_case.UserRegisterResponseModel;

/**
 * Create controller for the signup use case
 *<p>
 * Interface Adapter
 * @author Aryan Chablani
 */

public class UserRegisterController {

    /**
     * Introduces the necessary input boundary
     */
    final UserRegisterInputBoundary userInput;

    /**
     * Creates a UserRegisterController
     * @param accountGateway the UserRegisterInputBoundary
     */
    public UserRegisterController(UserRegisterInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

//    UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
//        return userInput.create(requestModel);
//    }

    /**
     * Provides ability to sign given a certain username and password.
     * @param username The username desired for signup
     * @param password1 The password desired for signup
     * @param password2 The password desired for signup
     * @param adminKeyEntered The admin key entered by the user to determine their level of admin access
     * @return Create the request model for the interactor
     */
    UserRegisterResponseModel create(String username, String password1, String password2, String adminKeyEntered) {
        UserRegisterRequestModel requestModel = new UserRegisterRequestModel(username, password1, password2,
                adminKeyEntered);

        return userInput.create(requestModel);
    }


}

