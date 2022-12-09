package frameworks_and_drivers.components;

import create_flashcard_use_case.CreateFlashcardResponseModel;
import interface_adapters.controllers.CreateFlashcardController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

/**
 * Panel for flashcard creator.
 * @author Junyu Chen
 */
public class CreateFlashcardPanel extends JPanel implements ActionListener {
    final CreateFlashcardController controller;
    final JTextArea term_text;
    final JTextArea definition_text;
    final JLabel existing_term;
    final JLabel existing_definition;
    final JFrame fcCMain;

    public CreateFlashcardPanel(CreateFlashcardController controller, JFrame fcCMain) {
        //Initiating all the sub panels.
        this.fcCMain = fcCMain;
        this.controller = controller;
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        JPanel textPanel = new JPanel(new GridLayout(2, 2));

        //Creating the button panel.
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(e -> this.fcCMain.dispose());
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        //Creating the label panel.
        JLabel term_label = new JLabel("Term:");
        JLabel definition_label = new JLabel("Definition:");
        labelPanel.add(term_label);
        labelPanel.add(definition_label);

        //Creating the text panel.
        term_text = new JTextArea();
        definition_text = new JTextArea();
        term_text.setLineWrap(true);
        definition_text.setLineWrap(true);
        JScrollPane term_text_sp = new JScrollPane(term_text);
        JScrollPane definition_text_sp = new JScrollPane(definition_text);
        existing_term = new JLabel("");
        existing_definition = new JLabel("");
        textPanel.add(term_text_sp);
        textPanel.add(definition_text_sp);
        textPanel.add(existing_term);
        textPanel.add(existing_definition);

        //Showing the sub panel.
        this.add(labelPanel, BorderLayout.NORTH);
        this.add(textPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1000, 500);
        this.setVisible(true);


    }

    /**
     * Response based on creation success or failure.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //Try to create new flashcard.
            CreateFlashcardResponseModel responseModel = controller.create(term_text.getText(),
                    definition_text.getText(), -1);
            //Success view.
            if (! responseModel.existsDuplicate()) {
                //No duplicate, flashcard created successfully.
                successView(responseModel);
            } else {
                //Exist duplicate term.
                duplicateView(responseModel);
            }
        } catch (RuntimeException error) {
            //Failure view.
            Object[] options = {"Re-edit", "Back to Flashcard Set"};
            int action = JOptionPane.showOptionDialog(this,
                    error.getMessage(),
                    "Failure", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (action == JOptionPane.NO_OPTION) {
                fcCMain.dispose();
            }
        }
    }

    /**
     * View of success for creation or overwrite.
     * @param responseModel response model
     */
    private void successView(CreateFlashcardResponseModel responseModel){
        Object[] options = {"Create Another Card", "Back to Flashcard Set"};
        int action = JOptionPane.showOptionDialog(this,
                "Card created at ["+ responseModel.getCreationDate().format(DateTimeFormatter.
                        ofPattern("yyyy-MM-dd HH:mm")) + "]\nTerm: " + responseModel.getTerm() + "\nDefinition: " +
                        responseModel.getDefinition() + "\nid: " + responseModel.getFlashcardId(),
                "Success", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (action == JOptionPane.YES_OPTION) {
            term_text.setText("");
            definition_text.setText("");
            existing_definition.setText("");
            existing_term.setText("");
        } else {
            fcCMain.dispose();
        }
    }

    /**
     * View for duplicate cards.
     * @param responseModel response model
     */
    private void duplicateView(CreateFlashcardResponseModel responseModel){
        Object[] options = {"Re-edit Current Card", "Overwrite", "Back to Flashcard Set"};
        int action = JOptionPane.showOptionDialog(this,
                "Term already exists:\n" + responseModel.getTerm() + "\n" + responseModel.getDefinition()
                        + "\nCurrent card:\n"+ term_text.getText() + "\n" + definition_text.getText(),
                "Conflict", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (action == JOptionPane.YES_OPTION) {
            existing_definition.setText("<html>Existing definition :<br>" + responseModel.getDefinition() +
                    "</html>");
            existing_term.setText("<html>Existing term :<br>" + responseModel.getTerm() +
                    "</html>");
        } else if (action == JOptionPane.NO_OPTION) {
            responseModel = controller.create(term_text.getText().replace("\n", " "),
                    definition_text.getText().replace("\n", " "), responseModel.getFlashcardId());
            successView(responseModel);
        } else {
            fcCMain.dispose();
        }
    }
}
