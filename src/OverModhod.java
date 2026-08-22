package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
}

