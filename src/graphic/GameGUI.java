package graphic;

import GameLogic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static graphic.isometric.*;

public class GameGUI {
    static PlayerIcon P1;
    static PlayerIcon P2;
    final static ImageIcon effect=new ImageIcon("src/graphic/image/effect.gif");

    public GameGUI(){
       frame.getContentPane().removeAll();
       JLabel loadingStart= new JLabel(new ImageIcon("src/graphic/image/wait for start.gif"));
       frame.add(loadingStart);
       frame.validate(); // ino khodamam nemidoonam chie:|  vali nabashe safhe Load nemishe
       Timer wait=new Timer(1200,e -> {
           frame.remove(loadingStart);
           createLayeredPane();
           createBoard();
           frame.validate();
       });
       wait.setRepeats(false);
       wait.start();

    }

    private void createLayeredPane(){
        layeredPane= new JLayeredPane();
        int scrollHeight= ((Rows+Cols)*(TILE_HEIGHT )/2) +faseleAmoodi;
        int scrollWidth=Cols * (TILE_HEIGHT )+faseleOfoghi +80;
        JScrollPane scrollPane= new JScrollPane(layeredPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        layeredPane.setPreferredSize(new Dimension(scrollWidth,scrollHeight));
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

        JLabel background = new JLabel(new ImageIcon(BACKGROUND_IMAGE));
        background.setBounds(0,0,scrollWidth+500,scrollHeight+400);
        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Image scaledBackground=BACKGROUND_IMAGE.getScaledInstance(e.getComponent().getWidth(),e.getComponent().getHeight(),Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledBackground));
                background.setBounds(0,0,e.getComponent().getWidth(),e.getComponent().getHeight());
            }
        });
        layeredPane.add(background,new Integer(0));

    }

    private void createBoard(){
        Icon imgIcon = new ImageIcon(TILE_IMAGE);
        for (int i = 0; i <Rows ; i++) {
            for (int j = 0; j < Cols; j++) {
                Point p=getCoordinate(i,j);
                int isoX=p.x;
                int isoY= p.y;
                JLabel label;
                label = new JLabel(imgIcon);
                label.setBounds((isoX ),isoY, TILE_WIDTH, TILE_HEIGHT); // (x,y,tool,ertefa)
                label.addMouseListener(new TilesMouseListener(i,j));
                layeredPane.add(label,new Integer(1));
            }
        }
        drawAllElements();
    }
    private void drawAllElements(){
        Board board= Game.getBoardInstance();

        for (int i = Rows - 1; i >= 0; i--) {
            for (int j = Cols - 1; j >= 0; j--) {
                  Object  element= board.getBoardElement(i,j);
                  if(element!=null) {
                      String elementName = element.getClass().getName();
                      if (element instanceof Player) {
                          if (element == Player.getP1())
                              elementName = "P1";
                          else
                              elementName = "P2";
                      }
                      if(element instanceof SpeedLimiter){
                          int limit= ((SpeedLimiter) element).getLimitingValue();
                          drawItem(i, j, elementName, limit);
                      }else
                          drawItem(i, j, elementName, -1);
                  }
            }
        }

    }

    public void drawItem(int i,int j,String type,int limit){
        Point p=getCoordinate(i,j);
        int isoX=p.x;
        int isoY= p.y;

        Image player1=  PLAYER1_IMAGE.getScaledInstance(36,50,Image.SCALE_SMOOTH);
        Image player2 = PLAYER2_IMAGE.getScaledInstance(36, 50, Image.SCALE_SMOOTH);
        Image wall = WALL_IMAGE.getScaledInstance(TILE_WIDTH, TILE_HEIGHT + 8, Image.SCALE_SMOOTH);
        Image star = STAR_IMAGE.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image speedlimiter = SPEEDLIMITER_IMAGE.getScaledInstance(60, 68, Image.SCALE_DEFAULT);
        JLabel item;
        type=type.replaceAll("GameLogic.","");
        int layer;
        switch (type){
            case "Star":
                item=new JLabel(new ImageIcon(star));
                item.setBounds((isoX ),isoY-13 , TILE_WIDTH, TILE_HEIGHT);
                layer=3;
                break;
            case "SpeedLimiter":
                item=new JLabel(new ImageIcon(speedlimiter));
                item.setVerticalTextPosition(SwingConstants.TOP);
                item.setToolTipText("سرعتگیر دارای ارزش "+limit);
                ToolTipManager.sharedInstance().setInitialDelay(50);
                item.setBounds((isoX ),isoY-27, TILE_WIDTH, TILE_HEIGHT+12);
                layer=4;
                break;
            case "P1":
                item=new PlayerIcon(i,j,new ImageIcon(player1),1);
                P1= (PlayerIcon) item;
                item.setBounds((isoX ),isoY-13 , TILE_WIDTH, TILE_HEIGHT);

                layer=6;
                break;
            case "P2":
                item=new PlayerIcon(i,j,new ImageIcon(player2),2);
                P2= (PlayerIcon) item;
                item.setBounds((isoX ),isoY-13, TILE_WIDTH, TILE_HEIGHT);

                layer=5;
                break;
            case "Wall":
                item=new JLabel(new ImageIcon(wall));
                item.setBounds((isoX ),isoY-8, TILE_WIDTH, TILE_HEIGHT+8);
                layer=2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

          layeredPane.add(item,new Integer(layer)); // vaghti  "Integer" bashe index nis! layer ast
    }

    static void removeItem(Point point){
        JLabel item= (JLabel) findComponent(point);
        if (item!=null){
            item.setIcon(effect);
            Timer timer = new Timer(900,e -> {
                layeredPane.remove(item);
                frame.repaint();
            });
            timer.start();
            timer.setRepeats(false);
        }
    }

    private static Component findComponent(Point p){
        for (Component component : layeredPane.getComponentsInLayer(3)) {  // 3 == stars
            if(component.getX()==p.x && component.getY()==(p.y-13))
                return component;
        }
        for (Component component : layeredPane.getComponentsInLayer(4)) { // 4 == speedlimiters
            if(component.getX()==p.x && component.getY()==(p.y-27))
                return component;
        }
        return null;
    }

    static Point getCoordinate(int i,int j){
        int cartX = j * (TILE_HEIGHT );
        int cartY = i * (TILE_HEIGHT );
        int isoY = (cartX + cartY) / 2;
        int isoX = (cartX - cartY);
        return new Point(isoX+faseleOfoghi,isoY+faseleAmoodi);
    }
}


