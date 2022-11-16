package quizUseCase.GUI;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Slider extends JPanel {
    public JSlider slider;

    private JLabel title;
    private JLabel valueText;

    public Slider(int min, int max, int value, int tickSpacing, String title) {
        this.slider = new JSlider(min, max, value);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        this.slider.setMinorTickSpacing(1);
        this.slider.setMajorTickSpacing(tickSpacing);

        this.title = new JLabel(title);
        this.valueText = new JLabel(String.valueOf(value));

        // set layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(50,100, 0, 100));
        this.add(this.slider);
        this.add(this.title);
        this.add(this.valueText);
    }

    public void updateValue() {
        this.valueText.setText(String.valueOf(this.slider.getValue()));
    }

    public int getValue() {
        return this.slider.getValue();
    }

    public void addChangeListener(ChangeListener listener) {
        this.slider.addChangeListener(listener);
    }

    /** GETTERS AND SETTERS **/
    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getValueText() {
        return valueText;
    }

    public void setValueText(JLabel valueText) {
        this.valueText = valueText;
    }
}
