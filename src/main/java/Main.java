import com.formdev.flatlaf.FlatDarculaLaf;

import login_and_signup_use_case.login_and_signup_use_case_screens.WelcomeScreen;

import java.io.IOException;

/**
 * Run the main class to run the application.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // ONLY UNCOMMENT ONE THEME!!
        // FlatIntelliJLaf.setup(); // light mode
        FlatDarculaLaf.setup(); // dark mode
        
        new WelcomeScreen();
    }
}
