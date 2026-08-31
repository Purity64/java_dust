package util;

import javax.swing.*;

import util.btn.RoundeBtn;

import java.awt.*;

public class ConfirmDialog extends JDialog {
    private boolean confirmed = false;

    public ConfirmDialog(Window owner, String message) {
        super(owner, "ยืนยันการทำรายการ", ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setSize(400, 220);
        setLocationRelativeTo(owner);

        // กล่องเนื้อหาหลัก
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        mainPanel.setBackground(Color.WHITE);

        // ข้อความแจ้งเตือน
        JLabel msgLabel = new JLabel("<html><center>" + message + "</center></html>", SwingConstants.CENTER);
        msgLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
        mainPanel.add(msgLabel, BorderLayout.CENTER);

        // แผงปุ่มกด
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setOpaque(false);

        RoundeBtn btnConfirm = new RoundeBtn("ตกลง", 12);
        btnConfirm.setPreferredSize(new Dimension(100, 35));
        btnConfirm.setBackground(new Color(0x66CC66));
        btnConfirm.addActionListener(e -> {
            confirmed = true;
            dispose(); // ปิดหน้าต่าง
        });

        RoundeBtn btnCancel = new RoundeBtn("ยกเลิก", 12);
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setBackground(new Color(0xFF5555));
        btnCancel.addActionListener(e -> {
            confirmed = false;
            dispose(); // ปิดหน้าต่าง
        });

        btnPanel.add(btnConfirm);
        btnPanel.add(btnCancel);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}