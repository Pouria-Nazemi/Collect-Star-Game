package graphic;

import GameLogic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class isometric {
    static JFrame frame;
    static String selected="";
    static final int TILE_WIDTH=96;
    static final int TILE_HEIGHT=50;
    static int fasele=30;
    static int Cols=5;
    static int Rows=5;
    static Image TILE_IMAGE;
    static Image SElECTED_IMAGE;
    static Image WALL_IMAGE;
    static Image STAR_IMAGE;
    static Image SPEEDLIMITER_IMAGE;
    static Image PLAYER1_IMAGE;
    static Image PLAYER2_IMAGE;

    public isometric(){
        frame =new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocation(400,150);


        TILE_IMAGE= new ImageIcon(getClass().getResource("image/ISOgrass2.png")).getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT,Image.SCALE_DEFAULT);
        SElECTED_IMAGE= new ImageIcon(getClass().getResource("image/selected-grass.png")).getImage().getScaledInstance(TILE_WIDTH, TILE_HEIGHT,Image.SCALE_DEFAULT);
        WALL_IMAGE= new ImageIcon(getClass().getResource("image/wall.png")).getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT+8,Image.SCALE_DEFAULT);
        try {
            PLAYER1_IMAGE= ImageIO.read(getClass().getResource("image/p1.png")); // bejaye getClass mishe inam zad: .read(new File(("src/graphic/image/p1.png")))
            PLAYER2_IMAGE= ImageIO.read(getClass().getResource("image/p2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        STAR_IMAGE= new ImageIcon(isometric.class.getResource("image/star-coin.gif")).getImage();
        SPEEDLIMITER_IMAGE= new ImageIcon(getClass().getResource("image/speedlimiter.gif")).getImage();


        JTextField xField = new JTextField(2);
        JTextField yField = new JTextField(2);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("x:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("y:"));
        myPanel.add(yField);

        while (!xField.getText().matches("[0-9]+") || !yField.getText().matches("[0-9]+")) {
            int result = JOptionPane.showConfirmDialog(null, myPanel, "ابعاد صفحه را وارد کنید", JOptionPane.DEFAULT_OPTION);
        }
        Rows = Integer.parseInt(xField.getText());
        Cols = Integer.parseInt(yField.getText());
        Game.setBoard(new Board(Cols, Rows));

        JPanel jp=new JPanel();
        jp.setBackground(new Color(151, 177, 28));
       /* JPanel jp= new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                Image backgroundImage = null;
                try {
                    backgroundImage = ImageIO.read(new File("src/graphic/image/background.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(backgroundImage, 0, 0, null);
            }};
*/

        jp.setLayout(null);
        JScrollPane jsp= new JScrollPane(jp); // scroll khastam bezaram vali bug dare
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       // jsp.add(jsp.createHorizontalScrollBar());
        //jsp.setAutoscrolls(true);
        jp.setPreferredSize(new Dimension(300,300));



        frame.getContentPane().add(jsp,BorderLayout.CENTER);
        Icon imgIcon = new ImageIcon(TILE_IMAGE);
        Icon wallIcon = new ImageIcon(WALL_IMAGE);

        for (int i = 0; i <Rows ; i++) {
            for (int j = 0; j < Cols; j++) {
                int isoX,isoY;
                JLabel label;

                int cartX = j * (TILE_HEIGHT - 1);
                int cartY = i * (TILE_HEIGHT - 1);
                isoX = (cartX - cartY);
                isoY = (cartX + cartY) / 2;
                label = new JLabel(imgIcon);
                label.setBounds(((Cols-1)*TILE_HEIGHT +isoX +fasele),isoY+fasele, TILE_WIDTH, TILE_HEIGHT); // (x,y,tool,ertefa)

                label.addMouseListener(new TileListener(i,j));

                jp.add(label);
            }
        }

        JPanel menu= new JPanel();
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));

        menu.setBackground(new Color(0, 48, 0));

        frame.getContentPane().add(menu,BorderLayout.EAST);

        int buttonsWidth=60-2 ;
        int buttonsHeight=60 ;
        JButton wallButton =CreateButton("دیوار",new ImageIcon(WALL_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight,Image.SCALE_DEFAULT)),"wall");
        menu.add(wallButton);
        Icon starIcon=new ImageIcon(STAR_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight,Image.SCALE_DEFAULT));
        menu.add(CreateButton("ستاره",starIcon,"star"));
        menu.add(CreateButton("سرعتگیر",new ImageIcon(SPEEDLIMITER_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight,Image.SCALE_DEFAULT)),"speedlimiter"));
        menu.add(CreateButton("بازیکن 1",new ImageIcon(PLAYER1_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight+10,Image.SCALE_DEFAULT)),"p1")); //heseh nabood felan icon ro null gozashtam movaghat
        menu.add(CreateButton("بازیکن 2",new ImageIcon(PLAYER2_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight+10,Image.SCALE_DEFAULT)),"p2"));





        frame.setSize((Cols+2)*TILE_WIDTH,500);
        frame.setVisible(true);
    }
    public JButton CreateButton(String nameFa,Icon icon,String nameEn){
        JButton button;
        if (icon==null)
            button=new JButton(nameFa);
          else
            button=new JButton(nameFa,icon);
        button.setSelected(true);
        button.setBackground(Color.WHITE);
        button.setSize(new Dimension(70,70));

        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(2, 2, 2, 2);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.TOP);
       // button.setBorder(line);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selected=nameEn;
                for (Component component : e.getComponent().getParent().getComponents()) {
                    component.setBackground(Color.white);
                    component.setForeground(Color.black);
                }
                e.getComponent().setBackground(Color.blue);
                e.getComponent().setForeground(Color.white); // rang matn
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton)e.getComponent()).setText(nameFa);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (icon!=null) ((JButton)e.getComponent()).setText("");
            }
        });
        return button;
    }
    public static void drawItem(int i,int j,String type){
        drawItem(i,j,type,-1);
    }
    public static void drawItem(int i,int j,String type,int limit){
        int cartX = j * (TILE_HEIGHT - 1);
        int cartY = i * (TILE_HEIGHT - 1);
        int isoX = (cartX - cartY);
        int isoY = (cartX + cartY) / 2;

        Image player1=  PLAYER1_IMAGE.getScaledInstance(36,50,Image.SCALE_DEFAULT);
        Image player2 = PLAYER2_IMAGE.getScaledInstance(36, 50, Image.SCALE_DEFAULT);
        Image wall = WALL_IMAGE.getScaledInstance(TILE_WIDTH, TILE_HEIGHT + 8, Image.SCALE_DEFAULT);
        Image star = STAR_IMAGE.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image speedlimiter = SPEEDLIMITER_IMAGE.getScaledInstance(60, 68, Image.SCALE_DEFAULT);
        JLabel item;
        int z; // ino kar nadashte bashid alakiye
        switch (type){
            case "star":
                item=new JLabel(new ImageIcon(star));
                item.setBounds(((Cols-1)*TILE_HEIGHT +isoX +fasele),isoY-13 +fasele, TILE_WIDTH, TILE_HEIGHT);
                z=300;
                break;
            case "speedlimiter":
                item=new JLabel(new ImageIcon(speedlimiter));
                item.setVerticalTextPosition(SwingConstants.TOP);
                item.setToolTipText(""+limit);
                ToolTipManager.sharedInstance().setInitialDelay(50);
                item.setBounds(((Cols-1)*TILE_HEIGHT +isoX +fasele),isoY-27+fasele, TILE_WIDTH, TILE_HEIGHT+12);
                z=400;
                break;
            case "p1":
                item=new JLabel(new ImageIcon(player1));
                item.setBounds(((Cols-1)*TILE_HEIGHT +isoX +fasele),isoY-13 +fasele, TILE_WIDTH, TILE_HEIGHT);
                z=600;
                break;
            case "p2":
                item=new JLabel(new ImageIcon(player2));
                item.setBounds(((Cols-1)*TILE_HEIGHT +isoX +fasele),isoY-13+fasele, TILE_WIDTH, TILE_HEIGHT);
                z=500;
                break;
            case "wall":
                item=new JLabel(new ImageIcon(wall));
                item.setBounds(((Cols-1)*TILE_HEIGHT +isoX +fasele),isoY-8+fasele, TILE_WIDTH, TILE_HEIGHT+8);
                z=100;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        item.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //e.getComponent().setVisible(false);
                isometric.frame.getLayeredPane().remove(e.getComponent());
                isometric.frame.getLayeredPane().repaint();
                Game.getBoardInstance().setBoard(null,i,j);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // z=(Cols-j)+z;
        frame.getLayeredPane().add(item);
    //    System.out.println("index="+(z));

        // frame.getLayeredPane().setLayer(item,z);
        frame.getLayeredPane().moveToFront(item);

    }

    public static void main(String[] args) {
        new isometric();
    }
}

