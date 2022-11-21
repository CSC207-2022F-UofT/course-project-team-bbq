package loginAndSignupUseCase;

import dataAccess.IFlashcardSetDataAccess;
import dataAccess.IUserDataAccess;
import entityRequestModels.CommonUserDsRequestModel;

import java.util.HashMap;
/**
 * Login Interactor, the heart of the login use case engine.
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserLoginInteractor implements UserLoginInputBoundary{
    final IUserDataAccess userDsGateway;
    final IFlashcardSetDataAccess flashcardSetDsGateway;
    final UserLoginOutputBoundary userLoginOutputBoundary;

    /**
     * Constructs a login interactor.
     * @param userLoginDsGateway the user login gateway
     * @param flashcardSetDsGateway the user flashcard set gateway for the sets belonging to the user
     * @param userLoginOutputBoundary the output boundary presenter
     */
    public UserLoginInteractor(IUserDataAccess userLoginDsGateway, IFlashcardSetDataAccess flashcardSetDsGateway,
                               UserLoginOutputBoundary userLoginOutputBoundary) {
        this.flashcardSetDsGateway = flashcardSetDsGateway;
        this.userLoginOutputBoundary = userLoginOutputBoundary;
        this.userDsGateway = userLoginDsGateway;
    }

    /**
     * Login a user
     * @param userLoginRequestModel to get all the data inputted by the user
     * @return a UserLoginResponseModel for the response of after the user has logged in
     * through the userLoginOutputBoundary
     */
    @Override
    public UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel) {
        CommonUserDsRequestModel tempUser = userDsGateway.getUser(userLoginRequestModel.getUsername());
        if (!userDsGateway.existsByName(userLoginRequestModel.getUsername())) {
            return userLoginOutputBoundary.prepareFailView("User Doesn't Exist");
        } else if (!tempUser.getPassword().equals(userLoginRequestModel.getPassword())) {
            return userLoginOutputBoundary.prepareFailView("Incorrect Password");
        }
        HashMap<Integer, String[]> flashcardSets = new HashMap<>();
        for(int flashcardSetId : tempUser.getFlashcardSetIds()){
            flashcardSets.put(flashcardSetId, flashcardSetDsGateway.getTitleAndDescription(flashcardSetId));
        }

        UserLoginResponseModel accountResponseModel = new UserLoginResponseModel(tempUser.getUsername(),
                tempUser.getIsAdmin(), flashcardSets);
        return userLoginOutputBoundary.prepareSuccessView(accountResponseModel);
    }
}
