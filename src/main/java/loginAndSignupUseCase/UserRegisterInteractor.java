package loginAndSignupUseCase;

import dataAccess.DBGateway;
import entities.User;
import entities.UserFactory;
import entityRequestModels.CommonUserDsRequestModel;

public class UserRegisterInteractor implements UserRegisterInputBoundary {
    private final DBGateway gateway;
    private final UserRegisterOutputBoundary userRegisterOutputBoundary;
    private final UserFactory userFactory;

    public UserRegisterInteractor(DBGateway gateway, UserRegisterOutputBoundary userRegisterOutputBoundary,
                                  UserFactory userFactory) {
        this.gateway = gateway;
        this.userRegisterOutputBoundary = userRegisterOutputBoundary;
        this.userFactory = userFactory;
    }


    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
         if (gateway.existsByName(requestModel.getName())) {
            return userRegisterOutputBoundary.prepareFailView("User Already Exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userRegisterOutputBoundary.prepareFailView("Passwords Don't Match.");
        }
        User fakeUser = userFactory.create("BLANK", "BLANK1", false);

        // set isAdmin to true if they entered the admin key correctly
        boolean isAdmin = fakeUser.adminKeyValid(requestModel.getAdminKeyEntered());

        User user = userFactory.create(requestModel.getName(), requestModel.getPassword(), isAdmin);
        if(!requestModel.getAdminKeyEntered().equals("") && !user.adminKeyValid(requestModel.getAdminKeyEntered())){
            return userRegisterOutputBoundary.prepareFailView("Incorrect Admin Key.");
        }
        if (!user.passwordIsValid()) {
            return userRegisterOutputBoundary.prepareFailView("User password must have more than 5 characters.");
        }

        CommonUserDsRequestModel userDsModel = new CommonUserDsRequestModel(user.getUsername(), user.getPassword(),
                user.getIsAdmin(), user.getFlashcardSetIds());
        gateway.saveUser(userDsModel);

        UserRegisterResponseModel accountResponseModel = new UserRegisterResponseModel(user.getUsername(),
                user.getPassword(), user.getIsAdmin());
        return userRegisterOutputBoundary.prepareSuccessView(accountResponseModel);
    }
}
