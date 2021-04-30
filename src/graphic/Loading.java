package graphic;

import javax.swing.*;
import java.awt.*;

public class Loading {
    public Loading() {

        JFrame loadFrame = new JFrame("THE GAME IS LOADING ... ");
        loadFrame.setLocation(400,150);
        loadFrame.setLayout(new BorderLayout());
        JPanel panel = new JPanel() ;

        Image LOADING = new ImageIcon(isometric.class.getResource("image/load.gif")).getImage();
        Image loading = LOADING.getScaledInstance(1000, 800, Image.SCALE_DEFAULT);
        JLabel item=new JLabel(new ImageIcon(loading));
//        JLabel j5 = new JLabel(new ImageIcon("src/graphic/image/loading2.png"));
//        j5.setBounds(0, 0, 300, 248);
        panel.setBounds(0, 0, 1000, 800);
        panel.setBackground(Color.black);
        panel.add(item);
        loadFrame.add(panel);
        loadFrame.setSize(1000, 800);
        loadFrame.setLayout(null);
        loadFrame.setVisible(true);

        Timer timer = new Timer(4500, e -> {
            loadFrame.setVisible(false);
            new isometric();
        });
        timer.setRepeats(false);
        timer.start();
    }
}
