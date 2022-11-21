package login_and_sign_up_use_case;

import entities.CommonUser;
import entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserUnitTest {
    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        User user = new CommonUser("Lucas", "123", false);

        Assertions.assertFalse(user.passwordIsValid());
    }

    @Test
    void given12345Password_whenPasswordIsValid_thenIsTrue() {
        User user = new CommonUser("Justin", "123456", false);

        Assertions.assertTrue(user.passwordIsValid());
    }

    @Test
    void givenAdminUserTrue_whenIsntAdmin_thenIsFalse() {
        User user = new CommonUser("Thomas", "12345", false);

        Assertions.assertFalse(user.getIsAdmin());
    }

    @Test
    void getAdminLevel() {
        User user = new CommonUser("Anthony", "12345", true);

        Assertions.assertTrue(user.getIsAdmin());
    }

    @Test
    void getListOfFlashcardSetIds() {
        User user = new CommonUser("Edward", "12345", false);
        ArrayList<Integer> check = new ArrayList<>();
        Assertions.assertEquals(user.getFlashcardSetIds(), check);
    }

    @Test
    void adminKeyValidChecker() {
        User user = new CommonUser("Junyu", "12345", false);
        Assertions.assertTrue(user.adminKeyValid("BuiltDifferent"));
    }

    @Test
    void adminKeyValidChecker2() {
        User user = new CommonUser("Winston", "12345", false);
        Assertions.assertFalse(user.adminKeyValid(" "));
    }

}
