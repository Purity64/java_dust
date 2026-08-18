import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ui {

    public void setBackgroundUi(JPanel p, int width, int height) {
        p.setPreferredSize(new Dimension(width, height));
        p.revalidate();
        p.repaint();
    }

    public void setSizeBoxLayout(Component p, int width, int height) {
        Dimension size = new Dimension(width, height);
        p.setPreferredSize(size);
        p.setMaximumSize(size);

        p.revalidate();
        p.repaint();
    }

    public ImageIcon getIcon(String path, int sizeW, int sizeH) {
        URL imgURL = getClass().getResource("/images/" + path);
        if (imgURL == null) {
            System.err.println("ไม่พบไฟล์รูปภาพ: /images/" + path);
            return null;
        }

        ImageIcon originalIcon = new ImageIcon(imgURL);
        Image scaledImg = originalIcon.getImage().getScaledInstance(sizeW, sizeH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public void setIcon(Component compo, String pathImage, int sizeW, int sizeH) {
        ImageIcon icon = getIcon(pathImage, sizeW, sizeH);
        if (icon == null) return;

        if (compo instanceof AbstractButton) {
            ((AbstractButton) compo).setIcon(icon);
        } else if (compo instanceof JLabel) {
            ((JLabel) compo).setIcon(icon);
        }
    }



}