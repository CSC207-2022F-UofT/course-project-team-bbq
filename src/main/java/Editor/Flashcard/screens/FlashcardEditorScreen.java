package Editor.Flashcard.screens;

import Editor.Flashcard.FlashcardEditorResponseModel;
import Editor.Flashcard.screens.FlashcardEditorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardEditorScreen extends JPanel implements ActionListener {

    FlashcardEditorController controller;

    JTextField idPanel;
    JTextField termPanel;
    JTextArea definitionPanel;


    public FlashcardEditorScreen(FlashcardEditorController controller){
        this.controller = controller;

        idPanel = new JTextField("Enter id...");
        termPanel = new JTextField("Enter term...");
        definitionPanel = new JTextArea("Enter definition...");

        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        confirm.addActionListener(this);
        cancel.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(cancel);

        this.add(idPanel);
        this.add(termPanel);
        this.add(definitionPanel);
        this.add(buttons);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked " + e.getActionCommand());
        if(e.getActionCommand().equals("Cancel")){
            System.exit(0);
        }
        else{
            try{
                int numId = Integer.parseInt(idPanel.getText());
                String newTerm = termPanel.getText();
                String newDefinition = definitionPanel.getText();
                FlashcardEditorResponseModel newFlashcard = controller.edit(numId, newTerm, newDefinition);
                String message = "Edited Flashcard #" + newFlashcard.getFlashcardId() + ": " + newFlashcard.getTermEdit() + " : " + newFlashcard.getDefinitionEdit();
                JOptionPane.showMessageDialog(this, message);
            }
            catch (Exception error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
    }
}
