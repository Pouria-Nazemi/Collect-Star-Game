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

     static MusicPlayer music;

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

        //music = new MusicPlayer("D:\\Sleep Away.wav");

        startJB = new JButton(startIcon);
        startJB.setBounds(189,213,437,110);
        startJB.addMouseListener(this);
        startJB.setBorder(null);

        aboutJB = new JButton(aboutIcon);
        aboutJB.setBounds(256,365,327,76);
        aboutJB.addMouseListener(this);
        //adding action listener later
        aboutJB.setBorder(null);

        menuJP.add(startJB,0);
        menuJP.add(aboutJB,1);
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
           // music.stopMusic();
            menuFrame.setVisible(false);
            loading();
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
    public void loading(){
        JFrame loadFrame = new JFrame("THE GAME IS LOADING ... ");
        loadFrame.setLocation(250,0);
        loadFrame.setLayout(new BorderLayout());
        loadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
