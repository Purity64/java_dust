package util;

import java.awt.*;
import javax.swing.*;

public class RoundedTextField extends JTextField {
    private int cornerRadius;
    private int shadowSize = 4;
    private boolean onShadow = false;
    private Color shadowColor = new Color(0, 0, 0, 50);
    private Color borderColor = new Color(0xCCCCCC);

    public RoundedTextField(int columns, int radius) {
        this("", columns, radius, false, 0);
    }


    public RoundedTextField( String text ,int columns, int radius) {
        this(text, columns, radius, false, 0);
    }
    public RoundedTextField(String text, int columns, int radius, boolean onShadow, int shadowSize) {
        super(text, columns);
        this.cornerRadius = radius;
        this.onShadow = onShadow;
        this.shadowSize = shadowSize;

        setOpaque(false); 
        setBackground(Color.WHITE);
        setForeground(new Color(0x333333));
        setCaretColor(Color.BLACK); 

        int padY = 6;
        int padX = radius / 2 + 4;
        int bottomPad = onShadow ? padY + shadowSize : padY;
        setBorder(BorderFactory.createEmptyBorder(padY, padX, bottomPad, padX));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int fieldWidth = onShadow ? width - shadowSize : width - 1;
        int fieldHeight = onShadow ? height - shadowSize : height - 1;

        if (onShadow) {
            g2.setColor(shadowColor);
            g2.fillRoundRect(
                shadowSize / 2, 
                shadowSize, 
                fieldWidth, 
                fieldHeight, 
                cornerRadius, 
                cornerRadius
            );
        }


        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, fieldWidth, fieldHeight, cornerRadius, cornerRadius);

        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, fieldWidth, fieldHeight, cornerRadius, cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
}