class TileListener implements MouseListener{
    private int i;
    private int j;
    public TileListener(int i, int j){
        this.i=i;
        this.j=j;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("Clicked :(%d,%d)\n", i, j);
        Board board=Game.getBoardInstance();
        if(board.getBoardElement(i,j)==null) {
            switch (isometric.selected) {
                case "wall":
                    isometric.drawItem(i, j, "wall");
                    board.setBoard(new Wall(i,j),i,j);
                    break;

                case "star":
                    isometric.drawItem(i, j, "star");
                    board.setBoard(new Star(i,j),i,j);
                    break;

                case "speedlimiter":
                    String input = JOptionPane.showInputDialog("مقدار محدودیت را وارد کنید");
                    if (input != null && input.matches("[0-9]+")) {
                        int limit = Integer.parseInt(input);
                        // if ()  shart bara adad soartgir
                        isometric.drawItem(i, j, "speedlimiter", limit);
                        board.setBoard(new SpeedLimiter(i,j,limit),i,j);
                        //
                    }
                    break;
                case "p1":
                    isometric.drawItem(i, j, "p1");
                    Player.getP1().setPointOfPlayer(i,j);
                    board.setBoard(Player.getP1(),i,j);
                    break;

                case "p2":
                    isometric.drawItem(i, j, "p2");
                    Player.getP2().setPointOfPlayer(i,j);
                    board.setBoard(Player.getP2(),i,j);
                    break;
            }
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
        Icon imgIcon = new ImageIcon(isometric.SElECTED_IMAGE);
        ((JLabel)e.getComponent()).setIcon(imgIcon);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Icon imgIcon = new ImageIcon(isometric.TILE_IMAGE);
        ((JLabel)e.getComponent()).setIcon(imgIcon);
        isometric.frame.getLayeredPane().moveToBack(e.getComponent());
    }
}