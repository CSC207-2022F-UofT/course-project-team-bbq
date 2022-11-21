package loginAndSignupUseCase;

import dataAccess.IUserDataAccess;
import entities.User;
import entities.UserFactory;
import entityRequestModels.CommonUserDsRequestModel;
/**
 * Signup Interactor, the heart of the registering use case engine.
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserRegisterInteractor implements UserRegisterInputBoundary {
    private final IUserDataAccess userDsGateway;
    private final UserRegisterOutputBoundary userRegisterOutputBoundary;
    private final UserFactory userFactory;

    /**
     * Constructs a registering interactor.
     * @param userRegisterDsGateway the user signup gateway
     * @param userRegisterOutputBoundary the signup output boundary presenter
     * @param userFactory to create a user
     */
    public UserRegisterInteractor(IUserDataAccess userRegisterDsGateway, UserRegisterOutputBoundary userRegisterOutputBoundary,
                                  UserFactory userFactory) {
        this.userDsGateway = userRegisterDsGateway;
        this.userRegisterOutputBoundary = userRegisterOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Register a user
     * @param requestModel to get all the data inputted by the user in order to register the
     * @return a UserRegisterResponseModel for the response to the system after the user has been registered
     */
    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel requestModel) {
        boolean isAdmin = false;

        if (userDsGateway.existsByName(requestModel.getName())) {
            return userRegisterOutputBoundary.prepareFailView("User Already Exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userRegisterOutputBoundary.prepareFailView("Passwords Don't Match.");
        }
        // Create a temporary user to access the adminkey
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
