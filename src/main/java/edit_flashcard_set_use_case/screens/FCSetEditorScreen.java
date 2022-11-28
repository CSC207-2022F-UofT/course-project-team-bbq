package edit_flashcard_set_use_case.screens;

import edit_flashcard_set_use_case.FCSetEditorResponseModel;
import data_access.entity_request_models.FlashcardSetDsRequestModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FCSetEditorScreen extends JPanel implements ActionListener {
    private final FCSetEditorController controller;
    private final FlashcardSetDsRequestModel flashcardSet;
    /**
     * The main JFrame in which this editor screen lies in.
     */
    final JFrame editPage;
    /**
     * The title input field.
     */
    final JTextField titleText;
    /**
     * The definition input field.
     */
    final JTextField descriptionText;

    /**
     * Creates a new FCSetEditorScreen object with the corresponding JLabels, JPanels and JButtons.
     * @param controller A FCSetEditorController object to tell the use case layer to execute.
     * @param flashcardSet A FlashcardSetDsRequestModel object to display the current flashcard set title and
     *                     description.
     * @param editPage The main JFrame edit page.
     */
    public FCSetEditorScreen(FCSetEditorController controller, FlashcardSetDsRequestModel flashcardSet, JFrame editPage){
        this.controller = controller;
        this.editPage = editPage;
        this.flashcardSet = flashcardSet;

        //These are the components for the title input field.
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Title: ");
        titleText = new JTextField(flashcardSet.getTitle());
        titlePanel.add(titleLabel);
        titlePanel.add(titleText);
        //These are the components for the description input field.
        JPanel descriptionPanel = new JPanel();
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionText = new JTextField(flashcardSet.getDescription());
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionText);
        //These are confirm and cancel buttons.
        JPanel buttons = new JPanel();
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        buttons.add(confirm);
        buttons.add(cancel);

        this.add(titlePanel);
        this.add(descriptionPanel);
        this.add(buttons);
    }

    /**
     * Listens to user's input and when user confirms their edits, the controller that is passed in will be called to
     * edit the following flashcard set. If user cancels, we close this page.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked " + e.getActionCommand());
        if(e.getActionCommand().equals("Cancel")){
            editPage.dispose();
        }
        else{
            int numId = flashcardSet.getFlashcardSetId();
            String newTerm = titleText.getText();
            String newDefinition = descriptionText.getText();
            try{
                FCSetEditorResponseModel newFlashcardSet = controller.edit(numId, newTerm, newDefinition);
                String message = "Edited FlashcardSet: " + newFlashcardSet.getTitleEdit() + " : " + newFlashcardSet.getDescriptionEdit();
                JOptionPane.showMessageDialog(this, message);
                editPage.dispose();
            }
            //Catches the FCSetEditFailed that is thrown by the presenter.
            catch (FCSetEditFailed error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
    }
}