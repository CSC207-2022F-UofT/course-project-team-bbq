package Editor.FlashcardSet.screens;

import Editor.FlashcardSet.FCSetEditorResponseModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FCSetEditorScreen extends JPanel implements ActionListener {
    FCSetEditorController controller;
    JFrame editPage;
    FlashcardSetDsRequestModel flashcardSet;
    JTextField termText;
    JTextField definitionText;


    public FCSetEditorScreen(FCSetEditorController controller, FlashcardSetDsRequestModel flashcardSet, JFrame editPage){
        this.controller = controller;
        this.editPage = editPage;
        this.flashcardSet = flashcardSet;

        JPanel termPanel = new JPanel();
        JLabel termLabel = new JLabel("Title: ");
        termText = new JTextField(flashcardSet.getTitle());
        termPanel.add(termLabel);
        termPanel.add(termText);

        JPanel definitionPanel = new JPanel();
        JLabel definitionLabel = new JLabel("Description: ");
        definitionText = new JTextField(flashcardSet.getDescription());
        definitionPanel.add(definitionLabel);
        definitionPanel.add(definitionText);

        JPanel buttons = new JPanel();
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(this);
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
            editPage.dispose();
        }
        else{
            int numId = flashcardSet.getFlashcardSetId();
            String newTerm = termText.getText();
            String newDefinition = definitionText.getText();
            try{
                FCSetEditorResponseModel newFlashcardSet = controller.edit(numId, newTerm, newDefinition);
                String message = "Edited FlashcardSet: " + newFlashcardSet.getTitleEdit() + " : " + newFlashcardSet.getDescriptionEdit();
                JOptionPane.showMessageDialog(this, message);
                editPage.dispose();
            }
            catch (Exception error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
    }
}