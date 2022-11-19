package loginAndSignupUseCase;

public interface UserRegisterOutputBoundary {

    UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user);

    UserRegisterResponseModel prepareFailView(String error);
}
