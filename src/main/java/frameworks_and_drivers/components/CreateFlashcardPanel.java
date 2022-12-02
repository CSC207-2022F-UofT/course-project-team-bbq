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
    CreateFlashcardController controller;
    JTextArea term_text, definition_text;
    JLabel existing_term, existing_definition;
    JFrame fcCMain;

    public CreateFlashcardPanel(CreateFlashcardController controller, JFrame fcCMain) {
        //Initiating all the sub panels.
        this.fcCMain = fcCMain;
        this.controller = controller;
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        JPanel textPanel = new JPanel(new GridLayout(2, 2));

        //Creating the button panel.
        JButton confirm = new JButton("confirm");
        JButton cancel = new JButton("cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(e -> this.fcCMain.dispose());
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);
        JLabel term_label = new JLabel("term");
        JLabel definition_label = new JLabel("definition");
        labelPanel.add(term_label);
        labelPanel.add(definition_label);

        //Creating the text panel.
        term_text = new JTextArea();
        definition_text = new JTextArea();
        term_text.setLineWrap(true);
        definition_text.setLineWrap(true);
        existing_term = new JLabel("");
        existing_definition = new JLabel("");
        textPanel.add(term_text);
        textPanel.add(definition_text);
        textPanel.add(existing_term);
        textPanel.add(existing_definition);

        //Creating the label panel.
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
            //Success view.
            CreateFlashcardResponseModel responseModel = controller.create(term_text.getText(),
                    definition_text.getText(), -1);
            if (! responseModel.existsDuplicate()) {
                successView(responseModel);
            } else {
                duplicateView(responseModel);
            }
        } catch (RuntimeException error) {
            //Failure view.
            Object[] options = {"re_edit", "back to flashcard set"};
            int action = JOptionPane.showOptionDialog(this,
                    error.getMessage(),
                    "Failure", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if (action == JOptionPane.NO_OPTION) {
                fcCMain.dispose();
            }
        }
    }

    private void successView(CreateFlashcardResponseModel responseModel){
        Object[] options = {"create another card", "back to flashcard set"};
        int action = JOptionPane.showOptionDialog(this,
                "Card created at "+ responseModel.getCreationDate().format(DateTimeFormatter.
                        ofPattern("yyyy-MM-dd HH:mm")) + "\nTerm: " + responseModel.getTerm() + "\nDefinition: " +
                        responseModel.getDefinition() + "\nid: " + responseModel.getFlashcardId(),
                "Success", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (action == JOptionPane.YES_OPTION) {
            term_text.setText("");
            definition_text.setText("");
        } else {
            fcCMain.dispose();
        }
    }
    private void duplicateView(CreateFlashcardResponseModel responseModel){
        Object[] options = {"re-edit current card", "overwrite", "back to flashcard set"};
        int action = JOptionPane.showOptionDialog(this,
                "Term already exist:\n" + responseModel.getTerm() + "\n" + responseModel.getDefinition()
                        + "\ncurrent card:\n"+ term_text.getText() + "\n" + definition_text.getText(),
                "conflict", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (action == JOptionPane.YES_OPTION) {
            existing_definition.setText("<html>existing definition :<br>" + responseModel.getDefinition() +
                    "</html>");
            existing_term.setText("<html>existing term :<br>" + responseModel.getTerm() +
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
