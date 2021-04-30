package graphic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMainMenu implements MouseListener {
     JButton startJB;
     JButton aboutJB;
     JFrame menuFrame;
     ImageIcon startIcon;
    ImageIcon aboutIcon;
    ImageIcon aboutIconSelected;
    ImageIcon startIconSelected;
    public GameMainMenu() {
        menuFrame = new JFrame("Main Menu");
        menuFrame.setLocation(250,150);
        menuFrame.setResizable(false);

        JPanel menuJP = new JPanel();

        Image backgroundIm = new ImageIcon(isometric.class.getResource("image/Game MAIN MENU.png")).getImage().getScaledInstance(800,500,Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundIm));
        background.setBounds(0,0,800,500);
        menuJP.add(background);

        startIcon = new ImageIcon(isometric.class.getResource("image/start button.png"))/*.getImage().getScaledInstance(439,109,Image.SCALE_SMOOTH)*/;
        aboutIcon = new ImageIcon(isometric.class.getResource("image/about button.png"))/*.getImage().getScaledInstance(331,70,Image.SCALE_SMOOTH)*/;
        startIconSelected = new ImageIcon(isometric.class.getResource("image/start button selected.png"));
        aboutIconSelected = new ImageIcon(isometric.class.getResource("image/about button selected.png"));

        startJB = new JButton(startIcon);
        startJB.setBounds(189,213,441,114);
        startJB.addMouseListener(this);

        aboutJB = new JButton(aboutIcon);
        aboutJB.setBounds(256,365,331,80);
        aboutJB.addMouseListener(this);
        //adding action listener later

        menuJP.add(aboutJB);
        menuJP.add(startJB);
        menuJP.setLayout(null);
        menuJP.setBounds(0,0,800,500);

        menuFrame.add(menuJP);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800,500);
        menuFrame.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource()==startJB){
            menuFrame.setVisible(false);
            new isometric();
        }
        if(e.getSource()==aboutJB){
            //baedan add shavad
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==startJB){
            startJB.setIcon(startIconSelected);
        }
        if(e.getSource()==aboutJB){
            aboutJB.setIcon(aboutIconSelected);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==startJB){
            startJB.setIcon(startIcon);
        }
        if(e.getSource()==aboutJB){
            aboutJB.setIcon(aboutIcon);
        }
    }
}
