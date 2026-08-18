package src;

import java.awt.Component;
import java.util.Map;

import javax.swing.JLabel;

import util.RoundeBtn;

public class Set_text {
    private Map<Integer, RoundeBtn> btn_list;
    private Map<String, Component> componentMap;
    public Set_text(Map<String, Component>  componentMap, Map<Integer, RoundeBtn> btn_list ){
        this.btn_list = btn_list;
        this.componentMap = componentMap;
    }
    
    public void setDetail_data(){
        int index =  Config.activate_btn;
        RoundeBtn btn_click = btn_list.get(index);
        JLabel title = swiptype_as_Jlable(componentMap.get("lbl_detail_title")); 
        JLabel pm_val = swiptype_as_Jlable(componentMap.get("lbl_pm_val")); 
        JLabel person = swiptype_as_Jlable(componentMap.get("lbl_total_val")); 
        JLabel lbl_healthy_val = swiptype_as_Jlable(componentMap.get("lbl_healthy_val")); 
        JLabel lbl_sick_val = swiptype_as_Jlable(componentMap.get("lbl_sick_val")); 
        JLabel lbl_per_val = swiptype_as_Jlable(componentMap.get("lbl_ratio_val")); 

        int person_val = btn_click.getPerson();
        int badoerson = btn_click.GetBadPerson();
        int healthy = person_val - badoerson;
        int pm = btn_click.getPm();
        title.setText("รายละเอียดพื้นที่ : " + btn_click.getName());
        if (pm == -1) {
            pm_val.setText("0 pm");
        }else{
            pm_val.setText(String.valueOf(pm) + " pm");
        }
        
        person.setText(String.valueOf(person_val)  + " คน");
        lbl_sick_val.setText(String.valueOf(badoerson)  + " คน");
        lbl_healthy_val.setText(String.valueOf(healthy)  + " คน");
        lbl_per_val.setText(String.valueOf(btn_click.GetBadPerson_per())  + " %");
        resetallBackGround();
    }

    public void resetallBackGround(){
        for(int i = 0 ; i < 800 ; i++){
            RoundeBtn btn_click = btn_list.get(i);
            if (btn_click != null) {
                btn_click.resetBackground();
            }
        }
    }

    public JLabel swiptype_as_Jlable(Component com){
        if (com instanceof JLabel) {
            return (JLabel) com;
        }
        return null;
    }
}
