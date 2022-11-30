package interface_adapters.presenters;

import interface_adapters.presenters.exceptions.UserLoginFailed;
import login_and_signup_use_case.UserLoginOutputBoundary;
import login_and_signup_use_case.UserLoginResponseModel;
/**
 * The presenter for Login Use Case
 *<p>
 * Interface Adapter
 * @author Aryan Chablani
 */
public class UserLoginPresenter implements UserLoginOutputBoundary {

    /**
     * Prepares the Login success view.
     * @param response the response for once the user logs in
     * @return the response if the Login is successful.
     */
    @Override
    public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel response) {
        return response;
    }

    /**
     * Prepares the Login failure view.
     * @param error to ensure there is an error.
     * @return the UserLoginFailed if the user login fails.
     */
    @Override
    public UserLoginResponseModel prepareFailView(String error) {
        throw new UserLoginFailed(error);
    }

}