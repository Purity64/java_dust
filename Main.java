import javax.swing.*;

import src.Btn_action;
import src.Config;
import src.Set_text;
import util.RoundedBox;
import util.btn.RoundeBtn;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    private Map<String, Component> componentMap = new HashMap<>();
    private Map<Integer, RoundeBtn> Btn_pn_Map = Config.main_btn;

    

    Ui ui = new Ui();
    public Main() {
        Config cf = new Config();
        BuildBox buildBox = new BuildBox();
    
        setTitle("purity_dust");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        setMinimumSize(new Dimension(1000, 600));
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); 

        JPanel box1 , box2 ;
        box1 = new JPanel();
        box2  = new JPanel();
        
        componentMap.put("MainBoxLeft", box1);
        componentMap.put("MainBoxRight", box2);
        RoundedBox boxin_box1 = new RoundedBox( 30 , 6);

        

        box1.setBackground(new Color(0xE9E2E2));
        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box2.setBackground(new Color(0xE9E2E2));

        boxin_box1.setBackground(new Color(0xD1D5CB));
        boxin_box1.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        add(box1);
        add(box2);

        box1.add(boxin_box1);

        buildBox.createLeftBox(boxin_box1 , Btn_pn_Map);
        buildBox.createRightBox(box2 , cf , componentMap);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int currentWidth = getContentPane().getWidth();
                int currentHeight = getContentPane().getHeight();

     

                int boxLeft_width = (int)(currentWidth * (70.0 / 100));
                int boxLeft_height = currentHeight;

                int boxRight_width = currentWidth - boxLeft_width;
                int boxRight_height = currentHeight;

                int box_in_left_width = boxLeft_width - 20;
                int box_in_left_height = boxLeft_height - 30;




                ui.setBackgroundUi(box1, boxLeft_width , boxLeft_height);
                ui.setBackgroundUi(box2, boxRight_width, boxRight_height);

                ui.setBackgroundUi(boxin_box1, box_in_left_width, box_in_left_height );

                ui.setIcon(GetComponent("Box_in_Right_Top_BoxManu_btn_left"), "cloud.png", 30, 30);
                ui.setIcon(GetComponent("Box_in_Right_Top_BoxManu_btn_right"), "users.png", 30, 30);

                ui.setIcon(GetComponent("btn_center_Rain_make"), "airplan.png", 30, 30);
                ui.setIcon(GetComponent("btn_center_Rain"), "rain.png", 30, 30);

                ui.setIcon(GetComponent("btn_submit_input_file"), "folder.png", 30, 30);
                

                int box_in_Right_width = boxRight_width -20;
                ReSetSize("Box_in_Right_center_detail", box_in_Right_width, 200);
                ReSetSize("lable_Box_center_detail_head", box_in_Right_width, 20);

                ReSetSize("Box_in_Right_Top_BoxManu" , box_in_Right_width , 70);
                ReSetSize("Box_in_Right_Top_BoxManu_btn_left", (box_in_Right_width / 2 ) - 30 , 45);
                ReSetSize("Box_in_Right_Top_BoxManu_btn_right", (box_in_Right_width / 2 ) - 30, 45);

                ReSetSize("box_center_rain", box_in_Right_width, 70);
                ReSetSize("btn_center_Rain", (box_in_Right_width / 2 ) - 30, 45);
                ReSetSize("btn_center_Rain_make", (box_in_Right_width / 2 ) - 30, 45);

                ReSetSize("Box_in_Right_button_input_area", box_in_Right_width, 70);
                ReSetSize("Box_in_Right_button_input_area_input_min_personal", (box_in_Right_width / 3) - 10, 45);
                ReSetSize("Box_in_Right_button_input_area_input_max_personal", (box_in_Right_width / 3) - 10, 45);
                ReSetSize("Box_in_Right_button_input_area_btn_submit_input_person", (box_in_Right_width / 3) - 10, 45);
                
                ReSetSize("box_bottom_input_person_stantic", box_in_Right_width , 60);
                ReSetSize("box_bottom_input_person_stantic_input_personal", (box_in_Right_width / 2) - 20, 45);
                ReSetSize("box_bottom_input_person_stantic_btn_submit_input_person_static", (box_in_Right_width / 2) - 40, 45);

                ReSetSize("box_input_file", box_in_Right_width , 50);
                ReSetSize("btn_submit_input_file", box_in_Right_width / 2, 45);

                revalidate();
                repaint();
            }


        });
        Set_text set_text = new Set_text(componentMap, Btn_pn_Map);
        Btn_action btn_action = new Btn_action(componentMap ,Btn_pn_Map , set_text);


    }
    public void ReSetSize( String key , int width , int height ){
        Component comp = componentMap.get(key);
        if (comp != null) {
            if (key.equalsIgnoreCase("Box_in_Right_Top_BoxManu")) {
                if (comp instanceof RoundedBox) {
                    RoundedBox box = (RoundedBox) comp; 
                    ui.setSizeBoxLayout(box, width, height);
                }
            }else if(key.equalsIgnoreCase("Box_in_Right_Top_BoxManu_btn_left")){
                if (comp instanceof RoundeBtn) {
                    RoundeBtn box = (RoundeBtn) comp; 
                    ui.setSizeBoxLayout(box, width, height);
                }
            }else{
                 ui.setSizeBoxLayout(comp, width, height);
            }

        }

        
    }

    public Component GetComponent(String key){
        return componentMap.get(key);
    }

    public static void main(String[] args) {
        Main f = new Main();
        
        f.setVisible(true);

    }
}