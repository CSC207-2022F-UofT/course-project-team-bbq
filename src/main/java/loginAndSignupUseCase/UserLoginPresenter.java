package loginAndSignupUseCase;

public interface UserLoginPresenter {

    UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user);

    UserLoginResponseModel prepareFailView(String error);
}
