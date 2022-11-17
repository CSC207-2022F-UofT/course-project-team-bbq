package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;

    public Card(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.PINK);

        this.setBounds(x, y, width, height);
    }
}
