package loginAndSignupUseCase;

public interface UserLoginOutputBoundary {
    UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user);

    UserLoginResponseModel prepareFailView(String error);
}
