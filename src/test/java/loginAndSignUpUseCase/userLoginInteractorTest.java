package loginAndSignUpUseCase;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import java.io.IOException;
import loginAndSignupUseCase.loginAndSignupUseCaseScreens.UserLoginPresenter;
import loginAndSignupUseCase.UserLoginOutputBoundary;
import loginAndSignupUseCase.UserLoginInputBoundary;
import loginAndSignupUseCase.UserLoginResponseModel;
import loginAndSignupUseCase.UserLoginRequestModel;
import loginAndSignupUseCase.UserLoginInteractor;

class UserLoginInteractorTest {

    // 1) UserLoginInteractorTest and prerequisite objects

    IUserDataAccess userGateway = new CommonUserDataAccess(
            "src/test/java/loginAndSignUpUseCase/testData/Users.csv");


    DBGateway gateway = new DBGateway(null, null, userGateway);

    UserLoginInteractorTest() throws IOException {
    }

    @Test
    void login() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("John", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                //assertEquals(Map, user.getFlashcardSets());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        UserLoginInputBoundary interactor = new UserLoginInteractor(
                gateway, presenter);

        // 2) Input data — Normally created by the Controller.
        UserLoginRequestModel inputData = new UserLoginRequestModel(
                "John", "Rockefeller123");

        // 3) Run the use case
        interactor.login(inputData);
    }

    @Test
    void login2() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("Walt", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertTrue(!user.getIsAdmin());
                //assertEquals({}, user.getFlashcardSets());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        UserLoginInputBoundary interactor = new UserLoginInteractor(
                gateway, presenter);

        // 2) Input data — Normally created by the Controller.
        UserLoginRequestModel inputData = new UserLoginRequestModel(
                "Walt", "Disney123");

        // 3) Run the use case
        interactor.login(inputData);
    }
}