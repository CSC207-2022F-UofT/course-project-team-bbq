package login_and_sign_up_use_case;

import dataAccess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import java.io.IOException;
import login_and_signup_use_case.login_and_signup_use_case_screens.UserLoginPresenter;
import login_and_signup_use_case.UserLoginOutputBoundary;
import login_and_signup_use_case.UserLoginInputBoundary;
import login_and_signup_use_case.UserLoginResponseModel;
import login_and_signup_use_case.UserLoginRequestModel;
import login_and_signup_use_case.UserLoginInteractor;

class UserLoginInteractorTest {

    // 1) UserLoginInteractorTest and prerequisite objects

    IUserDataAccess userGateway = new CommonUserDataAccess(
            "src/test/java/login_and_sign_up_use_case/test_data/Users.csv");


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

        UserLoginRequestModel inputData = new UserLoginRequestModel(
                "John", "Rockefeller123");

        interactor.login(inputData);
    }

    @Test
    void login2() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("Walt", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertFalse(user.getIsAdmin());
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

        UserLoginRequestModel inputData = new UserLoginRequestModel(
                "Walt", "Disney123");

        interactor.login(inputData);
    }

    @Test
    void login3() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("George", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertFalse(user.getIsAdmin());
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

        UserLoginRequestModel inputData = new UserLoginRequestModel(
                "George", "Clooney123");

        interactor.login(inputData);
    }

    @Test
    void login4() throws IOException {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("rObErT", user.getSignedInUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertTrue(user.getIsAdmin());
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

        UserLoginRequestModel inputData = new UserLoginRequestModel(
                "rObErT", "DowneyJr.");

        interactor.login(inputData);
    }
}