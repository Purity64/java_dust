package util;

import javax.swing.*;
import src.Config;
import src.Set_text;
import util.btn.BtnHistory;
import util.btn.Btn_Empty;
import util.btn.RoundeBtn;
import java.awt.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class History_screen extends JDialog {
    RoundedBox main_div_history = new RoundedBox(20, 6);
    int size = Config.history_btn.size();

    int page = 1;
    int maxpage;
    int start_btn_count = 1;
    int end_btn_count = start_btn_count + 59;
    Set_text set_text;

    public History_screen(Window owner, String message ,  Set_text set_text) {
        super(owner, "ยืนยันการทำรายการ", ModalityType.APPLICATION_MODAL);
        this.set_text = set_text;
        this.maxpage = (size == 0) ? 1 : (int) Math.ceil((double) size / 60);

        Dimension ownerSize = owner.getSize();
        int mainWidth = ownerSize.width;
        int mainHeight = ownerSize.height;

        setUndecorated(true);
        setSize(mainWidth, mainHeight);
        setLocationRelativeTo(owner);
        setBackground(new Color(0, 0, 0, 0));

        JPanel bgPanel = new JPanel(new GridBagLayout());
        bgPanel.setBackground(new Color(5, 97, 97, 50));

        RoundedBox mainPanel = new RoundedBox(20);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(mainWidth - 200, mainHeight - 100));
        mainPanel.setLayout(new BorderLayout());

        JPanel headerBox = new JPanel(new BorderLayout());
        headerBox.setOpaque(false);

        JPanel leftSpacer = new JPanel();
        leftSpacer.setPreferredSize(new Dimension(100, 40));
        leftSpacer.setOpaque(false);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("ยืนยันการทำรายการ");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setForeground(Color.DARK_GRAY);
        titlePanel.add(titleLabel);

        RoundeBtn closeBtn = new RoundeBtn("ปิด", 20);
        closeBtn.setBackground(Color.RED);
        closeBtn.setPreferredSize(new Dimension(100, 40));
        closeBtn.setForeground(Color.WHITE);

        JPanel rightPanel = new JPanel(new FlowLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(closeBtn);

        headerBox.add(leftSpacer, BorderLayout.WEST);
        headerBox.add(titlePanel, BorderLayout.CENTER);
        headerBox.add(rightPanel, BorderLayout.EAST);

        main_div_history.setLayout(new GridLayout(10, 6, 5, 5));
        main_div_history.setBackground(new Color(0xE9EBE5));
        main_div_history.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        RoundedBox main_page_box = new RoundedBox(20);
        main_page_box.setLayout(new FlowLayout(FlowLayout.CENTER));
        main_page_box.setBackground(Color.WHITE);
        
        mainPanel.add(headerBox, BorderLayout.NORTH);
        mainPanel.add(main_div_history, BorderLayout.CENTER);
        mainPanel.add(main_page_box, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // สร้างปุ่มหน้าแรก
        createBtn(main_div_history);

        Btn_Empty btn_back, btn_next;
        btn_back = new Btn_Empty("กลับ", 15, true, 6);
        btn_next = new Btn_Empty("หน้าต่อไป", 15, true, 6);
        RoundedTextField numpage = new RoundedTextField(page + "", 1, 20, true, 3);
        
        btn_back.setBackground(Color.WHITE);
        btn_back.setReSize(120, 50);
        btn_next.setBackground(Color.WHITE);
        btn_next.setReSize(120, 50);
        numpage.setPreferredSize(new Dimension(100, 45));
        numpage.setHorizontalAlignment(JTextField.CENTER);

        btn_back.addActionListener(e -> {
            topage(page - 1);
            numpage.setText(page + "");
        });

        btn_next.addActionListener(e -> {
            topage(page + 1);
            numpage.setText(page + "");
        });

        main_page_box.add(btn_back);
        main_page_box.add(numpage);
        main_page_box.add(btn_next);

        bgPanel.add(mainPanel);
        add(bgPanel);

        closeBtn.addActionListener(e -> dispose());
    }

    private void createBtn(RoundedBox div) {
        div.removeAll();

        BtnHistory btn;
        while (start_btn_count <= end_btn_count) {
            if (start_btn_count <= size) {
                btn = new BtnHistory("ครั้งที่ " + start_btn_count, 20, true, 6);
                btn.setBackground(Color.WHITE);
                btn.SetIndex(start_btn_count - 1);
                setAction(btn);
                div.add(btn);

                if (Config.show_btn_key == start_btn_count-1) {
                    btn.setBackground(Color.blue);
                }
                
                
            }else if(start_btn_count  == size + 1){
                btn = new BtnHistory("ปัจจุบัน", 20, true, 6);
                btn.setBackground(Color.GREEN);
                div.add(btn);
                btn.SetIndex(-1);
                setAction(btn);
            }else {
                JButton empty = new JButton();
                empty.setContentAreaFilled(false); 
                empty.setBorderPainted(false);    
                empty.setFocusPainted(false); 
                div.add(empty);
            }

            start_btn_count++;
        }
        div.revalidate();
        div.repaint();
    }

    private void topage(int nextpage) {
        if (nextpage >= 1 && nextpage <= maxpage) {
            this.page = nextpage;
            start_btn_count = ((page - 1) * 60) + 1;
            end_btn_count = start_btn_count + 59;
            createBtn(main_div_history);
        }
    }

    private void setAction(BtnHistory btn){
        btn.addActionListener(e -> {
            Config.show_btn_key = btn.GetIndex();
            if (Config.show_btn_key == -1) {
                Config.is_show_history = false;
            }else{
                Config.is_show_history = true;
            }
            
            setHistoryBtnAll();
            Config.activate_btn = -1;
            set_text.setDetail_data();
            dispose();
        });
    }

    private void setHistoryBtnAll() {
        Map<Integer, RoundeBtn> main_btn = Config.main_btn;
        Map<Integer, RoundeBtn> new_btn;
        Set<Integer> hoverarr = new HashSet<>() ;
        
        if (Config.show_btn_key == -1) {
            new_btn = Config.CopyMain_btn;
        } else {
            new_btn = Config.history_btn.get(Config.show_btn_key);
            hoverarr = Config.history_hover.get(Config.show_btn_key);
        }

        if (new_btn == null || main_btn == null) return;

        for (int i = 0; i < 800; i++) {
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

  
        for(Integer a : hoverarr ){
            RoundeBtn btn = Config.main_btn.get(a);
            btn.setisActivate(true);
            btn.resetBackground();
        }
        

    }
    
}