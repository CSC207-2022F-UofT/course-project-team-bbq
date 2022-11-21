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
    void create() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("Steve", user.getSignedInUsername());
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
                "Steve", "Apple123");

        // 3) Run the use case
        interactor.login(inputData);
    }

    @Test
    void create2() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("Richard", user.getSignedInUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertFalse(!user.getIsAdmin());
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
                "Richard", "Virgin123");

        // 3) Run the use case
        interactor.login(inputData);
    }
}