import java.awt.*;
import java.util.Map;
import javax.swing.*;

import src.Config;
import util.RoundedBox;
import util.RoundedTextField;
import util.btn.RoundeBtn;

public class BuildBox {
    
    public void createLeftBox(JPanel main_blox , Map<Integer, RoundeBtn> btnMap) {
        main_blox.setLayout(new GridLayout(20, 40, 2, 2));
        spawnBox(main_blox , btnMap);
        main_blox.revalidate();
        main_blox.repaint();
    }

    private void spawnBox(JPanel main_blox, Map<Integer, RoundeBtn> btnMap) {
        int index = 0;

        for (int i = 0; i < 20; i++) {
            char rowChar = (char) ('A' + i); 
            for (int j = 0; j < 40; j++) {
                RoundeBtn btn = box(main_blox, "0");
                btn.setX_location(i);
                btn.setY_location(j);
                String btnName = "" + rowChar + (j + 1);
                btn.setName(btnName);
                btn.setIndex(index);
                btnMap.put(index, btn);
                index++;
            }
        }
    }

    private RoundeBtn box(JPanel main_blox, String num) {
        RoundeBtn btn = new RoundeBtn(num, 10, true);
        btn.setText(num);
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 8));
        main_blox.add(btn);

        return btn;
    }

    public void createRightBox(JPanel main_box, Config cf, Map<String, Component> map) {
        RoundeBtn Btn_top_manu_left, Btn_top_manu_right, btn_center_Rain_make, btn_center_Rain, btn_submit_input_person, btn_submit_input_person_static , btn_submit_input_file , Btn_history;
        RoundedBox Boxtop, Box_center_detail, box_center_rain, box_button_input_area, box_bottom_input_person_stantic , box_input_file;
        RoundedTextField input_min_personal, input_max_personal, input_personal ;
        JLabel label_box_button_input_area;

        Boxtop = new RoundedBox(30, 6);
        Box_center_detail = new RoundedBox(30);

        box_input_file = new RoundedBox(0);
        btn_submit_input_file = new RoundeBtn("เพิ่มไฟล pm.txt", 30 , true , 6);

        box_center_rain = new RoundedBox(0);
        box_button_input_area = new RoundedBox(0);

        Btn_top_manu_left = new RoundeBtn("ฝุ่น", 20, true, 4);
        Btn_top_manu_right = new RoundeBtn("ประชากร", 20, false, 0); 

        btn_center_Rain_make = new RoundeBtn("ฝนเทียม", 20, true, 4);
        btn_center_Rain = new RoundeBtn("ฝนธรรมชาติ", 20, true, 4);
        Btn_history = new RoundeBtn("ประวัต", 20 , true , 4);

        input_min_personal = new RoundedTextField("0", 5, 20);
        label_box_button_input_area = new JLabel("-");
        input_max_personal = new RoundedTextField("250", 5, 20);
        btn_submit_input_person = new RoundeBtn("สุ่มประชากร", 20, true, 4);

        box_bottom_input_person_stantic = new RoundedBox(0);
        btn_submit_input_person_static = new RoundeBtn("ตั้งค่าจำนวนคน", 20, true, 4);
        input_personal = new RoundedTextField("0", 5, 20);

        input_min_personal.setHorizontalAlignment(JTextField.CENTER);
        input_max_personal.setHorizontalAlignment(JTextField.CENTER);
        input_personal.setHorizontalAlignment(JTextField.CENTER);

        main_box.setLayout(new BoxLayout(main_box, BoxLayout.Y_AXIS));
        main_box.add(Box.createRigidArea(new Dimension(0, 20)));

        Boxtop.setAlignmentX(Component.CENTER_ALIGNMENT);
        Boxtop.setLayout(new GridBagLayout());
        Boxtop.setBackground(new Color(0xF6E3E3));


        box_input_file.setAlignmentX(Component.CENTER_ALIGNMENT);
        box_input_file.setLayout(new GridBagLayout());
        box_input_file.setBackground(new Color(0xF6E3E3));

        Box_center_detail.setLayout(new GridBagLayout());
        Box_center_detail.setAlignmentX(Component.CENTER_ALIGNMENT);
        Box_center_detail.setBackground(Color.WHITE);
        Box_center_detail.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // Padding ขอบใน

        Font fontTitle = new Font("Leelawadee UI", Font.BOLD, 18);
        Font fontDetail = new Font("Leelawadee UI", Font.PLAIN, 15);

        GridBagConstraints gbcDetail = new GridBagConstraints();

        JLabel lbl_detail_title = new JLabel("รายละเอียดพื้นที่ : -");
        lbl_detail_title.setFont(fontTitle);

        gbcDetail.gridx = 0;
        gbcDetail.gridy = 0;
        gbcDetail.gridwidth = 2; 
        gbcDetail.anchor = GridBagConstraints.CENTER;
        gbcDetail.insets = new Insets(0, 0, 15, 0);
        Box_center_detail.add(lbl_detail_title, gbcDetail);

        JLabel lbl_pm_title = new JLabel("ปริมาณฝุ่น :");
        JLabel lbl_pm_val = new JLabel("0");

        JLabel lbl_total_title = new JLabel("จำนวนประชากรทั้งหมด :");
        JLabel lbl_total_val = new JLabel("0 คน");

        JLabel lbl_healthy_title = new JLabel("ประชากรร่างกายแข็งแรง :");
        JLabel lbl_healthy_val = new JLabel("0 คน");

        JLabel lbl_sick_title = new JLabel("จำนวนผู้ป่วยจากฝุ่น:");
        JLabel lbl_sick_val = new JLabel("0 คน");

        JLabel lbl_ratio_title = new JLabel("อัตราส่วนผู้ป่วย (%) :");
        JLabel lbl_ratio_val = new JLabel("0 %");

        JLabel[][] items = {
            {lbl_pm_title, lbl_pm_val},
            {lbl_total_title, lbl_total_val},
            {lbl_healthy_title, lbl_healthy_val},
            {lbl_sick_title, lbl_sick_val},
            {lbl_ratio_title, lbl_ratio_val}
        };

        gbcDetail.gridwidth = 1;
        for (int i = 0; i < items.length; i++) {
            JLabel titleLabel = items[i][0];
            JLabel valueLabel = items[i][1];

            titleLabel.setFont(fontDetail);
            valueLabel.setFont(fontDetail);

            gbcDetail.insets = new Insets(4, 0, 4, 0);

            gbcDetail.gridx = 0;
            gbcDetail.gridy = i + 1;
            gbcDetail.anchor = GridBagConstraints.WEST;
            gbcDetail.weightx = 1.0; 
            Box_center_detail.add(titleLabel, gbcDetail);

            gbcDetail.gridx = 1;
            gbcDetail.anchor = GridBagConstraints.EAST;
            gbcDetail.weightx = 0;
            Box_center_detail.add(valueLabel, gbcDetail);
        }


        box_center_rain.setAlignmentX(Component.CENTER_ALIGNMENT);
        box_center_rain.setLayout(new GridBagLayout());
        box_center_rain.setBackground(null);

        box_bottom_input_person_stantic.setAlignmentX(Component.CENTER_ALIGNMENT);
        box_bottom_input_person_stantic.setLayout(new GridBagLayout());
        box_bottom_input_person_stantic.setBackground(null);

        box_button_input_area.setAlignmentX(Component.CENTER_ALIGNMENT);
        box_button_input_area.setLayout(new GridBagLayout());
        box_button_input_area.setBackground(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);

        Boxtop.add(Btn_top_manu_left, gbc);
        Boxtop.add(Btn_top_manu_right, gbc);

        box_center_rain.add(btn_center_Rain_make, gbc);
        box_center_rain.add(btn_center_Rain, gbc);
        box_center_rain.add(Btn_history , gbc);

        box_button_input_area.add(input_min_personal, gbc);
        box_button_input_area.add(label_box_button_input_area);
        box_button_input_area.add(input_max_personal, gbc);
        box_button_input_area.add(btn_submit_input_person, gbc);

        box_bottom_input_person_stantic.add(input_personal, gbc);
        box_bottom_input_person_stantic.add(btn_submit_input_person_static, gbc);

        btn_center_Rain_make.setBackground(new Color(0xFFFFFF));
        btn_center_Rain_make.setName("btn_center_Rain_make");
        btn_center_Rain.setBackground(new Color(0xFFFFFF));

        Btn_top_manu_left.setBackground(new Color(0xFFFFFF));
        Btn_top_manu_right.setBackground(null);

        btn_submit_input_person.setBackground(new Color(0xFFFFFF));

        btn_submit_input_file.setBackground(new Color(0xFFFFFF));

        box_input_file.add(btn_submit_input_file);

        main_box.add(Boxtop);
        main_box.add(Box.createRigidArea(new Dimension(0, 20)));
        main_box.add(Box_center_detail);
        main_box.add(Box.createRigidArea(new Dimension(0, 20)));
        main_box.add(box_center_rain);
        main_box.add(Box.createRigidArea(new Dimension(0, 10)));
        main_box.add(box_button_input_area);
        main_box.add(Box.createRigidArea(new Dimension(0, 10)));
        main_box.add(box_bottom_input_person_stantic);
        main_box.add(Box.createRigidArea(new Dimension(0, 10)));
        main_box.add(box_input_file);

        map.put("box_center_rain", box_center_rain);
        map.put("btn_center_Rain", btn_center_Rain);
        map.put("btn_center_Rain_make", btn_center_Rain_make);
        map.put("btn_center_Btn_history", Btn_history);

        map.put("Box_in_Right_center_detail", Box_center_detail);
        
        map.put("lbl_detail_title", lbl_detail_title);
        map.put("lbl_pm_val", lbl_pm_val);
        map.put("lbl_total_val", lbl_total_val);
        map.put("lbl_healthy_val", lbl_healthy_val);
        map.put("lbl_sick_val", lbl_sick_val);
        map.put("lbl_ratio_val", lbl_ratio_val);

        map.put("Box_in_Right_Top_BoxManu", Boxtop);
        map.put("Box_in_Right_Top_BoxManu_btn_left", Btn_top_manu_left);
        map.put("Box_in_Right_Top_BoxManu_btn_right", Btn_top_manu_right);

        map.put("Box_in_Right_button_input_area", box_button_input_area);
        map.put("Box_in_Right_button_input_area_input_min_personal", input_min_personal);
        map.put("Box_in_Right_button_input_area_input_max_personal", input_max_personal);
        map.put("Box_in_Right_button_input_area_btn_submit_input_person", btn_submit_input_person);

        map.put("box_bottom_input_person_stantic", box_bottom_input_person_stantic);
        map.put("box_bottom_input_person_stantic_btn_submit_input_person_static", btn_submit_input_person_static);
        map.put("box_bottom_input_person_stantic_input_personal", input_personal);

        map.put("box_input_file", box_input_file);
        map.put("btn_submit_input_file", btn_submit_input_file);
    }
}