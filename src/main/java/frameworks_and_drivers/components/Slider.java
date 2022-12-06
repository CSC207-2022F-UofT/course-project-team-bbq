package frameworks_and_drivers.components;

import javax.swing.*;
import javax.swing.event.ChangeListener;

/**
 * GUI slider with more functionality.
 * Frameworks & Drivers
 * @author Anthony
 */
public class Slider extends JPanel {
    public final JSlider slider;

    private JLabel title;
    private final JLabel valueText;

    /**
     * Constructs a slider.
     * @param min the minimum slider value
     * @param max the maximum slider value
     * @param value the starting slider value
     * @param tickSpacing the major tick spacing
     * @param title the slider title
     */
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

    /**
     * Updates the value text label based on the current slider value.
     */
    public void updateValue() {
        this.valueText.setText(String.valueOf(this.slider.getValue()));
    }

    /**
     * Gets the slider's current value.
     * @return the slider's value
     */
    public int getValue() {
        return this.slider.getValue();
    }

    /**
     * Adds a change listener to the slider.
     * @param listener the change listener
     */
    public void addChangeListener(ChangeListener listener) {
        this.slider.addChangeListener(listener);
    }

    /**
     * Sets the title.
     * @param title the title
     */
    public void setTitle(JLabel title) {
        this.title = title;
    }
}
