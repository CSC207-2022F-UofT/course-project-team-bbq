package login_and_signup_use_case;

import data_access.DBGateway;
import data_access.entity_request_models.CommonUserDsRequestModel;

import java.util.HashMap;
/**
 * Login Interactor, the heart of the login use case engine.
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserLoginInteractor implements UserLoginInputBoundary{

    final DBGateway gateway;
    final UserLoginOutputBoundary userLoginOutputBoundary;

     /**
     * Constructs a login interactor.
     * @param gateway the gateway to database information
     * @param userLoginOutputBoundary the output boundary presenter
     */
    public UserLoginInteractor(DBGateway gateway,
                               UserLoginOutputBoundary userLoginOutputBoundary) {
        this.gateway = gateway;
        this.userLoginOutputBoundary = userLoginOutputBoundary;
    }

    /**
     * Login a user
     * @param userLoginRequestModel to get all the data inputted by the user
     * @return a UserLoginResponseModel for the response of after the user has logged in
     * through the userLoginOutputBoundary
     */
    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel) {
        CommonUserDsRequestModel tempUser = gateway.getCommonUser(userLoginRequestModel.getUsername());
        if (!gateway.existsByName(userLoginRequestModel.getUsername())) {
            return userLoginOutputBoundary.prepareFailView("User Doesn't Exist");
        } else if (!tempUser.getPassword().equals(userLoginRequestModel.getPassword())) {
            return userLoginOutputBoundary.prepareFailView("Incorrect Password");
        }
        HashMap<Integer, String[]> flashcardSets = new HashMap<>();
        for(int flashcardSetId : tempUser.getFlashcardSetIds()){
            flashcardSets.put(flashcardSetId, gateway.getTitleAndDescription(flashcardSetId));
        }

        UserLoginResponseModel accountResponseModel = new UserLoginResponseModel(tempUser.getUsername(),
                tempUser.getPassword(),
                tempUser.getIsAdmin(), flashcardSets);
        return userLoginOutputBoundary.prepareSuccessView(accountResponseModel);
    }
}
