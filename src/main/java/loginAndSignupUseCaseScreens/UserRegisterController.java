package loginAndSignupUseCaseScreens;

import loginAndSignupUseCase.UserRegisterInputBoundary;
import loginAndSignupUseCase.UserRegisterRequestModel;
import loginAndSignupUseCase.UserRegisterResponseModel;


// Interface adapters layer

public class UserRegisterController {

    final UserRegisterInputBoundary userInput;

    public UserRegisterController(UserRegisterInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
        return userInput.create(requestModel);
    }
    UserRegisterResponseModel create(String username, String password1, String password2, boolean isAdmin) {
        UserRegisterRequestModel requestModel = new UserRegisterRequestModel(username, password1, password2, isAdmin);

        return userInput.create(requestModel);
    }


}

