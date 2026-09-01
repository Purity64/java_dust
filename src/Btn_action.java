package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;

import util.Alert;
import util.History_screen;
import util.RoundedTextField;
import util.btn.RoundeBtn;

public class Btn_action {
    private Map<Integer, RoundeBtn> btn_list;
    private Map<String, Component> componentMap;
    private List<RoundeBtn> activeHoverBtns = new ArrayList<>();
    private boolean isActivate_togget_swite_mode = true; 

    private RoundeBtn dustBtn ;
    private RoundeBtn personalBtn ;
    public Btn_action(Map<String, Component>  componentMap, Map<Integer, RoundeBtn> btn_list  , Set_text set_text){
        this.btn_list = btn_list;
        this.componentMap = componentMap;

        //เปลี่ยนโหมดแสดง ข้อมูล
        dustBtn = switchType(GetComponent("Box_in_Right_Top_BoxManu_btn_left"));
        personalBtn = switchType(GetComponent("Box_in_Right_Top_BoxManu_btn_right"));
        resetbackground_swait_mode(dustBtn , personalBtn);

        dustBtn.addActionListener(e -> {
            isActivate_togget_swite_mode = true;
            resetbackground_swait_mode(dustBtn , personalBtn);
            resettext_swait_mode();

        });

        personalBtn.addActionListener(e -> {
            isActivate_togget_swite_mode  = false;
            resetbackground_swait_mode(dustBtn , personalBtn);
            resettext_swait_mode();
        });


        //ไฟลบ
        ReadFile fileChooser = new ReadFile();
        RoundeBtn btn_submit_input_file;
        Set_btn_view_pm set_btn_view_pm = new Set_btn_view_pm(btn_list, fileChooser);

        btn_submit_input_file = switchType(GetComponent("btn_submit_input_file")) ;

        btn_submit_input_file.addActionListener(e -> {
            
            OverModhod.reSetHistoryBtn();
            OverModhod.reSetHoverHistory();
            if(Config.history_btn.size()  > 0) Config.history_btn.clear();
            if(Config.history_hover.size() > 0) Config.history_hover.clear();
            if (Config.CopyMain_btn.size() > 0) Config.CopyMain_btn.clear(); 

            fileChooser.selectAndReadFile(btn_submit_input_file);

            set_btn_view_pm.SetPm();

            set_text.setDetail_data();
        });

        for (int i = 0; i < btn_list.size(); i++) {
            RoundeBtn btn = GetComponentBtn(i);
            int index = i;
            if (btn != null) {
                btn.addActionListener(e -> {
                
                if (Config.btn_togat_rain_make) {
                    makerain(btn.getX_location(), btn.getY_location());
                    if (Config.activate_btn >= 0) {
                        RoundeBtn btn_temp = GetComponentBtn(Config.activate_btn);
                        if (btn_temp != null) {
                            Config.activate_btn = -1;
                            btn_temp.resetBackground();
                        }
                    }
                }else{
                    if(Config.activate_btn == index){
                        Config.activate_btn = -1;
                    }else{
                        Config.activate_btn = index;
                    }
                     
                }

                set_text.setDetail_data(); 
                });

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (Config.btn_togat_rain_make) {
                            HoverBtn_rainMake(btn.getX_location(), btn.getY_location());
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (Config.btn_togat_rain_make) {
                            clearHoverEffects(); 
                        }
                    }
                });

            }
            btn.resetBackground();
        }

        // เพิ่ม คน
        RoundedTextField input_person = switchType_as_RoundedTextField(GetComponent("box_bottom_input_person_stantic_input_personal"));
        RoundeBtn btn_add_person = switchType(GetComponent("box_bottom_input_person_stantic_btn_submit_input_person_static"));

        input_person.addKeyListener(OverModhod.getNumericKeyFilter());

        btn_add_person.addActionListener(e -> {
                int btn_key = Config.activate_btn;
                int new_person = GetFildText_to_number(input_person);
                if (btn_key >= 0 && new_person >= 0) {
                    RoundeBtn  activate_btn = switchType(GetComponentBtn(btn_key));
                    activate_btn.setPerson(new_person);     
                    activate_btn.calculatePerson();
                    set_text.setDetail_data();      
                }
            
        });


        // สุ่มคน
        RoundedTextField input_min_person_random = switchType_as_RoundedTextField(GetComponent("Box_in_Right_button_input_area_input_min_personal"));
        RoundedTextField input_max_person_random = switchType_as_RoundedTextField(GetComponent("Box_in_Right_button_input_area_input_max_personal"));
        RoundeBtn btn_add_person_random = switchType(GetComponent("Box_in_Right_button_input_area_btn_submit_input_person"));
        
        input_min_person_random.addKeyListener(OverModhod.getNumericKeyFilter());
        input_max_person_random.addKeyListener(OverModhod.getNumericKeyFilter());

        btn_add_person_random.addActionListener(e -> {
            int min = GetFildText_to_number(input_min_person_random);
            int max = GetFildText_to_number(input_max_person_random);

            if (min >= 0 && max >= 0) {
                RoundeBtn btn;
                ArrayList<Integer> temparr = random_person(min , max);
                if (temparr.size() < 800) {
                    return;
                }
                for (int i = 0; i < 800; i++) {
                    int new_person = temparr.get(i);
                    btn = GetComponentBtn(i);
                    btn.setPerson(new_person);
                    btn.calculatePerson();
                }
            }
        });

        // ฝนธรรมชาติ
        RoundeBtn rain_naternallBtn = switchType(GetComponent("btn_center_Rain"));
        RoundeBtn rain_make_Btn = switchType(GetComponent("btn_center_Rain_make"));
        rain_naternallBtn.addActionListener(e -> {
            trenoff_seait_mode();

            clearHoverEffects();
            OverModhod.reSetHistoryBtn();
            OverModhod.reSetHoverHistory();

            Config.btn_togat_rain_make = false;
            rain_make_Btn.resetBackground();

            Map<Integer, RoundeBtn> addToHistory = new HashMap<>();
            Map<Integer, RoundeBtn> Copy_Main_Btn = Config.CopyMain_btn;
            Copy_Main_Btn.clear();

            Create_cloenBtnhistory(addToHistory , Copy_Main_Btn);
            Set<Integer> hoverset = new HashSet<>();

            RoundeBtn btn;
            for (int i = 0; i < 800; i++) {
                btn = GetComponentBtn(i);

                int pm = btn.getPm();
                if(pm <= 0) continue;
                int newPm = 0;
                if (pm >50) {
                    newPm = pm - 50;
                    
                    
                }else {
                    newPm = 0;
                }
                
                hoverset.add(btn.getIndex());
                btn.setPm(newPm);
                btn.calculatePerson();

            }
            set_text.setDetail_data();


            Config.history_hover.add(hoverset);

            create_Copymain_cloenhistory(Copy_Main_Btn);

        
            setOld_cloenhistory(addToHistory);

            Config.history_btn.add(addToHistory);
            });

        //ฝนเทียม
        
        rain_make_Btn.addActionListener(e -> {
            Config.btn_togat_rain_make = !Config.btn_togat_rain_make;

            trenoff_seait_mode();

            resetbackground_swait_mode(dustBtn, personalBtn);
            OverModhod.reSetHistoryBtn();
            OverModhod.reSetHoverHistory();
            rain_make_Btn.resetBackground();
        });

        //ประวัติ
        RoundeBtn btn_history = switchType(GetComponent("btn_center_Btn_history"));
        btn_history.addActionListener(e -> {
            Window parentWindow = SwingUtilities.getWindowAncestor(btn_history);
            History_screen dialog = new History_screen(parentWindow, "" , set_text);
            dialog.setVisible(true);

        });


    }



    public RoundeBtn GetComponentBtn(int key){
        return btn_list.get(key);
    }

    public Component GetComponent(String key){
        return componentMap.get(key);
    }

    public RoundeBtn switchType(Component com) {
        if (com instanceof RoundeBtn) {
            return (RoundeBtn) com;
        }
        return null;
    }
    
    public RoundedTextField switchType_as_RoundedTextField(Component com){
        if (com instanceof RoundedTextField textField) {
            return (RoundedTextField) textField;
        }
        return null;
    }

    public int GetFildText_to_number(RoundedTextField field) {

        String text = field.getText().trim();

        if (text.isEmpty()) {
            Alert.warnMessage("กรุณากรอกตัวเลข");
            return -1;
        }

        try {
            int number = Integer.parseInt(text);

            if (number >= 0) {
                return number;
            }

        } catch (NumberFormatException e) {
            Alert.warnMessage("กรุณากรอกตัวเลขให้ถูกต้อง");
        }

        return -1;
    }

    public void makerain(int x, int y) {
        
        

        Map<Integer, RoundeBtn> addToHistory = new HashMap<>();
        Map<Integer, RoundeBtn> Copy_Main_Btn = Config.CopyMain_btn;
        Copy_Main_Btn.clear();

        Create_cloenBtnhistory(addToHistory , Copy_Main_Btn);
        

        ArrayList<ArrayList> mainarr = Calculate.calculate_area(x, y);

        Set<Integer> hoverset = new HashSet<>();
        BtnMap_update(mainarr.get(0), 50 , hoverset);
        BtnMap_update(mainarr.get(1), 25 , hoverset);
        BtnMap_update(mainarr.get(2), 10 , hoverset);


        Config.history_hover.add(hoverset);

        create_Copymain_cloenhistory(Copy_Main_Btn);

     
        setOld_cloenhistory(addToHistory);

        Config.history_btn.add(addToHistory);
    }

    private void Create_cloenBtnhistory(Map<Integer, RoundeBtn> addToHistory,Map<Integer, RoundeBtn> Copy_Main_Btn){
        for (Map.Entry<Integer, RoundeBtn> entry : btn_list.entrySet()) {
            RoundeBtn originalBtn = entry.getValue();
            int originalPm = originalBtn.getPm();
            
            RoundeBtn clonedBtn = new RoundeBtn(originalBtn.getPm() + "" , 20 , true);
            RoundeBtn clonedBtnMain = new RoundeBtn(originalBtn.getPm() + "" , 20 , true);
            clonedBtn.setPm(originalPm); 
            clonedBtnMain.setPm(originalPm);

            Copy_Main_Btn.put(entry.getKey(), clonedBtnMain);
            addToHistory.put(entry.getKey(), clonedBtn);
        }
    }

    private void create_Copymain_cloenhistory(Map<Integer, RoundeBtn> Copy_Main_Btn){
        for (Map.Entry<Integer, RoundeBtn> entry : btn_list.entrySet()) {
            RoundeBtn originalBtn = entry.getValue();
            int originalPm = originalBtn.getPm();

            
            RoundeBtn clonedBtnMain = new RoundeBtn(originalBtn.getPm() + "" , 20 , true);
            clonedBtnMain.setPm(originalPm);
            
            Copy_Main_Btn.put(entry.getKey(), clonedBtnMain);
        }
    }

    private void setOld_cloenhistory(Map<Integer, RoundeBtn> addToHistory){
        for (int i = 0; i < addToHistory.size(); i++) {
            RoundeBtn btn = addToHistory.get(i);
            RoundeBtn originalBtn = btn_list.get(i);
            int originalPm = originalBtn.getPm();

            btn.setOldBadperdon_per(originalBtn.GetBadPerson_per());
            btn.setOldPm(originalPm);
        }
    }

    private void BtnMap_update(ArrayList<String> arr ,int pm_per , Set<Integer> mySet){

        for (int i = 0; i < btn_list.size(); i++) {
            RoundeBtn btn = btn_list.get(i);
            int main_x = btn.getX_location();
            int main_y = btn.getY_location();
            for(String a : arr){
                String text[] = a.split(",");

                int arr_X = Integer.parseInt(text[0]) ;
                int arr_Y = Integer.parseInt(text[1]);

                if (arr_X < 0 || arr_Y < 0) continue;

                if (main_x == arr_X && arr_Y == main_y) {
                    int basePm = btn.getPm();
                    if (basePm > 0) {
                        btn.setPm(basePm - Math.round(basePm * pm_per / 100));

                        mySet.add(btn.getIndex());
                    }

                }

            }
        }


    }

    private void HoverBtn_rainMake(int x , int y){
        ArrayList<ArrayList> mainarr =  Calculate.calculate_area(x , y);
        BtnMap_Hover(mainarr.get(0) , Color.GREEN);
        BtnMap_Hover(mainarr.get(1) , Color.YELLOW);
        BtnMap_Hover(mainarr.get(2) , Color.BLUE);
    }


    private void BtnMap_Hover(ArrayList<String> arr, Color cor) {
        int GRID_COLS = 40; 
        

        for (String a : arr) {
            String[] text = a.split(",");
            int arr_X = Integer.parseInt(text[0]);
            int arr_Y = Integer.parseInt(text[1]);

            if (arr_X < 0 || arr_Y < 0) continue;

            int index = (arr_X * GRID_COLS) + arr_Y; 
            RoundeBtn btn = btn_list.get(index);

            if (btn != null && btn.getPm() > 0) {
                btn.setBackground(cor);
                btn.repaint();
                activeHoverBtns.add(btn); 
            }
        }
    }

    private void clearHoverEffects() {
        for (RoundeBtn btn : activeHoverBtns) {
            if (btn != null) {
                btn.resetBackground();
                btn.repaint();
            }
        }
        activeHoverBtns.clear(); 
    }
    
    private ArrayList<Integer> random_person(int min, int max) {

        if (min > max) {
            Alert.warnMessage("ค่าต่ำสุดต้องน้อยกว่าค่าสูงสุด");
            return new ArrayList<>();
        }
        int amount = 800;

        if ((long) max - min + 1 < amount) {
            Alert.warnMessage("ช่วงตัวเลขต้องมีอย่างน้อย 800 ค่า");
            return new ArrayList<>();
        }

        HashSet<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < amount) {

            int randomNumber = ThreadLocalRandom.current()
                    .nextInt(min, max + 1);

            uniqueNumbers.add(randomNumber);
        }

        return new ArrayList<>(uniqueNumbers);
    }

    private void resetbackground_swait_mode(RoundeBtn dustBtn , RoundeBtn personalBtn){
        if (isActivate_togget_swite_mode) {
            dustBtn.setBackground(Color.white);
            dustBtn.setShadow(true);

            personalBtn.setBackground(null);
            personalBtn.setShadow(false);
        }else{

            dustBtn.setBackground(null);
            dustBtn.setShadow(false);


            personalBtn.setBackground(Color.white);
            personalBtn.setShadow(true);
        }
    }

    private void resettext_swait_mode(){
        for (RoundeBtn btn : btn_list.values()) {
            String text ;
            if (isActivate_togget_swite_mode) {
                text = btn.getPm() + "";
            }else{
                int person = btn.getPerson();
                if (person >= 1000) {
                    person = person / 1000;
                    text = person + " k";
                }else{
                    text = person + "";
                }
                
            }
            btn.setText(text);
        }
    }

    private void trenoff_seait_mode(){
        isActivate_togget_swite_mode = true;
        resetbackground_swait_mode(dustBtn , personalBtn);
        resettext_swait_mode();
    }




        



    
}
