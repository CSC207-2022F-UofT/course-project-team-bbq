package loginAndSignupUseCase;

import loginAndSignupUseCase.UserResponseModel;

public interface UserPresenter {

    UserResponseModel prepareSuccessView(UserResponseModel user);

    UserResponseModel prepareFailView(String error);
}
