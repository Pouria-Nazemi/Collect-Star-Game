package graphic;

import GameLogic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static graphic.GameCreator.*;

public class GameGUI {
    static PlayerIcon P1;
    static PlayerIcon P2;
    final static ImageIcon effect = new ImageIcon("src/graphic/image/effect.gif");
    private static JLabel ScoreP1;
    private static JLabel ScoreP2;
    private static JLabel TurnInfo;
    private static JTextArea listP1;
    private static JTextArea listP2;

    public GameGUI(){
        frame.getContentPane().removeAll();
        frame.setTitle("Star Collector Game");
        /* The START gif that represents before the the game starts */
        JLabel loadingStart = new JLabel( new ImageIcon("src/graphic/image/wait for start.gif") );
        frame.add(loadingStart);
        frame.validate();
        /* A delay for starting loading */
        Timer wait = new Timer(1200,e -> {
            frame.remove(loadingStart);
            createLayeredPane();
            createBoard();
            frame.validate();
        });
        wait.setRepeats(false); /* instructs the Timer to send only one action event to the listeners */
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
        createScoreboards();
        createLimitersList();
    }

    /* Making some icons to show the scores to the players an keep them UP-TO-DATE ! */
    private void createScoreboards(){
/*    /// in baraye load font az folder. emrooz okayesh mikonam ///
        try {
           Font font= Font.createFont(Font.TRUETYPE_FONT, new File("A.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        TurnInfo = new JLabel(" نوبت بازیکن 1  - با کلیک روی یکی از خانه ها حرکت کنید ",SwingConstants.CENTER);
        TurnInfo.setBounds((frame.getWidth()-300)/2,0,300,30);
        TurnInfo.setForeground(new Color(11, 86, 1));

        frame.getLayeredPane().add(TurnInfo,new Integer(4));

        /* An icon to show the player scores during the game */
        ImageIcon blueCircle = new ImageIcon("src/graphic/image/BlueCircle.gif");
        JLabel circleP1 = new JLabel(blueCircle);
        circleP1.setBounds(0,0,100,100);

        JLabel P1Lable = new JLabel("بازیکن یک");
        P1Lable.setBounds(20,0,150,150);
        P1Lable.setFont(new Font("Calibri",Font.BOLD,16));

        ScoreP1 = new JLabel("0");
        ScoreP1.setBounds(40,0,150,90);
        ScoreP1.setFont(new Font("Calibri",Font.BOLD,40));
        ScoreP1.setForeground(Color.yellow);

        /* Adding those icons to the frame in different layers */
        frame.getLayeredPane().add(circleP1,new Integer(7));
        frame.getLayeredPane().add(P1Lable,new Integer(8));
        frame.getLayeredPane().add(ScoreP1,new Integer(9));

        /* An icon to show the player scores during the game */
        ImageIcon greenCircle = new ImageIcon("src/graphic/image/GreenCircle.gif");
        JLabel circleP2 = new JLabel(greenCircle);
        circleP2.setBounds(frame.getWidth()-120,0,100,100);

        JLabel P2Lable = new JLabel("بازیکن دو");
        P2Lable.setBounds(frame.getWidth()-95,0,150,150);
        P2Lable.setFont(new Font("Calibri",Font.BOLD,16));

        ScoreP2 = new JLabel("0");
        ScoreP2.setBounds(frame.getWidth()-80,0,150,90);
        ScoreP2.setFont(new Font("Calibri",Font.BOLD,40));
        ScoreP2.setForeground(Color.yellow);

        frame.getLayeredPane().add(circleP2,new Integer(7));
        frame.getLayeredPane().add(P2Lable,new Integer(8));
        frame.getLayeredPane().add(ScoreP2,new Integer(9));

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                circleP2.setLocation(frame.getWidth()-120,circleP2.getY());
                P2Lable.setLocation(frame.getWidth()-95,P2Lable.getY());
                ScoreP2.setLocation(frame.getWidth()-80,ScoreP2.getY());
                TurnInfo.setLocation((frame.getWidth()-300)/2,0);
            }
        });

    }

    /* Here we update the stars' count for each player  */
    static void updateScores(){
        ScoreP1.setText(""+Player.getP1().getScore());
        ScoreP2.setText(""+ Player.getP2().getScore());
    }

    /* Making some icons to show the speed limiters' value to the players an keep them UP-TO-DATE ! */
    private  void createLimitersList(){
        int frameWidth= frame.getWidth();
        int frameHeight= frame.getHeight();
        Image frame1Image = new ImageIcon("src/graphic/image/frame1.png").getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH);
        JLabel frame1 = new JLabel(new ImageIcon(frame1Image));
        frame1.setBounds(0,frameHeight-250,150,200);
        frame.getLayeredPane().add(frame1,new Integer(2));

        Image frame2Image = new ImageIcon("src/graphic/image/frame2.png").getImage().getScaledInstance(160, 210,Image.SCALE_SMOOTH);
        JLabel frame2 = new JLabel(new ImageIcon(frame2Image));
        frame2.setBounds(frameWidth-180,frameHeight-250, 160, 210);
        frame.getLayeredPane().add(frame2,new Integer(2));

        listP1 = new JTextArea();
        listP1.setToolTipText("سرعتگیر های بازیکن یک");
        listP1.setBorder(BorderFactory.createEmptyBorder(10,60,2,2)); /* Creates an empty border */
        listP1.setEditable(false);
        listP1.setFont(new Font(listP1.getFont().getName(),Font.BOLD,25));
        listP1.setSize(new Dimension(150,200));
        JScrollPane scroll1 = new JScrollPane(listP1);
        scroll1.setAutoscrolls(false);
        scroll1.setPreferredSize(new Dimension(150,400));
        scroll1.setBounds(7,frameHeight-250+10, 136, 185);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getLayeredPane().add(scroll1,new Integer(1));

        /* Just like the previous part but for another player */
        listP2= new JTextArea();
        listP2.setToolTipText("سرعتگیر های بازیکن دو");
        listP2.setEditable(false);
        listP2.setFont(new Font(listP2.getFont().getName(),Font.BOLD,25));
        listP2.setSize(new Dimension(150,200));
        listP2.setBorder(BorderFactory.createEmptyBorder(10,50,2,2));
        JScrollPane scroll2 = new JScrollPane(listP2);
        scroll2.setBounds(frameWidth-155,frameHeight-225, 109, 165);

        scroll2.setPreferredSize(new Dimension(190,400));
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getLayeredPane().add(scroll2,new Integer(1));

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame1.setLocation(frame1.getX(),frame.getHeight()-250);
                frame2.setLocation(frame.getWidth()-180,frame.getHeight()-250);
                scroll1.setLocation(7,frame.getHeight()-240);
                scroll2.setLocation(frame.getWidth()-155,frame.getHeight()-225);
            }
        });

    }

    /* Here we keep the limit lists UP-TO-DATE and limits will be added to the appropriate list */
    static void printLimits(){
        StringBuilder text1 = new StringBuilder();
        for (Integer integer : Player.getP2().getLimit()) {
            text1.append(integer).append("\n"); /* Getting the limit from the ' Array list ' and put them together */
        }
        listP1.setText(text1.toString());

        /* Just like the previous part */
        StringBuilder text2 = new StringBuilder();
        for (Integer limit : Player.getP1().getLimit()) {
            text2.append(limit).append("\n");
        }
        listP2.setText(text2.toString());
    }

    /* A method to update the turn , by using GameLogic's classes */
    static void UpdateTurnInfo(){
        int turn = GameController.getTurn();
        String text = String.format("نوبت بازیکن %d", turn);
        Player player;

        if (turn==1)
            player = Player.getP1();
        else
            player= Player.getP2();

        if (player.getLimit().size() != 0) {
            int limit = player.getLimit().get(0);
            text+=String.format(" -  شما حداکثر %d خانه میتوانید حرکت کنید", limit);
            TurnInfo.setForeground(Color.red);
        }else
            /* setting foreground */
            TurnInfo.setForeground(new Color(11, 86, 1));

        TurnInfo.setText(text);
    }

    private void createBoard(){
        Icon imgIcon = new ImageIcon(TILE_IMAGE);
        for (int i = 0; i <Rows ; i++) {
            for (int j = 0; j < Cols; j++) {
                Point p = getCoordinate(i,j);
                int isoX = p.x;
                int isoY = p.y;
                JLabel label;
                label = new JLabel(imgIcon);
                label.setBounds((isoX ),isoY, TILE_WIDTH, TILE_HEIGHT); // ( x , y , width , height )
                label.addMouseListener(new TilesMouseListener(i,j));
                layeredPane.add(label,new Integer(1));
            }
        }
        drawAllElements();
    }

    /* As its name shows , this method would draw all elements !  */
    private void drawAllElements(){
        Board board = Game.getBoardInstance();  /* Using the board that is saved in another class : "Game" */

        for (int i = Rows - 1; i >= 0; i--) {
            for (int j = Cols - 1; j >= 0; j--) {
                Object element = board.getBoardElement(i, j); /* getBoardElement gives us just one specific element that is saved in the array */
                if (element != null) {
                    String elementName = element.getClass().getName();
                    if (element instanceof Player) {
                        if (element == Player.getP1())
                            elementName = "P1";
                        else
                            elementName = "P2";
                    }
                    if (element instanceof SpeedLimiter) {
                        int limit = ((SpeedLimiter) element).getLimitingValue();
                        drawItem(i, j, elementName, limit);
                    } else
                        drawItem(i, j, elementName, -1);
                }
            }
        }
    }

    /* A method to draw each item in different layers */
    public void drawItem(int i,int j,String type,int limit){
        Point p=getCoordinate(i,j);
        int isoX=p.x;
        int isoY= p.y;

        Image player1 = PLAYER1_IMAGE.getScaledInstance(36,50,Image.SCALE_SMOOTH);
        Image player2 = PLAYER2_IMAGE.getScaledInstance(36, 50, Image.SCALE_SMOOTH);
        Image wall = WALL_IMAGE.getScaledInstance(TILE_WIDTH, 58, Image.SCALE_SMOOTH);
        Image star = STAR_IMAGE.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        Image speedlimiter = SPEEDLIMITER_IMAGE.getScaledInstance(60, 68, Image.SCALE_DEFAULT);
        JLabel item;
        type = type.replaceAll("GameLogic.","");
        int layer;
        switch (type){
            case "Star":
                item = new JLabel(new ImageIcon(star));
                item.setBounds((isoX ),isoY-13 , TILE_WIDTH, TILE_HEIGHT);
                layer = 3;
                break;
            case "SpeedLimiter":
                item = new JLabel(new ImageIcon(speedlimiter));
                item.setVerticalTextPosition(SwingConstants.TOP);
                item.setToolTipText("سرعتگیر دارای ارزش "+limit);
                /* During the game , while the player moves the mouse on the speed limiter , the text will be shown for a short time */
                ToolTipManager.sharedInstance().setInitialDelay(50);
                item.setBounds((isoX ),isoY-27, TILE_WIDTH, TILE_HEIGHT+12);
                layer = 4;
                break;
            case "P1":
                item = new PlayerIcon(i,j,new ImageIcon(player1),1);
                P1 = (PlayerIcon) item;
                item.setToolTipText("بازیکن یک");
                ToolTipManager.sharedInstance().setInitialDelay(50);
                item.setBounds((isoX ),isoY-13 , TILE_WIDTH, TILE_HEIGHT);
                layer = 6;
                break;
            case "P2":
                item = new PlayerIcon(i,j,new ImageIcon(player2),2);
                P2 = (PlayerIcon) item;
                item.setToolTipText("بازیکن دو");
                ToolTipManager.sharedInstance().setInitialDelay(50);
                item.setBounds((isoX ),isoY-13, TILE_WIDTH, TILE_HEIGHT);
                layer = 5;
                break;
            case "Wall":
                item = new JLabel(new ImageIcon(wall));
                item.setBounds((isoX ),isoY-8, TILE_WIDTH, TILE_HEIGHT+8);
                layer = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        layeredPane.add(item,new Integer(layer)); /* When this is an INTEGER , this is not an index , that's a layer , then ! */
    }

    /* This method removes items with effect */
    static void removeItem(Point point){
        JLabel item = (JLabel) findComponent(point);
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
        for (Component component : layeredPane.getComponentsInLayer(3)) {  /*  3 == stars */
            if(component.getX()==p.x && component.getY()==(p.y-13))
                return component;
        }
        for (Component component : layeredPane.getComponentsInLayer(4)) { /*  4 == speedlimiters  */
            if(component.getX()==p.x && component.getY()==(p.y-27))
                return component;
        }
        return null;
    }

    /* A getter for coordinate */
    static Point getCoordinate(int i,int j){
        int cartX = j * (TILE_HEIGHT );
        int cartY = i * (TILE_HEIGHT );
        int isoX = (cartX - cartY);
        int isoY = (cartX + cartY) / 2;
        return new Point(isoX + faseleOfoghi , isoY + faseleAmoodi );
    }

    static void checkWin(){
        if(Star.getCount()==0) {
            layeredPane.removeAll();
            layeredPane.setOpaque(true); /* Component paints every pixel within its bounds */
            layeredPane.repaint();
            Player P1 = Player.getP1();
            Player P2 = Player.getP2();
            int frameWidth= frame.getWidth();
            int frameHeight= frame.getHeight();
            if (P1.getScore() == P2.getScore()){
                /* A GIF file that will be shown at the end of the game */
                Image drawImage = new ImageIcon("src/graphic/image/draw.gif").getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_DEFAULT);
                JLabel draw = new JLabel(new ImageIcon(drawImage));
                draw.setBounds(0, 0, frameWidth, frameHeight);

                JLabel text = new JLabel("DRAW!");
                text.setHorizontalTextPosition(SwingConstants.CENTER);
                text.setForeground(Color.green);
                text.setFont(new Font(text.getFont().getName(),Font.BOLD,100));
                text.setBounds((frameWidth-350)/2,50,500,100);
                frame.getLayeredPane().add(draw, new Integer(5));
                frame.getLayeredPane().add(text, new Integer(6));
            } else {
                Image winImage = new ImageIcon("src/graphic/image/win.gif").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
                JLabel win = new JLabel(new ImageIcon(winImage));
                win.setBounds(0, 0, frameWidth, frameHeight);
                win.setOpaque(true);
                win.setBackground(new Color(49, 49, 49));
                frame.getLayeredPane().add(win, new Integer(5));
                JLabel text = new JLabel();
                if(P1.getScore() > P2.getScore()){
                    text.setText("P1");
                }else
                    text.setText("P2");
                text.setHorizontalTextPosition(SwingConstants.CENTER);
                text.setForeground(Color.red);
                text.setFont(new Font(text.getFont().getName(),Font.BOLD,85));
                text.setBounds((frameWidth/2)-50,(frameHeight/2)-35,500,100);
                frame.getLayeredPane().add(text, new Integer(6));


            }
                JButton back=new JButton("Back to Main Menu");
                back.setBounds((frameWidth/2)-150,frameHeight-170,300,50);
                back.setBackground(new Color(60, 132, 222));
                back.addActionListener(e -> {
                    frame.dispose();
                    new GameMainMenu();
                    Player.getP1().resetScore();
                    Player.getP2().resetScore();
                });
                frame.getLayeredPane().add(back, new Integer(7));
        }
    }
}
/* In this type of 3D game boards , it is better to have the movable element's images,in different views */
class PlayerIcon extends JLabel {
    int i;
    int j;
    final int id;
    private final Image UP_IMAGE;
    private final Image DOWN_IMAGE;
    private final Image LEFT_IMAGE;
    private final Image RIGHT_IMAGE;

