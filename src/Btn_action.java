package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.event.MouseEvent;

import util.RoundeBtn;
import util.RoundedTextField;

public class Btn_action {
    private Map<Integer, RoundeBtn> btn_list;
    private Map<String, Component> componentMap;
    public Btn_action(Map<String, Component>  componentMap, Map<Integer, RoundeBtn> btn_list  , Set_text set_text){
        this.btn_list = btn_list;
        this.componentMap = componentMap;

        ReadFile fileChooser = new ReadFile();
        RoundeBtn btn_submit_input_file;
        Set_btn_view_pm set_btn_view_pm = new Set_btn_view_pm(btn_list, fileChooser);

        btn_submit_input_file = switchType(GetComponent("btn_submit_input_file")) ;

        btn_submit_input_file.addActionListener(e -> {
            fileChooser.selectAndReadFile(btn_submit_input_file);
            set_btn_view_pm.SetPm();
        });

        for (int i = 0; i < btn_list.size(); i++) {
            int index = i;
            RoundeBtn btn = GetComponentBtn(i);
            if (btn != null) {
                btn.addActionListener(e -> {
                    Config.activate_btn = index;
                    if (Config.btn_togat_rain_make)makerain(btn.getX_location(), btn.getY_location());
                    
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
                            resetAllButtonBackgrounds(); 
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

            if (min >= 0 & max >= 0) {
                RoundeBtn btn;
                for (int i = 0; i < 800; i++) {
                    int new_person = ThreadLocalRandom.current().nextInt(min, max + 1);
                    btn = GetComponentBtn(i);
                    btn.setPerson(new_person);
                    btn.calculatePerson();
                }
            }
        });

        // ฝนธรรมชาติ
        RoundeBtn rain_naternallBtn = switchType(GetComponent("btn_center_Rain"));

        rain_naternallBtn.addActionListener(e -> {
            RoundeBtn btn;
            for (int i = 0; i < 800; i++) {
                btn = GetComponentBtn(i);

                int pm = btn.getPm();
                int newPm = 0;
                if (pm >50) {
                    newPm = pm - 50;
                }

                btn.setPm(newPm);
                btn.calculatePerson();

                if (Config.activate_btn == btn.getIndex() && Config.activate_btn != 0) {
                    set_text.setDetail_data();
                }
            }
        });

        //ฝนเทียม
        RoundeBtn rain_make_Btn = switchType(GetComponent("btn_center_Rain_make"));
        rain_make_Btn.addActionListener(e -> {
            Config.btn_togat_rain_make = !Config.btn_togat_rain_make;
            rain_make_Btn.resetBackground();
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

    public int GetFildText_to_number(RoundedTextField Field){
        String num = Field.getText();
        if (Integer.parseInt(num) >= 0) {
            int new_num = (int)Integer.parseInt(num);
            return new_num;
        }
        return -1;
    }

    public void makerain(int x , int y){
        ArrayList<ArrayList> mainarr =  Calculate.calculate_area(x , y);
        BtnMap_update(mainarr.get(0) , 50);
        BtnMap_update(mainarr.get(1) , 25);
        BtnMap_update(mainarr.get(2) , 10);
    }

    private void BtnMap_update(ArrayList<String> arr ,int pm_per){
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
                    btn.setPm(basePm - Math.round(basePm * pm_per / 100));
                    btn.resetBackground();
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


    private void BtnMap_Hover(ArrayList<String> arr ,Color cor){
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
                    System.out.println(cor.toString());

                    btn.setBackground(cor);
                }

            }
        }
    }

    private void resetAllButtonBackgrounds() {
        for (int i = 0; i < btn_list.size(); i++) {
            RoundeBtn btn = btn_list.get(i);
            if (btn != null) {
                btn.resetBackground();
            }
        }
    }
        



    
}
