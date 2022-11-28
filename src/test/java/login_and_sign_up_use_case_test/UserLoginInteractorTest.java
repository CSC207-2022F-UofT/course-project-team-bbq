package login_and_sign_up_use_case_test;

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

import static org.junit.jupiter.api.Assertions.fail;

class UserLoginInteractorTest {

    // 1) UserLoginInteractorTest and prerequisite objects
    IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(
            "src/test/java/login_and_sign_up_use_case_test/test_data/Flashcards.csv");
    IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(
            "src/test/java/login_and_sign_up_use_case_test/test_data/FlashcardSets.csv");
    IUserDataAccess userGateway = new CommonUserDataAccess(
            "src/test/java/login_and_sign_up_use_case_test/test_data/LoginUsers.csv");


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
    void login2() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
                HashMap<Integer, String[]> emptyFlashcardSet = new HashMap<>();
                Map<Integer, String[]> notEmptyFlashcardSet = new HashMap<>();
                String[] firstSet = new String[] {"test set", "for testing study use case"};
                String[] secondSet = new String[] {"empty test set", "for testing study use case with empty set"};
                notEmptyFlashcardSet.put(0, firstSet);
                notEmptyFlashcardSet.put(1, secondSet);

                Assertions.assertEquals("Walt", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertEquals(notEmptyFlashcardSet.get(0)[0], user.getFlashcardSets().get(0)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(0)[1], user.getFlashcardSets().get(0)[1]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(1)[0], user.getFlashcardSets().get(1)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(1)[1], user.getFlashcardSets().get(1)[1]);
                Assertions.assertEquals(notEmptyFlashcardSet.size(), user.getFlashcardSets().size());
                Assertions.assertNotEquals(emptyFlashcardSet, user.getFlashcardSets());
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
    void login3() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                Assertions.assertEquals("George", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertEquals(41, user.getFlashcardSets().size());
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
    void login4() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {

                HashMap<Integer, String[]> emptyFlashcardSet = new HashMap<>();
                Map<Integer, String[]> notEmptyFlashcardSet = new HashMap<>();
                String[] zeroethSet = new String[] {"test set", "for testing study use case"};
                String[] firstSet = new String[] {"empty test set", "for testing study use case with empty set"};
                String[] secondSet = new String[] {"tester", "tester thing"};
                String[] thirdSet = new String[] {"test set3", "for testing login use case3"};
                String[] thirtiethSet = new String[] {"test set30", "for testing login use case30"};
                String[] ninetiethSet = new String[] {"test set90", "for testing login use case90"};
                notEmptyFlashcardSet.put(0, zeroethSet);
                notEmptyFlashcardSet.put(1, firstSet);
                notEmptyFlashcardSet.put(2, secondSet);
                notEmptyFlashcardSet.put(3, thirdSet);
                notEmptyFlashcardSet.put(4, thirtiethSet);
                notEmptyFlashcardSet.put(5, ninetiethSet);
                int setSize = user.getFlashcardSets().size();
                int tempSetSize = notEmptyFlashcardSet.size();

                Assertions.assertEquals("Ch3is", user.getSignedInUsername());
                Assertions.assertNotEquals("rObErT", user.getSignedInUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertNotEquals(emptyFlashcardSet, user.getFlashcardSets());
                Assertions.assertEquals(tempSetSize-1, setSize);
                Assertions.assertEquals(notEmptyFlashcardSet.get(0)[0], user.getFlashcardSets().get(0)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(0)[1], user.getFlashcardSets().get(0)[1]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(1)[0], user.getFlashcardSets().get(1)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(1)[1], user.getFlashcardSets().get(1)[1]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(3)[0], user.getFlashcardSets().get(3)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(3)[1], user.getFlashcardSets().get(3)[1]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(4)[0], user.getFlashcardSets().get(30)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(4)[1], user.getFlashcardSets().get(30)[1]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(5)[0], user.getFlashcardSets().get(90)[0]);
                Assertions.assertEquals(notEmptyFlashcardSet.get(5)[1], user.getFlashcardSets().get(90)[1]);
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
                "Ch3is", "EVans!@#$");

        interactor.login(inputData);
    }

    @Test
    void login5() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
                Assertions.assertEquals("Steve", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertEquals(9, user.getFlashcardSets().size());
                userGateway.saveFlashcardSetID(user.getSignedInUsername(), 90);

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
                "Steve", "Apple123");

        interactor.login(inputData);
    }

    @Test
    void login6() {
        UserLoginOutputBoundary presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
                Assertions.assertEquals("Steve", user.getSignedInUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertEquals(10, user.getFlashcardSets().size());
                userGateway.deleteFlashcardSetID(user.getSignedInUsername(), 90);

                System.out.println(user.getFlashcardSets().size());

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
        "Steve", "Apple123");

        interactor.login(inputData);
    }

}