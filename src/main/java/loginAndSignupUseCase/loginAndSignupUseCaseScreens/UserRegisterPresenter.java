package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserRegisterOutputBoundary;
import loginAndSignupUseCase.UserRegisterResponseModel;

/**
 * The presenter for Signup Use Case
 *<p>
 * Interface Adapter
 * @author Aryan Chablani
 */

public class UserRegisterPresenter implements UserRegisterOutputBoundary {

    /**
     * Prepares the Signup success view.
     * @param response the response for once the user registers
     * @return the response if the registration is successful.
     */
    @Override
    public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel response) {
        return response;
    }

    /**
     * Prepares the Signup failure view.
     * @param error to ensure there is an error.
     * @throw the UserRegistrationFailed if the user signup fails.
     */
    @Override
    public UserRegisterResponseModel prepareFailView(String error) {
        throw new UserRegistrationFailed(error);
    }
}

