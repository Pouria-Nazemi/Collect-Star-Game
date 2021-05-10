package graphic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMainMenu implements MouseListener {

    private JButton startJB;
    private JButton aboutJB;
    private JFrame menuFrame;
    private ImageIcon startIcon;
    private ImageIcon aboutIcon;
    private ImageIcon aboutIconSelected;
    private ImageIcon startIconSelected;
    private  MusicPlayer music;


    /* Preparing game menu */
    public GameMainMenu() {

        menuFrame = new JFrame("Main Menu");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        menuFrame.setLocation((screenSize.width-800)/2,(screenSize.height -500)/2);
        menuFrame.setResizable(false);

        JPanel menuJP = new JPanel();
        Image backgroundImage = new ImageIcon(GameCreator.class.getResource("image/Game MAIN MENU.png")).getImage().getScaledInstance(800,500,Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0,0,800,500);
        menuJP.add(background);


        /* Preparing the images' files  */
        startIcon = new ImageIcon(GameCreator.class.getResource("image/start button.png"))/*.getImage().getScaledInstance(439,109,Image.SCALE_SMOOTH)*/;
        aboutIcon = new ImageIcon(GameCreator.class.getResource("image/about button.png"))/*.getImage().getScaledInstance(331,70,Image.SCALE_SMOOTH)*/;
        startIconSelected = new ImageIcon(GameCreator.class.getResource("image/start button selected.png"));
        aboutIconSelected = new ImageIcon(GameCreator.class.getResource("image/about button selected.png"));

        music = new MusicPlayer("src\\graphic\\music\\menuMusic.wav");
        music.loopEnable();

        /* Start bottom */
        startJB = new JButton(startIcon);
        startJB.setBounds(189,213,437,110);
        startJB.addMouseListener(this);
        startJB.setBorder(null);

        /* About bottom */
        aboutJB = new JButton(aboutIcon);
        aboutJB.setBounds(256,365,327,76);
        /* Adds the specified mouse listener to receive mouse events from this component */
        aboutJB.addMouseListener(this);
        aboutJB.setBorder(null);

        menuJP.add(startJB,0);
        menuJP.add(aboutJB,1);
        menuJP.setLayout(null);
        menuJP.setBounds(0,0,800,500);

        menuFrame.add(menuJP);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* pushing x = closing this frame */
        menuFrame.setSize(800,500);
        menuFrame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == startJB){
            music.stopMusic();
            menuFrame.setVisible(false);
            loading();
        }
        if(e.getSource()==aboutJB){
            new AboutFrame();
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

    /* Preparing loading page */
    /* With using a Jpanel and a Jlabel */
    public void loading(){
        //MusicPlayer music = new MusicPlayer("C:\\Users\\User7\\Desktop\\gameLoading.wav");
        JFrame loadFrame = new JFrame("THE GAME IS LOADING ... ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        loadFrame.setLocation((screenSize.width-1000)/2,(screenSize.height -800)/2);
        loadFrame.setLayout(new BorderLayout());
        loadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() ;
        Image LOADING = new ImageIcon(GameCreator.class.getResource("image/load.gif")).getImage();
        Image loading = LOADING.getScaledInstance(1000, 800, Image.SCALE_DEFAULT);

        JLabel item = new JLabel(new ImageIcon(loading));
        panel.setBounds(0, 0, 1000, 800);
        panel.setBackground(Color.black);
        panel.add(item);

        loadFrame.add(panel);
        loadFrame.setSize(1000, 800);
        loadFrame.setLayout(null);
        loadFrame.setVisible(true);

        Timer timer = new Timer(4500, e -> {
            loadFrame.setVisible(false);
            //music.stopMusic();
            new GameCreator();
        });

        timer.setRepeats(false);
        timer.start();
    }
}
