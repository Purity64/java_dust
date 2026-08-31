package util.btn;

import java.awt.*;
import src.Config;

import javax.swing.*;

public abstract class MainBtn extends JButton  {
    protected boolean onShadow ;
    private int shadowSize = 0;
    private Color shadowColor = new Color(0, 0, 0, 50); 
    private int cornerRadius;

    MainBtn(){
        setContentAreaFilled(false); 
        setBorderPainted(false);    
        setFocusPainted(false);  
        setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
    }

    MainBtn(String pm){
        this(); 
        setText(pm);
    }

    protected void setShadow(boolean onShadow , int shadowSize){ 
        this.onShadow = onShadow;
        this.shadowSize = shadowSize;
    }

    protected void setRounded(int cornerRadius){
        this.cornerRadius = cornerRadius;
    }

    public void setReSize(int width , int height){
        setPreferredSize(new Dimension(width , height));
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int btnWidth = onShadow ? width - shadowSize : width;
        int btnHeight = onShadow ? height - shadowSize : height;

        if (onShadow) {
            g2.setColor(shadowColor);
            g2.fillRoundRect(
                shadowSize / 2, 
                shadowSize, 
                btnWidth, 
                btnHeight, 
                cornerRadius, 
                cornerRadius
            );
        }

        Color bg = getBackground() != null ? getBackground() : Color.WHITE;
        if (!Config.btn_togat_rain_make && getModel().isPressed()) {
            g2.setColor(bg.darker());
        } else if (!Config.btn_togat_rain_make && getModel().isRollover()) {
            g2.setColor(bg.brighter());
        } else {
            g2.setColor(bg); 
        }

        g2.fillRoundRect(0, 0, btnWidth, btnHeight, cornerRadius, cornerRadius);
        g2.dispose();

        super.paintComponent(g);
    }
}
