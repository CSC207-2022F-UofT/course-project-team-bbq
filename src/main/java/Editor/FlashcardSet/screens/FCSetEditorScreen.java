package Editor.FlashcardSet.screens;

import Editor.FlashcardSet.FCSetEditorResponseModel;
import Editor.FlashcardSet.screens.FCSetEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FCSetEditorScreen extends JPanel implements ActionListener {
    FCSetEditorController controller;

    JTextField idPanel;
    JTextField termPanel;
    JTextArea definitionPanel;


    public FCSetEditorScreen(FCSetEditorController controller){
        this.controller = controller;

        idPanel = new JTextField("Enter id...");
        termPanel = new JTextField("Enter title...");
        definitionPanel = new JTextArea("Enter description...");

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
                FCSetEditorResponseModel newFlashcard = controller.edit(numId, newTerm, newDefinition);
                String message = "Edited Flashcard #" + newFlashcard.getFlashcardSetId() + ": " + newFlashcard.getTitleEdit() + " : " + newFlashcard.getDescriptionEdit();
                JOptionPane.showMessageDialog(this, message);
            }
            catch (Exception error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
    }
}
