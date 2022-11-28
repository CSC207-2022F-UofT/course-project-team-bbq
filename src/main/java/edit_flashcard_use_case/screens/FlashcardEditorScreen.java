package edit_flashcard_use_case.screens;

import edit_flashcard_use_case.FlashcardEditorResponseModel;
import data_access.entity_request_models.FlashcardDsRequestModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardEditorScreen extends JPanel implements ActionListener {
    private final FlashcardEditorController controller;
    private final FlashcardDsRequestModel flashcard;
    /**
     * The main JFrame in which this editor screen lies in.
     */
    final JFrame editPage;
    /**
     * The term input field.
     */
    final JTextField termText;
    /**
     * The definition input field.
     */
    final JTextField definitionText;

    /**
     * Creates a new FlashcardEditorScreen object with the corresponding JLabels, JPanels and JButtons.
     * @param controller A FlashcardEditorController object to tell the use case layer to execute.
     * @param flashcard A FlashcardDsRequestModel object to display the current flashcard term and definition.
     * @param editPage The main JFrame edit page.
     */
    public FlashcardEditorScreen(FlashcardEditorController controller, FlashcardDsRequestModel flashcard, JFrame editPage){
        this.controller = controller;
        this.flashcard = flashcard;
        this.editPage = editPage;

        //These are the components for the term input field.
        JPanel termPanel = new JPanel();
        JLabel termLabel = new JLabel("Term: ");
        termText = new JTextField(flashcard.getTerm());
        termPanel.add(termLabel);
        termPanel.add(termText);
        //These are the components for the definition input field.
        JPanel definitionPanel = new JPanel();
        JLabel definitionLabel = new JLabel("Definition: ");
        definitionText = new JTextField(flashcard.getDefinition());
        definitionPanel.add(definitionLabel);
        definitionPanel.add(definitionText);
        //These are confirm and cancel buttons.
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        this.add(termPanel);
        this.add(definitionPanel);
        this.add(buttons);


    }

    /**
     * Listens to user's input and when user confirms their edits, the controller that is passed in will be called to
     * edit the following flashcard. If user cancels, we close this page.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked " + e.getActionCommand());
        if(e.getActionCommand().equals("Cancel")){
            editPage.dispose();
        }
        else{
            int numId = flashcard.getFlashcardId();
            String newTerm = termText.getText();
            String newDefinition = definitionText.getText();
            try{
                FlashcardEditorResponseModel newFlashcard = controller.edit(numId, newTerm, newDefinition);
                String message = "Edited Flashcard: " + newFlashcard.getTermEdit() + " : " + newFlashcard.getDefinitionEdit();
                JOptionPane.showMessageDialog(this, message);
                editPage.dispose();
            }
            //Catches the FlashcardEditFailed that is thrown by the presenter.
            catch (FlashcardEditFailed error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
    }
}