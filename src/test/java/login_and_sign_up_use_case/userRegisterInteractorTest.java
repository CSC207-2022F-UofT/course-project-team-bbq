package login_and_sign_up_use_case;

import dataAccess.*;
import entities.CommonUserFactory;
import entities.UserFactory;
import login_and_signup_use_case.login_and_signup_use_case_screens.UserRegisterPresenter;
import login_and_signup_use_case.UserRegisterOutputBoundary;
import login_and_signup_use_case.UserRegisterInputBoundary;
import login_and_signup_use_case.UserRegisterResponseModel;
import login_and_signup_use_case.UserRegisterRequestModel;
import login_and_signup_use_case.UserRegisterInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

class UserRegisterInteractorTest {

    // 1) UserRegisterInteractor and prerequisite objects

    IUserDataAccess userGateway = new CommonUserDataAccess(
            "src/test/java/login_and_sign_up_use_case/test_data/Users.csv");

    DBGateway gateway = new DBGateway(null, null, userGateway);

    UserRegisterInteractorTest() throws IOException {
    }

    @Test
    void create() throws IOException {
        // 1) Create a UserRegisterInteractor and prerequisite objects
        //    (arguments for the UserRegisterInteractor constructor parameters)
        // 2) create the Input Data
        // 3) Call the use case User Input Boundary method to run the use case
        // 4) Check that the Output Data passed to the Presenter is correct
        // 5) Check that the expected changes to the data layer are there.

        // This creates an anonymous implementing class for the Output Boundary.
        UserRegisterOutputBoundary presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                // 4) Check that the Output Data and associated changes
                // are correct
                Assertions.assertEquals("Steve", user.getSignedUpUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertTrue(gateway.existsByName("Steve"));
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new CommonUserFactory();
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                gateway, presenter, userFactory);

        // 2) Input data — Normally created by the Controller.
        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Steve", "Apple123", "Apple123", "");

        // 3) Run the use case
        interactor.create(inputData);
    }

    @Test
    void create2() throws IOException {
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                Assertions.assertEquals("Richard", user.getSignedUpUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertTrue(gateway.existsByName("Richard"));
                Assertions.assertFalse(gateway.existsByName("Daquan"));
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new CommonUserFactory();
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                gateway, presenter, userFactory);

        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Richard", "Virgin123", "Virgin123", "BuiltDifferent");

        interactor.create(inputData);
    }

    @Test
    void create3() throws IOException {
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                Assertions.assertEquals("Tom", user.getSignedUpUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertTrue(gateway.existsByName("Richard"));
                Assertions.assertFalse(gateway.existsByName("Daquan"));
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new CommonUserFactory();
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                gateway, presenter, userFactory);

        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Tom", "Cruise123", "Cruise123", "BuiltDifferent");

        interactor.create(inputData);
    }

    @Test
    void create4() throws IOException {
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                Assertions.assertEquals("Brad", user.getSignedUpUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertTrue(gateway.existsByName("Richard"));
                Assertions.assertFalse(gateway.existsByName("Daquan"));
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        UserFactory userFactory = new CommonUserFactory();
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                gateway, presenter, userFactory);

        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Brad", "Pitt123", "Pitt123", "BuiltDifferent");

        interactor.create(inputData);
    }

}