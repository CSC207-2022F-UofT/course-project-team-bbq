//package userRegisterAndLoginUseCaseTest;
//
//import dataAccess.IUserDataAccess;
//import entities.CommonUserFactory;
//import entities.UserFactory;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
//import loginAndSignupUseCase.*;
//import loginAndSignupUseCaseScreens.CommonUserDataAccess;
//import org.junit.jupiter.api.Test;
////import org.testing.annotations.Test;
//
////import static org.junit.jupiter.api.Assertions.*;
//import java.io.IOException;
//
//import static org.junit.Assert.*;
////import static org.testng.AssertJUnit.*;
//
//class UserRegisterInteractorTest {
//
//    @Test
//    void create() throws IOException {
//        // 1) Create a UserRegisterInteractor and prerequisite objects
//        //    (arguments for the UserRegisterInteractor constructor parameters)
//        // 2) create the Input Data
//        // 3) Call the use case User Input Boundary method to run the use case
//        // 4) Check that the Output Data passed to the Presenter is correct
//        // 5) Check that the expected changes to the data layer are there.
//
//        // 1) UserRegisterInteractor and prerequisite objects
//        IUserDataAccess userRepository = new CommonUserDataAccess();
//
//        // This creates an anonymous implementing class for the Output Boundary.
//        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
//            @Override
//            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
//                // 4) Check that the Output Data and associated changes
//                // are correct
//                assertEquals("Steve", user.getSignedUpUsername());
//                assertEquals(false, user.getIsAdmin());
//                assertTrue(userRepository.existsByName("Steve"));
//                return null;
//            }
//
//            @Override
//            public UserRegisterResponseModel prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//                return null;
//            }
//        };
//
//        UserFactory userFactory = new CommonUserFactory();
//        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
//                userRepository, presenter, userFactory);
//
//        // 2) Input data — Normally created by the Controller.
//        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
//                "Steve", "Apple123", "Apple123", "");
//
//        // 3) Run the use case
//        interactor.create(inputData);
//    }
//
//    @Test
//    void create2() throws IOException {
//
//        IUserDataAccess userRepository = new CommonUserDataAccess();
//
//        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
//            @Override
//            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
//
//                assertEquals("Richard", user.getSignedUpUsername());
//                assertEquals(true, user.getIsAdmin());
//                assertFalse(userRepository.existsByName("Steve"));
//                assertTrue(userRepository.existsByName("Richard"));
//                return null;
//            }
//
//            @Override
//            public UserRegisterResponseModel prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//                return null;
//            }
//        };
//
//        UserFactory userFactory = new CommonUserFactory();
//        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
//                userRepository, presenter, userFactory);
//
//        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
//                "Richard", "Virgin123", "Virgin123", "BuiltDifferent");
//
//        interactor.create(inputData);
//    }
//
//}