package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserLoginOutputBoundary;
import loginAndSignupUseCase.UserLoginResponseModel;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    @Override
    public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel response) {
        return response;
    }

    @Override
    public UserLoginResponseModel prepareFailView(String error) {
        throw new UserLoginFailed(error);
    }

}