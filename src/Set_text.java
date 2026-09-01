package src;

import java.awt.Component;
import java.util.Map;

import javax.swing.JLabel;

import util.btn.RoundeBtn;

public class Set_text {
    private Map<Integer, RoundeBtn> btn_list;
    private Map<String, Component> componentMap;
    public Set_text(Map<String, Component>  componentMap, Map<Integer, RoundeBtn> btn_list ){
        this.btn_list = btn_list;
        this.componentMap = componentMap;
    }
    
    public void setDetail_data(){

        JLabel title = swiptype_as_Jlable(componentMap.get("lbl_detail_title")); 
        JLabel pm_val = swiptype_as_Jlable(componentMap.get("lbl_pm_val")); 
        JLabel person = swiptype_as_Jlable(componentMap.get("lbl_total_val")); 
        JLabel lbl_healthy_val = swiptype_as_Jlable(componentMap.get("lbl_healthy_val")); 
        JLabel lbl_sick_val = swiptype_as_Jlable(componentMap.get("lbl_sick_val")); 
        JLabel lbl_per_val = swiptype_as_Jlable(componentMap.get("lbl_ratio_val")); 

        int index =  Config.activate_btn;
                

        int person_val = 0;
        int badoerson = 0 ;
        int healthy = 0;
        int pm  = 0;
        int BadPerson_pser = 0;
        int oldpm = 0;
        int oldBadperson_per = 0;
        if (index >= 0) {
            RoundeBtn btn_click = btn_list.get(index);
            if (btn_click != null) {
                person_val = btn_click.getPerson();
                badoerson = btn_click.GetBadPerson();
                healthy = person_val - badoerson;
                pm = btn_click.getPm();
                oldpm = btn_click.getOldPm();
                BadPerson_pser = btn_click.GetBadPerson_per();
                oldBadperson_per = btn_click.getOldBadperdon_per();


                title.setText("รายละเอียดพื้นที่ : " + btn_click.getName());
                if (Config.is_show_history) {
                    if (btn_click.isActivate()) {
                        if (oldpm < 0) {
                            pm_val.setText(pm + " -> 0 pm");
                        } else {
                            pm_val.setText( pm + " -> " + oldpm + " pm");
                        }


                        int olt_bad_person_val = (int) Math.round((double) person_val * oldBadperson_per / 100.0);
                        int olt_healthy =  person_val - olt_bad_person_val;
                        person.setText(  person_val  + " คน");
                        lbl_sick_val.setText( olt_bad_person_val + " -> " + badoerson  + " คน");
                        lbl_healthy_val.setText( olt_healthy + " -> " + healthy  + " คน");
                        lbl_per_val.setText(oldBadperson_per + " -> " + BadPerson_pser  + " %");
                    }else{
                        if (pm < 0) {
                            pm_val.setText("0 pm");
                        } else {
                            pm_val.setText( pm + " pm");
                        }
                    }

                }else{
                    if (pm < 0) {
                        pm_val.setText("0 pm");
                    } else {
                        pm_val.setText(pm + " pm");
                    }
                }

            }
        } else {
            int sumperson = 0;
            int sumbadoerson = 0;
            int sumpm = 0;
            int validPmCount = 0; 

            for (int i = 0; i < btn_list.size(); i++) {
                RoundeBtn btn = btn_list.get(i);
                if (btn != null) {
                    sumperson += btn.getPerson();
                    sumbadoerson += btn.GetBadPerson();

                    int currentPm = btn.getPm();
                    if (currentPm >= 0) { 
                        sumpm += currentPm;
                        validPmCount++;
                    }
                }
            }

            person_val = sumperson;
            badoerson = sumbadoerson;
            healthy = sumperson - sumbadoerson;

            if (sumperson > 0) {
                BadPerson_pser = (int) Math.round(((double) sumbadoerson / sumperson) * 100.0);
            } else {
                BadPerson_pser = 0;
            }

            title.setText("รายละเอียดพื้นที่ : ภาพรวมทั้งหมด");

            if (validPmCount > 0) {
                double avgPm = (double) sumpm / validPmCount;
                pm_val.setText(String.format("%.1f pm avg", avgPm));
            } else {
                pm_val.setText("0 pm avg");
            }
        }

        
        if (!Config.is_show_history) {
            person.setText(String.valueOf(person_val)  + " คน");
            lbl_sick_val.setText(String.valueOf(badoerson)  + " คน");
            lbl_healthy_val.setText(String.valueOf(healthy)  + " คน");
            lbl_per_val.setText(String.valueOf(BadPerson_pser)  + " %");

        }
        

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
