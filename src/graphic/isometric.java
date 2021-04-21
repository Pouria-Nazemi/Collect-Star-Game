package graphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class isometric {
    static JFrame f;
    public static final int TILE_WIDTH=96;
    public static final int TILE_HEIGHT=50;
    public static int Cols=5;
    public static int Rows=5;
    isometric(){
        f=new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocation(500,200);


        JPanel jp= new JPanel();
 //       GridLayout gridLayout = new GridLayout(Rows, Cols);
        jp.setLayout(null);
        f.getContentPane().add(jp,BorderLayout.CENTER);
        Image img= new ImageIcon(getClass().getResource("image/ISOgrass2.png")).getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT,Image.SCALE_DEFAULT);
        Icon imgIcon = new ImageIcon(img);
        Image wall_img= new ImageIcon(getClass().getResource("image/wall.png")).getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT+8,Image.SCALE_DEFAULT);
        Icon wallIcon = new ImageIcon(wall_img);
        for (int i = 0; i <Rows ; i++) {
            for (int j = 0; j < Cols; j++) {
                int isoX,isoY;
                JLabel label;
                if(i==3 && j<3){

                    drawItem(i-1,j,"star");
                    drawItem(i,j,"wall");

                }
                    int cartX = j * (TILE_HEIGHT - 1);
                    int cartY = i * (TILE_HEIGHT - 1);
                     isoX = (cartX - cartY);
                     isoY = (cartX + cartY) / 2;
                     label = new JLabel(imgIcon);
                    label.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY, TILE_WIDTH, TILE_HEIGHT); // baraye rahati taghir

                label.addMouseListener(new TileInGame(i,j));

               // label.setLocation(((Cols-1)*TILE_HEIGHT +isoX ),isoY);
                jp.add(label);
            }
        }
//
        f.setSize((Cols+1)*TILE_WIDTH,300);
        f.setVisible(true);
    }

    public void drawItem(int i,int j,String type){

        int cartX = j * (TILE_HEIGHT - 1);
        int cartY = i * (TILE_HEIGHT - 1);
        int isoX = (cartX - cartY);
        int isoY = (cartX + cartY) / 2;
        try {
            Image wall= ImageIO.read(getClass().getResource("image/wall.png"));
            wall=  wall.getScaledInstance(TILE_WIDTH,TILE_HEIGHT+8,Image.SCALE_DEFAULT);
            Image star= new ImageIcon(getClass().getResource("image/star-coin.gif")).getImage();
            star=  star.getScaledInstance(30,30,Image.SCALE_DEFAULT);
            JLabel item;
            switch (type){
                case "star":
                    item=new JLabel(new ImageIcon(star));
                    item.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY-13, TILE_WIDTH, TILE_HEIGHT);
                    break;
                case "wall":
                    item=new JLabel(new ImageIcon(wall));
                    item.setBounds(((Cols-1)*TILE_HEIGHT +isoX ),isoY-8, TILE_WIDTH, TILE_HEIGHT+8);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            f.getLayeredPane().add(item);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new isometric();
    }
}

class TileInGame implements MouseListener{
    private int i;
    private int j;
    public TileInGame(int i, int j){
        this.i=i;
        this.j=j;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("Clicked :(%d,%d)\n", i, j);
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
        isometric.f.getLayeredPane().moveToBack(e.getComponent());
    }
}