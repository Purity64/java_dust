package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.btn.RoundeBtn;

public class OverModhod {
    public static KeyAdapter getNumericKeyFilter() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        };
    }

    public static void reSetHoverHistory(){
        int id = Config.show_btn_key;
        if (id >= 0) {
            Set<Integer> lisSet = Config.history_hover.get(id);

            for(Integer index : lisSet){
                RoundeBtn btn = Config.main_btn.get(index);

                btn.setisActivate(false);
                btn.resetBackground();
            }
        }
        Config.is_show_history = false;
        Config.show_btn_key = -1;


    }

    public static void reSetHistoryBtn(){
        Map<Integer, RoundeBtn> main_btn = Config.main_btn;
        Map<Integer, RoundeBtn> new_btn = Config.CopyMain_btn;


        if (new_btn == null || main_btn == null) return;
        for (int i = 0; i < new_btn.size(); i++) {
            RoundeBtn Mbtn = main_btn.get(i);
            RoundeBtn Nbtn = new_btn.get(i);

            if (Mbtn != null) {
                if (Nbtn != null) {
                    int oldpm = Mbtn.getPm();
                    int newpm = Nbtn.getPm();
                    
                    if (oldpm != newpm) {  
                        
                        Mbtn.setPm(newpm);
                        Mbtn.setOldPm(Nbtn.getOldPm());
                        Mbtn.setOldBadperdon_per(Nbtn.getOldBadperdon_per());
                
                    } 
                } 
                Mbtn.setisActivate(false);
                Mbtn.resetBackground(); 
            }
        }

    }
}

