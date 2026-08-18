package util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedBox extends JPanel {
    private int cornerRadius;
    private int shadowSize = 0;
    private Color shadowColor = new Color(0, 0, 0, 80);

    public RoundedBox(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); 
    }

    public RoundedBox(int radius,int shadowSize) {
        this(radius);
        this.shadowSize = shadowSize;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        g2.clip(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));

        int r = shadowColor.getRed();
        int gCol = shadowColor.getGreen();
        int b = shadowColor.getBlue();
        int maxAlpha = shadowColor.getAlpha();

        for (int i = 0; i < shadowSize; i++) {
            double opacityRatio = Math.pow(1.0 - ((double) i / shadowSize), 2);
            int currentAlpha = (int) (maxAlpha * opacityRatio);

            g2.setColor(new Color(r, gCol, b, Math.max(0, currentAlpha)));
            
            int currentRadius = Math.max(0, cornerRadius - i);
            g2.drawRoundRect(i, i, width - 1 - (i * 2), height - 1 - (i * 2), currentRadius, currentRadius);
        }
        
        g2.dispose();
    }
}