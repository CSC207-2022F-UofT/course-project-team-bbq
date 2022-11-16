package loginAndSignupUseCase;

import dataAccess.IUserDataAccess;
import entities.User;
import entities.UserFactory;
import entityRequestModels.CommonUserDsRequestModel;

public class UserRegisterInteractor implements UserRegisterInputBoundary {
    final IUserDataAccess userDsGateway;
    final UserRegisterPresenter userRegisterPresenter;
    final UserFactory userFactory;

    public UserRegisterInteractor(IUserDataAccess userRegisterDsGateway, UserRegisterPresenter userRegisterPresenter,
                                  UserFactory userFactory) {
        this.userDsGateway = userRegisterDsGateway;
        this.userRegisterPresenter = userRegisterPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userRegisterPresenter.prepareFailView("User Already Exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userRegisterPresenter.prepareFailView("Passwords Don't Match.");
        } else if (!requestModel.getADMIN_KEY().equals(requestModel.getADMIN_KEY())){
            return userRegisterPresenter.prepareFailView("Incorrect Admin Key.");
        }

        User user = userFactory.create(requestModel.getName(), requestModel.getPassword(),
                requestModel.getIsAdmin());
        if (!user.passwordIsValid()) {
            return userRegisterPresenter.prepareFailView("User password must have more than 5 characters.");
        }

        CommonUserDsRequestModel userDsModel = new CommonUserDsRequestModel(user.getUsername(), user.getPassword(),
                user.getIsAdmin(), user.getFlashcardSetIds());
        userDsGateway.saveUser(userDsModel);

        UserRegisterResponseModel accountResponseModel = new UserRegisterResponseModel(user.getUsername());
        return userRegisterPresenter.prepareSuccessView(accountResponseModel);
    }
}
