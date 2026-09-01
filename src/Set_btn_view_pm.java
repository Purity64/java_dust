package src;

import java.util.ArrayList;
import java.util.Map;

import util.btn.RoundeBtn;

public class Set_btn_view_pm {
    Map<Integer, RoundeBtn> btn_list;
    ReadFile file;
    ArrayList<Integer> pm;
    
    public Set_btn_view_pm(Map<Integer, RoundeBtn> btn_list, ReadFile file ){
        this.btn_list = btn_list;
        this.file = file;
        this.pm = file.getPM();

        
    }

    public void SetPm(){
        for (int i = 0; i < 800; i++) {
            RoundeBtn btn = this.btn_list.get(i);
            if (i < pm.size()) {
                btn.setPm(pm.get(i));
            }  else{
                btn.setPm(-1);
            }
            
        }



    }
}
