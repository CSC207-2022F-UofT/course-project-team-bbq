package login_and_signup_use_case;

import data_access.DBGateway;
import entities.User;
import entities.UserFactory;
import data_access.entity_request_models.CommonUserDsRequestModel;
/**
 * Signup Interactor, the heart of the registering use case engine.
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserRegisterInteractor implements UserRegisterInputBoundary {
    private final DBGateway gateway;
    private final UserRegisterOutputBoundary userRegisterOutputBoundary;
    private final UserFactory userFactory;

    /**
     * Constructs a registering interactor.
     * @param gateway the user signup gateway
     * @param userRegisterOutputBoundary the signup output boundary presenter
     * @param userFactory to create a user
     */
    public UserRegisterInteractor(DBGateway gateway, UserRegisterOutputBoundary userRegisterOutputBoundary,
                                  UserFactory userFactory) {
        this.gateway = gateway;
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
         if (gateway.existsByName(requestModel.getName())) {
            return userRegisterOutputBoundary.prepareFailView("User Already Exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userRegisterOutputBoundary.prepareFailView("Passwords Don't Match.");
        }
        // Create a temporary user to access the adminkey
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
