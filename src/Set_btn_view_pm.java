package src;

import java.util.Map;

import util.btn.RoundeBtn;

public class Set_btn_view_pm {
    Map<Integer, RoundeBtn> btn_list;
    ReadFile file;
    int pm[];
    
    public Set_btn_view_pm(Map<Integer, RoundeBtn> btn_list, ReadFile file ){
        this.btn_list = btn_list;
        this.file = file;
        this.pm = file.getPM();
    }

    public void SetPm(){
        for (int i = 0; i < pm.length; i++) {
            RoundeBtn btn = this.btn_list.get(i);
            btn.setPm(pm[i]);
        }

    }
}
