package loginAndSignupUseCase;

import dataAccess.IUserDataAccess;
import entities.User;
import entities.UserFactory;
import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.UserDsRequestModel;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserRegisterInteractor implements UserInputBoundary {
    final IUserDataAccess userDsGateway;
    final UserPresenter userPresenter;
    final UserFactory userFactory;

    public UserRegisterInteractor(IUserDataAccess userRegisterDsGateway, UserPresenter userPresenter,
                                  UserFactory userFactory) {
        this.userDsGateway = userRegisterDsGateway;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public UserResponseModel create(CommonUserDsRequestModel requestModel) {
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userPresenter.prepareFailView("User already exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userPresenter.prepareFailView("Passwords don't match.");
        }

        User user = userFactory.create(requestModel.getName(), requestModel.getPassword(),
                requestModel.getIsAdmin(), new HashMap<Integer, String[]>());
        if (!user.passwordIsValid()) {
            return userPresenter.prepareFailView("User password must have more than 5 characters.");
        }

        LocalDateTime now = LocalDateTime.now();
        CommonUserDsRequestModel userDsModel = new CommonUserDsRequestModel(user.getUsername(), user.getPassword(),
                user.getIsAdmin(), now);
        userDsGateway.saveUser(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(user.getUsername(), now.toString());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
