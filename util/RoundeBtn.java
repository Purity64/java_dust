package util;

import java.awt.*;
import src.Config;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RoundeBtn extends JButton {
    private int cornerRadius;
    private int shadowSize = 4; 
    private boolean onShadow = false;
    private Color shadowColor = new Color(0, 0, 0, 50); 

    private int x_location = -1;
    private int y_location  = -1;

    private int min_pm = 0;
    private int max_pm = 250;
    private int pm = -1;

    private boolean isRandom = false;

    private int person = 0;
    private int badperson = 0;
    private int badperson_per  = 0;

    private String name;
    private int location = -1;
    

    public RoundeBtn(String pm, int radius) {
        this(pm, radius, false);
    }

    public RoundeBtn(String pm, int radius, boolean isRandom) {
        super(pm);
        try {
            this.pm = Integer.parseInt(pm) - 1;
        } catch (NumberFormatException e) {
            this.pm = -1; 
        }
        this.cornerRadius = radius;
        this.isRandom = isRandom;

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setBackground(Color.WHITE);
        setOpaque(false);
        setFont(new Font("Leelawadee UI", Font.PLAIN, 20));

        if (this.isRandom) {
            this.person = 10000;
            calculatePerson();
        }
    }

    public RoundeBtn(String pm, int radius, boolean onShadow, int shadowSize) {
        this(pm, radius, false);
        this.shadowSize = shadowSize;
        this.onShadow = onShadow;
    }

    public void setShadow(Boolean isShadow){
        this.onShadow = isRandom;
    }
    public void calculatePerson() {
        int max = 0, min = 0;
        if (this.pm > 150 ) {
            max = 50;
            min = 30;
        } else if (this.pm > 100) {
            max = 29;
            min = 20;
        } else if (this.pm > 50) {
            max = 19;
            min = 10;
        } else if (this.pm >= this.min_pm) {
            max = 9;
            min = 0;
        }

        int pm_per = ThreadLocalRandom.current().nextInt(min, max + 1);
        this.badperson = (int) Math.round((double) this.person * pm_per / 100.0);
        this.badperson_per = pm_per ;
        if(!Config.btn_togat_rain_make) resetBackground();
    }

    public void resetBackground() {
        if ( this.name == "btn_center_Rain_make") {
            if (Config.btn_togat_rain_make ) {
                setBackground(Color.blue);
            }else{
                setBackground(Color.white);
            }
            return;
        }

        if (this.person <= 0) return;

        double per_badPerson = ((double) this.badperson / this.person) * 100.0;
        if (this.location == Config.activate_btn) {
            setBackground(Color.blue);
        }else if (per_badPerson >= 30.0) {
            setBackground(new Color(0xFF5555)); 
        } else if (per_badPerson >= 20.0) {
            setBackground(new Color(0xFF9933)); 
        } else if (per_badPerson >= 10.0) {
            setBackground(new Color(0xFFDD44)); 
        } else if(per_badPerson >= 0.0) {
            setBackground(new Color(0x66CC66)); 
            if (this.pm == -1) {
                setBackground(Color.white);
            }
        }




    }

    public void setPm(int newPm) {
        this.pm = newPm;
        setText(String.valueOf(newPm));
        if (isRandom) {
            calculatePerson();
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setIndex(int index){
        this.location = index;
    }

    public int getIndex() {
        return this.location;
    }

    public void setPerson(int person){
        this.person = person;
    }
    public int getPerson(){
        return this.person;
    }

    public int GetBadPerson(){
        return this.badperson;
    }

    public int GetBadPerson_per(){
        return this.badperson_per;
    }

    public int getPm(){
        return this.pm;
    }

    public void setX_location(int x){
        this.x_location = x;
    }

    public void setY_location(int y){
        this.y_location = y;
    }

    public int getX_location(){
        return this.x_location;
    }

    public int getY_location(){
        return this.y_location;
    }

    @Override
    protected void paintComponent(Graphics g) {
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