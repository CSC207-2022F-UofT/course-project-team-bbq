package Editor.Flashcard.screens;

import Editor.Flashcard.FlashcardEditorResponseModel;
import EditorMainPage.FlashcardInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardEditorScreen extends JPanel implements ActionListener {
    FlashcardEditorController controller;
    FlashcardInfo flashcard;
    JFrame application;
    JTextField termText;
    JTextField definitionText;


    public FlashcardEditorScreen(FlashcardEditorController controller, FlashcardInfo flashcard, JFrame application){
        this.controller = controller;
        this.flashcard = flashcard;
        this.application = application;

        JPanel termPanel = new JPanel();
        JLabel termLabel = new JLabel("Term: ");
        termText = new JTextField(flashcard.getTerm());
        termPanel.add(termLabel);
        termPanel.add(termText);

        JPanel definitionPanel = new JPanel();
        JLabel definitionLabel = new JLabel("Definition: ");
        definitionText = new JTextField(flashcard.getDefinition());
        definitionPanel.add(definitionLabel);
        definitionPanel.add(definitionText);

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


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked " + e.getActionCommand());
        if(e.getActionCommand().equals("Cancel")){
            application.dispose();
        }
        else{
            try{
                int numId = flashcard.getId();
                String newTerm = termText.getText();
                String newDefinition = definitionText.getText();
                FlashcardEditorResponseModel newFlashcard = controller.edit(numId, newTerm, newDefinition);
                String message = "Edited Flashcard" + ": " + newFlashcard.getTermEdit() + " : " + newFlashcard.getDefinitionEdit();
                JOptionPane.showMessageDialog(this, message);
                application.dispose();
            }
            catch (Exception error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
    }
}