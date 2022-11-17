package loginAndSignupUseCase;

import dataAccess.IFlashcardSetDataAccess;
import dataAccess.IUserDataAccess;
import entityRequestModels.CommonUserDsRequestModel;

import java.util.HashMap;

public class UserLoginInteractor implements UserLoginInputBoundary{
    final IUserDataAccess userDsGateway;
    final IFlashcardSetDataAccess flashcardSetDsGateway;
    final UserLoginOutputBoundary userLoginOutputBoundary;

    public UserLoginInteractor(IUserDataAccess userLoginDsGateway, IFlashcardSetDataAccess flashcardSetDsGateway,
                               UserLoginOutputBoundary userLoginOutputBoundary) {
        this.flashcardSetDsGateway = flashcardSetDsGateway;
        this.userLoginOutputBoundary = userLoginOutputBoundary;
        this.userDsGateway = userLoginDsGateway;
    }
    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel) {
        CommonUserDsRequestModel tempUser = userDsGateway.getUser(userLoginRequestModel.getUsername());
        if (!userDsGateway.existsByName(userLoginRequestModel.getUsername())) {
            return userLoginOutputBoundary.prepareFailView("User Doesn't Exist");
        } else if (!tempUser.getPassword().equals(userLoginRequestModel.getPassword())) {
            return userLoginOutputBoundary.prepareFailView("Incorrect Password");
        }
        HashMap flashcardSets = new HashMap<Integer, String[]>();
        for(int flashcardSetId : tempUser.getFlashcardSetIds()){
            flashcardSets.put(flashcardSetId, flashcardSetDsGateway.getFlashcardSet(flashcardSetId));
        }


        UserLoginResponseModel accountResponseModel = new UserLoginResponseModel(tempUser.getUsername(),
                tempUser.getIsAdmin(), flashcardSets);
        return userLoginOutputBoundary.prepareSuccessView(accountResponseModel);
    }
}