    public PlayerIcon( int i , int j , ImageIcon imageIcon , int id ) {
        super(imageIcon);
        this.i = i;
        this.j = j;
        this.id = id;
        UP_IMAGE = new ImageIcon("src/graphic/image/P"+id+"_up.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
        DOWN_IMAGE = new ImageIcon("src/graphic/image/P"+id+"_down.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
        LEFT_IMAGE = new ImageIcon("src/graphic/image/P"+id+"_left.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
        RIGHT_IMAGE = new ImageIcon("src/graphic/image/P"+id+"_right.png").getImage().getScaledInstance(36,50,Image.SCALE_SMOOTH);;
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
        int iGoal = i-value;
        Timer timer = new Timer(250, new ActionListener() {
            private int iStart = i;

            @Override
            public void actionPerformed(ActionEvent e) {
                Point point = GameGUI.getCoordinate(iStart, j);
                GameGUI.removeItem(point);
                setLocation(point.x , point.y  - 13);
                if (iStart == iGoal) {
                    Timer thisTimer= (Timer) e.getSource();
                    thisTimer.stop();  /* Here we check , whenever the player's move is finished , it should stop moving  */
                    GameGUI.checkWin();
                }
                iStart--;
            }
        });
        timer.start();
        i=iGoal;
    }

    /* Just like the previous part , but in another direction */
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
                if (iStart == iGoal){
                    Timer thisTimer= (Timer) e.getSource();
                    thisTimer.stop();  /* Here we check , whenever the player's move is finished , it should stop moving  */
                    GameGUI.checkWin();
                }
                iStart ++;
            }
        });
        timer.start();
        i=iGoal;
    }

    /* Just like the previous part , but in another direction */
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
                if (jStart == jGoal){
                    Timer thisTimer= (Timer) e.getSource();
                    thisTimer.stop();  /* Here we check , whenever the player's move is finished , it should stop moving  */
                    GameGUI.checkWin();
                }
                jStart--;
            }
        });
        timer.start();
        j=jGoal;
    }

    /* Just like the previous part , but in another direction */
    public void moveRight(int value){
        setIcon(new ImageIcon(RIGHT_IMAGE));
        int jGoal=j+value;
        Timer timer = new Timer(250, new ActionListener() {
            private int jStart = j;

            @Override
            public void actionPerformed(ActionEvent e) {
                Point point = GameGUI.getCoordinate(i, jStart);
                GameGUI.removeItem(point);
                setLocation(point.x , point.y  - 13);
                if (jStart == jGoal){
                    Timer thisTimer= (Timer) e.getSource();
                    thisTimer.stop();  /* Here we check , whenever the player's move is finished , it should stop moving  */
                    GameGUI.checkWin();
                }
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
        }else /* if the move is not valid */
            JOptionPane.showMessageDialog(null, "این حرکت مجاز نمی باشد!");
        GameGUI.printLimits();
        GameGUI.updateScores();
        GameGUI.UpdateTurnInfo();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Icon imgIcon = new ImageIcon(GameCreator.SElECTED_IMAGE);
        ((JLabel)e.getComponent()).setIcon(imgIcon);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Icon imgIcon = new ImageIcon(GameCreator.TILE_IMAGE);
        ((JLabel)e.getComponent()).setIcon(imgIcon);
    }

}