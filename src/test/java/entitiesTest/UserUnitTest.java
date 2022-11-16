package entitiesTest;

import entities.CommonUser;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserUnitTest {
    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        User user = new CommonUser("Lucas", "123", false);

        assertFalse(user.passwordIsValid());
    }

    @Test
    void given12345Password_whenPasswordIsValid_thenIsTrue() {
        User user = new CommonUser("Justin", "123456", false);

        assertTrue(user.passwordIsValid());
    }

    @Test
    void givenAdminUserTrue_whenIsntAdmin_thenIsFalse() {
        User user = new CommonUser("Thomas", "12345", false);

        assertFalse(user.getIsAdmin());
    }

    @Test
    void getAdminLevel() {
        User user = new CommonUser("Anthony", "12345", true);

        assertEquals(user.getIsAdmin(), true);
    }

    @Test
    void getListOfFlashcardSetIds() {
        User user = new CommonUser("Edward", "12345", false);
        ArrayList<Integer> check = new ArrayList<Integer>();
        assertEquals(user.getFlashcardSetIds(), check);
    }



}
