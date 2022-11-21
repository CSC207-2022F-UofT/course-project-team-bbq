package loginAndSignupUseCase;

import dataAccess.DBGateway;
import entityRequestModels.CommonUserDsRequestModel;

import java.util.HashMap;

public class UserLoginInteractor implements UserLoginInputBoundary{

    final DBGateway gateway;
    final UserLoginOutputBoundary userLoginOutputBoundary;

    public UserLoginInteractor(DBGateway gateway,
                               UserLoginOutputBoundary userLoginOutputBoundary) {
        this.gateway = gateway;
        this.userLoginOutputBoundary = userLoginOutputBoundary;
    }
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
