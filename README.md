**Welcome to Bargain Bin Quizlet (BBQ for short)!**

This is a flashcard study application inspired by Quizlet.

# Main Features

### 1a. Sign Up For The Application.
- If the user selects the "Sign Up" option from the welcome screen the user has an option of Signing up as a user with or without admin level access 
  - The user must select a username that does not previously exist in the database, if the user inputs a previously existing username they shall be warned with a failure in registration and must choose another username.
  - The password needs to be greater than or equal to 5 characters in length and not blank.
  - The repeat password must match the password entered to ensure coherency and surety.
  - If the user does not require admin level they can leave the Admin Key field blank and they shall be registered as a common user that can access teh program.
  - If the user does require admin level access they can input the Admin Key in the field, if there is an error in the inputting of the key the user shall be warned accordingly.

### 1b. Log In For The Application.
- If the user selects the "Log In" option from the welcome screen the user may input their respective username and password, if the username does not match an existing username from the database or incorrect password they shall be warned.

### 2. Create a flashcard or a flashcard set.

### 3a. Edit a flashcard set.
 - If there are no flashcard sets on the main page, then we cannot edit a flashcard set.
 - If there is a flashcard set, the user can choose which flashcard set to edit and can click the "Edit" button of the desired flashcard set.
  - When "Edit" is clicked an Editor Main Page is shown. There is an "Edit Flashcard Set" button and when clicked a window pops up with Title and Description text prompts for the user to edit. The user cannot change the Title to an empty title.
### 3b. Edit a flashcard in a flashcard set.
 - If there are no flashcard sets on the main page, then we cannot edit a flashcard in a flashcard set.
 - If there is a flashcard set, the user can choose which flashcard set to edit and can click the "Edit" button of the desired flashcard set.
  - When "Edit" is clicked an Editor Main Page is shown.
   - If there are no flashcards, the user cannot edit a flashcard.
   - If there are flashcards, the user can choose which flashcard to edit by clicking the "Edit Flashcard" button. When clicked a window pops up with Term and Definition text prompts for the user to edit. The user cannot change the Term to an empty term.

### 4. Delete a flashcard or a flashcard set.

### 5. Search for a public flashcard set.
- If the user selects the search button from the main page the user can search through the public community database for flashcard sets
    - The user types in search input, and selects tags to search from (title, description, owner), or optionally they can choose to “search all”
    - The user will be taken to a results screen where a list of all the public flashcard sets matching the user’s search will be returned, displaying the title, description, and creator for each
    - If the user is an admin, a list of all flashcard sets will be returned, regardless if the flashcard set is public or private
    - Once the user is on the results screen they are able to study or take a quiz for each flashcard set
    - If the user is an admin they also have the ability to edit and delete every flashcard set in the results

### 6. Study from a flashcard set.
-Given a flashcard set, the user can enter study mode
-The user can choose how to sort the flashcard set: by creation date, by alphabetical order, or by random shuffle
-The user can choose if they want to view the cards in order or in reverse order
-The user can choose if the flashcard displays terms or definition by default
-The user is then presented with the first flashcard in the flashcard set, given their sorting preferences. The user can choose to 
flip the flashcard, go to the next card, or go to the previous card until they wish to quit studying
### 7. Generate and take a quiz from a flashcard set.
- Given a flashcard set, the user can enter quiz mode.
- Quiz mode features three types of questions: multiple choice, text entry, and true/false.
- First, the user toggles a set of quiz settings (such as number of questions, question types, and timer).
- Then, the user submits their quiz settings and a quiz is automatically generated for them.
- After taking and submitting the quiz, the user can receive their quiz results and identify which questions they answered incorrectly.

### 8. Save user and flashcard data to the database.

# How to run the application?

Run src/main/java/Main.java.
