package graphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class isometric {
    static JFrame frame;
    static String selected="";
    static final int TILE_WIDTH=96;
    static final int TILE_HEIGHT=50;
    static int Cols=5;
    static int Rows=5;

    isometric(){
        frame =new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocation(500,200);

        JTextField xField = new JTextField(2);
        JTextField yField = new JTextField(2);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("x:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("y:"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "ابعاد صفحه را وارد کنید", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + xField.getText());
            System.out.println("y value: " + yField.getText());
            Rows= Integer.parseInt(xField.getText());
            Cols= Integer.parseInt(yField.getText());
        }

        JPanel jp= new JPanel();

        jp.setLayout(null);
        JScrollPane jsp= new JScrollPane(jp); // scroll khastam bezaram vali bug dare
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       // jsp.add(jsp.createHorizontalScrollBar());
        //jsp.setAutoscrolls(true);
        jp.setPreferredSize(new Dimension(300,300));

        frame.getContentPane().add(jsp,BorderLayout.CENTER);
        Image img= new ImageIcon(getClass().getResource("image/ISOgrass2.png")).getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT,Image.SCALE_DEFAULT);
        Icon imgIcon = new ImageIcon(img);
        Image wall_img= new ImageIcon(getClass().getResource("image/wall.png")).getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT+8,Image.SCALE_DEFAULT);
        Icon wallIcon = new ImageIcon(wall_img);

        for (int i = 0; i <Rows ; i++) {
            for (int j = 0; j < Cols; j++) {
                int isoX,isoY;
                JLabel label;

                int cartX = j * (TILE_HEIGHT - 1);
                int cartY = i * (TILE_HEIGHT - 1);
                isoX = (cartX - cartY);
                isoY = (cartX + cartY) / 2;
                label = new JLabel(imgIcon);
                label.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY, TILE_WIDTH, TILE_HEIGHT); // (x,y,tool,ertefa)

                label.addMouseListener(new TileListener(i,j));

                jp.add(label);
            }
        }

        JPanel menu= new JPanel();
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));

        frame.getContentPane().add(menu,BorderLayout.EAST);

        JButton wallButton =CreateButton("دیوار",wallIcon,"wall");
        menu.add(wallButton);
        Icon starIcon=new ImageIcon(new ImageIcon(getClass().getResource("image/star-coin.gif")).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        menu.add(CreateButton("ستاره",starIcon,"star"));
        menu.add(CreateButton("بازیکن 1",null,"p1")); //heseh nabood felan icon ro null gozashtam movaghat
        menu.add(CreateButton("بازیکن 2",null,"p2"));





        frame.setSize((Cols+2)*TILE_WIDTH,400);
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

        int cartX = j * (TILE_HEIGHT - 1);
        int cartY = i * (TILE_HEIGHT - 1);
        int isoX = (cartX - cartY);
        int isoY = (cartX + cartY) / 2;
        try {
            Image player1= ImageIO.read(isometric.class.getResource("image/p1.png")); // bejaye getClass mishe inam zad: .read(new File(("src/graphic/image/p1.png")))
            player1=  player1.getScaledInstance(36,50,Image.SCALE_DEFAULT);
            Image player2= ImageIO.read(isometric.class.getResource("image/p2.png"));
            player2=  player2.getScaledInstance(36,50,Image.SCALE_DEFAULT);
            Image wall= ImageIO.read(isometric.class.getResource("image/wall.png"));
            wall=  wall.getScaledInstance(TILE_WIDTH,TILE_HEIGHT+8,Image.SCALE_DEFAULT);
            Image star= new ImageIcon(isometric.class.getResource("image/star-coin.gif")).getImage();
            star=  star.getScaledInstance(30,30,Image.SCALE_DEFAULT);
            JLabel item;
            switch (type){
                case "star":
                    item=new JLabel(new ImageIcon(star));
                    item.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY-13, TILE_WIDTH, TILE_HEIGHT);
                    break;
                case "p1":
                    item=new JLabel(new ImageIcon(player1));
                    item.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY-13, TILE_WIDTH, TILE_HEIGHT);
                    break;
                case "p2":
                    item=new JLabel(new ImageIcon(player2));
                    item.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY-13, TILE_WIDTH, TILE_HEIGHT);
                    break;
                case "wall":
                    item=new JLabel(new ImageIcon(wall));
                    item.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY-8, TILE_WIDTH, TILE_HEIGHT+8);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }


            frame.getLayeredPane().add(item);

            frame.getLayeredPane().moveToFront(item);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        switch (isometric.selected){
            case "wall":
                isometric.drawItem(i,j,"wall");
                // setboard(i,j, ... )
                break;
            case "star":
                isometric.drawItem(i,j,"star");

                break;
            case "speedlimiter":
                //felan khaliye
                break;
            case "p1":
                isometric.drawItem(i,j,"p1");

                break;
            case "p2":
                isometric.drawItem(i,j,"p2");

                break;
            //....
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
        Image img= new ImageIcon(getClass().getResource("image/selected-grass.png")).getImage().getScaledInstance(isometric.TILE_WIDTH, isometric.TILE_HEIGHT,Image.SCALE_DEFAULT);
        Icon imgIcon = new ImageIcon(img);
        ((JLabel)e.getComponent()).setIcon(imgIcon);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Image img= new ImageIcon(getClass().getResource("image/ISOgrass2.png")).getImage().getScaledInstance(isometric.TILE_WIDTH, isometric.TILE_HEIGHT,Image.SCALE_DEFAULT);
        Icon imgIcon = new ImageIcon(img);
        ((JLabel)e.getComponent()).setIcon(imgIcon);
        isometric.frame.getLayeredPane().moveToBack(e.getComponent());
    }
}