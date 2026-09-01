package util.btn;

import java.awt.*;
import src.Config;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingConstants;

public class RoundeBtn extends MainBtn {

    

    private int x_location = -1;
    private int y_location  = -1;

    private int min_pm = 0;
    private int pm = -1;

    private boolean isRandom = false;

    private int person = 0;
    private int badperson = 0;
    private int badperson_per  = 0;

    private String name;
    private int location = -1;

    private int OldPm;
    private int OldBadperdon_per;

    private boolean isActivate = false;
    

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
        super.setRounded(radius);
        this.isRandom = isRandom;

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setBackground(Color.WHITE);
        setOpaque(false);
        

        if (this.isRandom) {
            this.person = 3000;
            calculatePerson();
        }
    }

    public RoundeBtn(String pm, int radius, boolean onShadow, int shadowSize) {
        this(pm, radius, false);
        super.setShadow(onShadow, shadowSize);
    }

    public void setShadow(Boolean isShadow){
        this.onShadow = isShadow;
    }
    public void calculatePerson() {
        int max = 0, min = 0;
        int pm_per = 0;
        if (this.pm == 0) {
            
        }else{
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

            pm_per = ThreadLocalRandom.current().nextInt(min, max + 1);
        }

 
        this.badperson = (int) Math.round((double) this.person * pm_per / 100.0);
        this.badperson_per = pm_per ;
        if(!Config.btn_togat_rain_make) resetBackground();
    }

    public void resetBackground() {
        if (isActivate) {
            setBackground(Color.BLUE);
            return;
        }
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

    public void setOldPm(int OldPm){
        this.OldPm = OldPm;
    }

    public int getOldPm(){
        return this.OldPm;
    }

    public void setisActivate(boolean status){
        this.isActivate = status;
    }

    public boolean isActivate(){
        return this.isActivate;
    }

    public void setOldBadperdon_per(int OldBadperdon_per){
        this.OldBadperdon_per = OldBadperdon_per;
    }

    public int getOldBadperdon_per(){
        return this.OldBadperdon_per;
    }


}