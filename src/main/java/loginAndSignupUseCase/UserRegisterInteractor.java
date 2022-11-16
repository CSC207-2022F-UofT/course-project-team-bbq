package loginAndSignupUseCase;

import dataAccess.IUserDataAccess;
import entities.User;
import entities.UserFactory;
import entityRequestModels.CommonUserDsRequestModel;

public class UserRegisterInteractor implements UserRegisterInputBoundary {
    final IUserDataAccess userDsGateway;
    final UserRegisterOutputBoundary userRegisterOutputBoundary;
    final UserFactory userFactory;

    public UserRegisterInteractor(IUserDataAccess userRegisterDsGateway, UserRegisterOutputBoundary userRegisterOutputBoundary,
                                  UserFactory userFactory) {
        this.userDsGateway = userRegisterDsGateway;
        this.userRegisterOutputBoundary = userRegisterOutputBoundary;
        this.userFactory = userFactory;
    }


    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userRegisterOutputBoundary.prepareFailView("User Already Exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userRegisterOutputBoundary.prepareFailView("Passwords Don't Match.");
        } else if (!requestModel.getADMIN_KEY().equals(requestModel.getADMIN_KEY())){
            return userRegisterOutputBoundary.prepareFailView("Incorrect Admin Key.");
        }

        User user = userFactory.create(requestModel.getName(), requestModel.getPassword(),
                requestModel.getIsAdmin());
        if (!user.passwordIsValid()) {
            return userRegisterOutputBoundary.prepareFailView("User password must have more than 5 characters.");
        }

        CommonUserDsRequestModel userDsModel = new CommonUserDsRequestModel(user.getUsername(), user.getPassword(),
                user.getIsAdmin(), user.getFlashcardSetIds());
        userDsGateway.saveUser(userDsModel);

        UserRegisterResponseModel accountResponseModel = new UserRegisterResponseModel(user.getUsername());
        return userRegisterOutputBoundary.prepareSuccessView(accountResponseModel);
    }
}
