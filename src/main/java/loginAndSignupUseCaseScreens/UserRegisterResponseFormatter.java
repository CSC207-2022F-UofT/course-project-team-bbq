package loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserRegisterPresenter;
import loginAndSignupUseCase.UserRegisterResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Interface adapters layer

public class UserRegisterResponseFormatter implements UserRegisterPresenter {

    @Override
    public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel response) {
        return response;
    }

    @Override
    public UserRegisterResponseModel prepareFailView(String error) {
        throw new UserCreationFailed(error);
    }
}

