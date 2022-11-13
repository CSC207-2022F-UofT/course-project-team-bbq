package loginAndSignupUseCase;

import entityRequestModels.CommonUserDsRequestModel;
import loginAndSignupUseCase.UserResponseModel;

public interface UserInputBoundary {
    UserResponseModel create(CommonUserDsRequestModel requestModel);
}
