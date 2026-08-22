package util;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Alert {

    public static void warnMessage(String message) {

        Font thaiFont = new Font("Tahoma", Font.PLAIN, 16);

        UIManager.put("OptionPane.messageFont", thaiFont);
        UIManager.put("OptionPane.buttonFont", thaiFont);

        JOptionPane.showMessageDialog(
            null,
            message,
            "แจ้งเตือน",
            JOptionPane.ERROR_MESSAGE
        );
    }
}