package login_and_sign_up_use_case_test;

import data_access_use_case.*;
import entities.CommonUserFactory;
import entities.UserFactory;
import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import interface_adapters.presenters.UserRegisterPresenter;
import login_and_signup_use_case.UserRegisterOutputBoundary;
import login_and_signup_use_case.UserRegisterInputBoundary;
import login_and_signup_use_case.UserRegisterResponseModel;
import login_and_signup_use_case.UserRegisterRequestModel;
import login_and_signup_use_case.UserRegisterInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

class UserRegisterInteractorTest {

    // 1) Create a UserRegisterInteractor and prerequisite objects
    //    (arguments for the UserRegisterInteractor constructor parameters)
    // 2) create the Input Data
    // 3) Call the use case User Input Boundary method to run the use case
    // 4) Check that the Output Data passed to the Presenter is correct
    // 5) Check that the expected changes to the data layer are there.

    // 1) UserRegisterInteractor and prerequisite objects

    IUserDataAccess userGateway = new CommonUserDataAccess(
            "src/test/java/login_and_sign_up_use_case_test/test_data/RegistrationUsers.csv");

    File file = new File("src/test/java/login_and_sign_up_use_case_test/test_data/RegistrationUsers.csv");

    DBGateway gateway = new DBGateway(null, null, userGateway);

    UserRegisterInteractorTest() throws IOException {
    }

    /**
     * Test whether the register interactor is able to create a user in the csv file
     */
    @Test
    void create() {

        // This creates an anonymous implementing class for the Output Boundary.
        UserRegisterOutputBoundary presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                // 4) Check that the Output Data and associated changes
                // are correct
                Assertions.assertEquals("Steve", user.getSignedUpUsername());
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

    /**
     * Test whether the register interactor is able to create a user in the csv file with another existing user,
     * and doesn't have any other users.
     */
    @Test
    void create2() {
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                // 4) Check that the Output Data and associated changes
                // are correct
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

        // 2) Input data — Normally created by the Controller.
        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Richard", "Virgin123", "Virgin123", "BuiltDifferent");

        // 3) Run the use case
        interactor.create(inputData);
    }

    /**
     * Test whether the register interactor is able to create a user in the csv file with multiple other
     * existing users, and doesn't have any other users.
     */
    @Test
    void create3() {
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                // 4) Check that the Output Data and associated changes
                // are correct
                Assertions.assertEquals("Tom", user.getSignedUpUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertTrue(gateway.existsByName("Richard"));
                Assertions.assertTrue(gateway.existsByName("Tom"));
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

        // 2) Input data — Normally created by the Controller.
        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Tom", "Cruise123", "Cruise123", "BuiltDifferent");

        // 3) Run the use case
        interactor.create(inputData);
    }

    /**
     * Test whether the register interactor is able to create a user in the csv file with multiple other
     * existing users, and doesn't have any other users. Also check the password is correctly stored
     */
    @Test
    void create4() {
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                // 4) Check that the Output Data and associated changes
                // are correct
                Assertions.assertEquals("Brad", user.getSignedUpUsername());
                Assertions.assertTrue(user.getIsAdmin());
                Assertions.assertNotEquals("Brad123", user.getSignedUpPassword());
                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertTrue(gateway.existsByName("Richard"));
                Assertions.assertTrue(gateway.existsByName("Tom"));
                Assertions.assertTrue(gateway.existsByName("Brad"));
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

        // 2) Input data — Normally created by the Controller.
        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Brad", "Pitt123", "Pitt123", "BuiltDifferent");

        // 3) Run the use case
        interactor.create(inputData);
    }

    /**
     * Test whether the register interactor is able to create a user in the csv file with multiple other
     * existing users, and doesn't have any other users. When the user registers with unconventional chars,
     * mix of letters and numbers.
     */
    @Test
    void register5WithMultipleExistingUsersAndPasswordTestAndUnconventionalChars(){
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                // 4) Check that the Output Data and associated changes
                // are correct
                Assertions.assertEquals("HeNr!", user.getSignedUpUsername());
                Assertions.assertFalse(user.getIsAdmin());
                Assertions.assertEquals("Superman123", user.getSignedUpPassword());
//                Assertions.assertTrue(userGateway.existsByName("Brad"));
                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertTrue(gateway.existsByName("HeNr!"));
//                Assertions.assertTrue(gateway.existsByName("Tom"));
//                Assertions.assertTrue(gateway.existsByName("Richard"));
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

        // 2) Input data — Normally created by the Controller.
        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "HeNr!", "Superman123", "Superman123", "");

        // 3) Run the use case
        interactor.create(inputData);
    }

    /**
     * Test whether the register interactor is able to create a user with the mock/temporary
     * database with multiple other existing users. Ensuring a sense of Liskov Substitution.
     */
    @Test
    void create6() {

        //Delete the previously stored users in the csv file to be able to conduct tests again
        file.delete();

        //Create a mock database
        IUserDataAccess userGateway2 = new InMemoryUser();

        DBGateway gateway2 = new DBGateway(null, null, userGateway2);
        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {

                // 4) Check that the Output Data and associated changes
                // are correct
                Assertions.assertEquals("Brad", user.getSignedUpUsername());
                Assertions.assertTrue(user.getIsAdmin());
//                Assertions.assertTrue(gateway.existsByName("Steve"));
                Assertions.assertFalse(gateway2.existsByName("Channing"));
                Assertions.assertFalse(gateway2.existsByName("Ryan"));
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
                gateway2, presenter, userFactory);

        // 2) Input data — Normally created by the Controller.
        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
                "Brad", "Pitt123", "Pitt123", "BuiltDifferent");

        // 3) Run the use case
        interactor.create(inputData);
    }

}