class PlayerIcon extends JLabel {
    int i;
    int j;
    final int id;
    private final Image UP_IMAGE;
    private final Image DOWN_IMAGE;
    private final Image LEFT_IMAGE;
    private final Image RIGHT_IMAGE;

    public PlayerIcon(int i, int j, ImageIcon imageIcon,int id) {
        super(imageIcon);
        this.i = i;
        this.j = j;
        this.id=id;
        UP_IMAGE=new ImageIcon("src/graphic/image/P"+id+"_up.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
        DOWN_IMAGE=new ImageIcon("src/graphic/image/P"+id+"_down.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
        LEFT_IMAGE=new ImageIcon("src/graphic/image/P"+id+"_left.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
        RIGHT_IMAGE=new ImageIcon("src/graphic/image/P"+id+"_right.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
    }


    public void moveTo(int iGoal, int jGoal) {
        if(iGoal<i)
            moveUp(i-iGoal);
        else if(iGoal>i)
            moveDown(iGoal-i);
        if(jGoal<j)
            moveLeft(j-jGoal);
        else if(jGoal>j)
            moveRight(jGoal-j);
    }

    public void moveUp(int value){
       setIcon(new ImageIcon(UP_IMAGE));
        int iGoal=i-value;
        Timer timer = new Timer(250, new ActionListener() {
            private int iStart = i;

            @Override
            public void actionPerformed(ActionEvent e) {
                Point point=GameGUI.getCoordinate(iStart, j);
                GameGUI.removeItem(point);
                setLocation(point.x , point.y  - 13);
                if (iStart == iGoal) {
                    ((Timer) e.getSource()).stop();
                }
                iStart--;
            }
        });
        timer.start();
        i=iGoal;
    }
    public void moveDown(int value){
       setIcon(new ImageIcon(DOWN_IMAGE));
        int iGoal=i+value;
        Timer timer = new Timer(250, new ActionListener() {
            private int iStart = i;

            @Override
            public void actionPerformed(ActionEvent e) {
                Point point=GameGUI.getCoordinate(iStart, j);
                GameGUI.removeItem(point);
                setLocation(point.x , point.y - 13);
                if (iStart == iGoal)
                    ((Timer) e.getSource()).stop();

                iStart ++;
            }
        });
        timer.start();
        i=iGoal;
    }
    public void moveLeft(int value){
        setIcon(new ImageIcon(LEFT_IMAGE));
        int jGoal=j-value;
        Timer timer = new Timer(250, new ActionListener() {
            private int jStart = j;

            @Override
            public void actionPerformed(ActionEvent e) {
                Point point=GameGUI.getCoordinate(i, jStart);
                GameGUI.removeItem(point);
                setLocation(point.x , point.y  - 13);
                if (jStart == jGoal)
                    ((Timer) e.getSource()).stop();
                jStart--;
            }
        });
        timer.start();
        j=jGoal;
    }
    public void moveRight(int value){
        setIcon(new ImageIcon(RIGHT_IMAGE));
        int jGoal=j+value;
        Timer timer = new Timer(250, new ActionListener() {
            private int jStart = j;

            @Override
            public void actionPerformed(ActionEvent e) {
                Point point=GameGUI.getCoordinate(i, jStart);
                GameGUI.removeItem(point);
                setLocation(point.x , point.y  - 13);
                if (jStart == jGoal)
                    ((Timer) e.getSource()).stop();
                jStart++;
            }
        });
        timer.start();
        j=jGoal;
    }
}

class TilesMouseListener implements MouseListener {
    private int i;
    private int j;
    public TilesMouseListener(int i, int j){
        this.i=i;
        this.j=j;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("Clicked :(%d,%d)\n", i, j);
        if(GameController.gettingDestinationAndMove(i,j)== true) {
            if (GameController.getTurn()==2) {
                GameGUI.P1.moveTo(i, j);
            } else {
                GameGUI.P2.moveTo(i, j);
            }
        }else
            JOptionPane.showMessageDialog(null, "این حرکت مجاز نمی باشد!");


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
    }

}