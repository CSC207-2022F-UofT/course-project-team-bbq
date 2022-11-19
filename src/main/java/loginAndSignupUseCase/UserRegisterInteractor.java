package loginAndSignupUseCase;

import dataAccess.IUserDataAccess;
import entities.User;
import entities.UserFactory;
import entityRequestModels.CommonUserDsRequestModel;

public class UserRegisterInteractor implements UserRegisterInputBoundary {
    private final IUserDataAccess userDsGateway;
    private final UserRegisterOutputBoundary userRegisterOutputBoundary;
    private final UserFactory userFactory;

    public UserRegisterInteractor(IUserDataAccess userRegisterDsGateway, UserRegisterOutputBoundary userRegisterOutputBoundary,
                                  UserFactory userFactory) {
        this.userDsGateway = userRegisterDsGateway;
        this.userRegisterOutputBoundary = userRegisterOutputBoundary;
        this.userFactory = userFactory;
    }


    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
        boolean isAdmin = false;

        if (userDsGateway.existsByName(requestModel.getName())) {
            return userRegisterOutputBoundary.prepareFailView("User Already Exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userRegisterOutputBoundary.prepareFailView("Passwords Don't Match.");
        }
        User fakeUser = userFactory.create("BLANK", "BLANK1", false);
        if(requestModel.getAdminKeyEntered().equals("")){
            isAdmin = false;
        }else if(fakeUser.adminKeyValid(requestModel.getAdminKeyEntered())){
            isAdmin = true;
        }


        User user = userFactory.create(requestModel.getName(), requestModel.getPassword(), isAdmin);
        if(!requestModel.getAdminKeyEntered().equals("") && !user.adminKeyValid(requestModel.getAdminKeyEntered())){
            return userRegisterOutputBoundary.prepareFailView("Incorrect Admin Key.");
        }
        if (!user.passwordIsValid()) {
            return userRegisterOutputBoundary.prepareFailView("User password must have more than 5 characters.");
        }

        CommonUserDsRequestModel userDsModel = new CommonUserDsRequestModel(user.getUsername(), user.getPassword(),
                user.getIsAdmin(), user.getFlashcardSetIds());
        userDsGateway.saveUser(userDsModel);

        UserRegisterResponseModel accountResponseModel = new UserRegisterResponseModel(user.getUsername(),
                user.getPassword(), user.getIsAdmin());
        return userRegisterOutputBoundary.prepareSuccessView(accountResponseModel);
    }
}
