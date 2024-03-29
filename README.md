**Welcome to Bargain Bin Quizlet (BBQ for short)!**

This is a flashcard study application inspired by Quizlet.
More information on the development process: https://docs.google.com/presentation/d/1njNzKJetvinsbI11aE5h2UpoX83O-bVc-4Z1DgMm6rg/edit#slide=id.p 

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
- Flashcard Set:
  - The user can create a flashcard set by clicking the "Add Flashcard Set" button located on the main page
  - Once the button is clicked, the user is taken to the flashcard set creation screen
  - The user can then enter a title and description for the flashcard set
  - If the user clicks on "Create" with either the title or description missing, the user is alerted to include them
  - The user can also make the flashcard set Public (unchecked box) or Private (checked box)
  - Note: public flashcard sets are searchable by other users, while private ones are not (unless those users are Admins)
  - If the user wishes to cancel the creation, they can do so by clicking "Cancel"
  - Upon successful creation, the user is notified that their flashcard set has been created
- Flashcard:
  - The user opens the flashcard creator screen by clicking "Add Flashcard" button within the edit flashcard screen.
  - The user enters the term and definition in the field under the respective label and clicks "Confirm" to save the flashcard.
  - If card is saved successfully, success screen will then be displayed and user can choose to create another card or return to the edit flashcard screen.
  - If the user clicks "Cancel", the page will close the current screen and return to the edit flashcard screen.
  - The user can only enter non-empty term or definition with at least one character that is not space or line breaker.
  - If the user tries to create another card with the same term, warning will be shown.
  - The user can then choose to continue editing the current card or overwrite the existing card.

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
- Flashcard Set:
  - The user can delete a flashcard set by clicking the "Delete" button located inside a flashcard set's options box on the main page
  - When the button is clicked, the user is then taken to the flashcard set deletion screen
  - On this screen, user can cancel the deletion at any time by clicking the "Cancel" button
  - If the user clicks on "Delete", the user is presented with a confirmation dialog box
  - Selecting "No" cancels the deletion and selecting "Yes" commences the deletion process 
  - Note: deleting a flashcard set would also delete all flashcards contained within that set
  - Upon successful deletion, the user is notified that their flashcard set has been deleted
- Flashcard:
  - The user can delete a flashcard by clicking the "Delete Flashcard" button within the edit flashcard screen.
  - A confirmation page will then be shown and verify that the user wants to delete the flashcard.
  - The user can click "Confirm" to remove the flashcard or "Cancel" to cancel the deletion.
  - If successful, the success screen will show.


### 5. Search for a public flashcard set.
- If the user selects the search button from the main page the user can search through the public community database for flashcard sets
    - The user types in search input, and selects tags to search from (title, description, owner), or optionally they can choose to “search all”
    - The user will be taken to a results screen where a list of all the public flashcard sets matching the user’s search will be returned, displaying the title, description, and creator for each
    - If the user is an admin, a list of all flashcard sets will be returned, regardless if the flashcard set is public or private
    - Once the user is on the results screen they are able to study or take a quiz for each flashcard set
    - If the user is an admin they also have the ability to edit and delete every flashcard set in the results

### 6. Study from a flashcard set.
- Given a flashcard set, the user can enter study mode
- The user can choose how to sort the flashcard set: by creation date, by alphabetical order, or by random shuffle
- The user can choose if they want to view the cards in order or in reverse order
- The user can choose if the flashcard displays terms or definition by default
- The user is then presented with the first flashcard in the flashcard set, given their sorting preferences. The user can choose to 
flip the flashcard, go to the next card, or go to the previous card until they wish to quit studying
### 7. Generate and take a quiz from a flashcard set.
- Given a flashcard set, the user can enter quiz mode.
- Quiz mode features three types of questions: multiple choice, text entry, and true/false.
- First, the user toggles a set of quiz settings (such as number of questions, question types, and timer).
- Then, the user submits their quiz settings and a quiz is automatically generated for them.
- After taking and submitting the quiz, the user can receive their quiz results and identify which questions they answered incorrectly.

# How to run the application?

Run src/main/java/Main.java.
