package loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserRegisterOutputBoundary;
import loginAndSignupUseCase.UserRegisterResponseModel;

// Interface adapters layer

public class UserRegisterPresenter implements UserRegisterOutputBoundary {

    @Override
    public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel response) {
        return response;
    }

    @Override
    public UserRegisterResponseModel prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}

