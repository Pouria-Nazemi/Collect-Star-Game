package graphic;

import javax.swing.*;
import java.awt.*;

public class AboutFrame {
    public AboutFrame() {
        JFrame aboutFrame = new JFrame("About the game and Producers");
        aboutFrame.setLocation(300,0);
        aboutFrame.setResizable(false);
        Image backgroundIm = new ImageIcon(isometric.class.getResource("image/About page.png")).getImage().getScaledInstance(700,690,Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundIm));
        background.setBounds(0,0,700,700);
        aboutFrame.add(background);
        aboutFrame.setSize(700,700);
        aboutFrame.setVisible(true);
    }
}
