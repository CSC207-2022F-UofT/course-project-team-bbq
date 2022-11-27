package login_and_sign_up_use_case;

import data_access.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import login_and_signup_use_case.login_and_signup_use_case_screens.UserLoginPresenter;
import login_and_signup_use_case.UserLoginOutputBoundary;
import login_and_signup_use_case.UserLoginInputBoundary;
import login_and_signup_use_case.UserLoginResponseModel;
import login_and_signup_use_case.UserLoginRequestModel;
import login_and_signup_use_case.UserLoginInteractor;

class UserLoginInteractorTest {

    // 1) UserLoginInteractorTest and prerequisite objects
    IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(
            "src/test/java/login_and_sign_up_use_case/test_data/Flashcards.csv");
    IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(
            "src/test/java/login_and_sign_up_use_case/test_data/FlashcardSets.csv");
    IUserDataAccess userGateway = new CommonUserDataAccess(
            "src/test/java/login_and_sign_up_use_case/test_data/LoginUsers.csv");


    DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

    UserLoginInteractorTest() throws IOException {
    }

    @Test
    void login() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
                HashMap<Integer, String[]> emptyFlashcardSet = new HashMap<>();

                Assertions.assertEquals("John", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertTrue(gateway.existsByName("Ch3is"));
                Assertions.assertEquals(emptyFlashcardSet, user.getFlashcardSets());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                Assertions.fail("Use case failure is unexpected.");
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
    void login2() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
                HashMap<Integer, String[]> emptyFlashcardSet = new HashMap<>();
                Map<Integer, String[]> notEmptyFlashcardSet = user.getFlashcardSets();

                Assertions.assertEquals("Walt", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertEquals(notEmptyFlashcardSet, user.getFlashcardSets());
                System.out.println(user.getFlashcardSets());
                Assertions.assertNotEquals(emptyFlashcardSet, user.getFlashcardSets());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                Assertions.fail("Use case failure is unexpected.");
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
    void login3() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("George", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                Assertions.fail("Use case failure is unexpected.");
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
    void login4() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("rObErT", user.getSignedInUsername());
                Assertions.assertTrue(user.getIsAdmin());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                Assertions.fail("Use case failure is unexpected.");
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