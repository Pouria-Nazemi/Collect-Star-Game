package graphic ;

import GameLogic.*;
import javax.swing.*; /* A class for giving us the ability to use SWING graphic options */
import javax.swing.border.Border; /*To put a border around a JComponent*/
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*; /* For dealing with different types of events fired by AWT components*/

public class GameCreator {
    static JFrame frame;
    static JLayeredPane layeredPane;
    static String selected="";
    static final int TILE_WIDTH = 96;
    static final int TILE_HEIGHT = 50;
    static int faseleAmoodi = 30;
    static int faseleOfoghi;
    static int Cols = 5;
    static int Rows = 5;
    static Image TILE_IMAGE;
    static Image SElECTED_IMAGE;
    static Image WALL_IMAGE;
    static Image STAR_IMAGE;
    static Image SPEEDLIMITER_IMAGE;
    static Image PLAYER1_IMAGE;
    static Image PLAYER2_IMAGE;
    static Image BACKGROUND_IMAGE;


    public GameCreator(){
        frame = new JFrame("Creating Game");
        /* to close the program automatically after pushing x bottom */
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        frame.setLocation( 400 , 150 );

        /* load images */
        TILE_IMAGE = new ImageIcon("src/graphic/image/ISOgrass2.png").getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT,Image.SCALE_SMOOTH);
        SElECTED_IMAGE = new ImageIcon("src/graphic/image/selected-grass.png").getImage().getScaledInstance(TILE_WIDTH, TILE_HEIGHT,Image.SCALE_SMOOTH);
        WALL_IMAGE = new ImageIcon("src/graphic/image/wall.png").getImage().getScaledInstance(TILE_WIDTH,TILE_HEIGHT+8,Image.SCALE_SMOOTH);
        PLAYER1_IMAGE= new ImageIcon("src/graphic/image/p1.png").getImage();
        PLAYER2_IMAGE= new ImageIcon("src/graphic/image/p2.png").getImage();
        STAR_IMAGE = new ImageIcon("src/graphic/image/star-coin.gif").getImage();
        SPEEDLIMITER_IMAGE = new ImageIcon("src/graphic/image/speedlimiter.gif").getImage();

        setBoardDimension();
        createLayeredPane();
        createBoard();
        createMenu();

        frame.setSize(( Cols + 2 ) * TILE_WIDTH , 550 );
        frame.setVisible( true );
    }

    private void setBoardDimension() {
        /* Creating text fields to receive the dimention of the game board */
        JTextField xField = new JTextField(2);
        JTextField yField = new JTextField(2);
        JPanel inputDialog = new JPanel();
        inputDialog.add(new JLabel("x:"));
        inputDialog.add(xField);
        inputDialog.add(Box.createHorizontalStrut(15)); /* A spacer */
        inputDialog.add(new JLabel("y:"));
        inputDialog.add(yField);

        while (true) {
            JOptionPane.showConfirmDialog(null, inputDialog, "ابعاد صفحه را وارد کنید", JOptionPane.DEFAULT_OPTION);
            /* To check if the input is valid or not (It prevents unwanted entering STRINGS)! */
            if((xField.getText().matches("[0-9]+") && yField.getText().matches("[0-9]+"))){
                Rows = Integer.parseInt(xField.getText());
                Cols = Integer.parseInt(yField.getText());
                if(GameController.boardDimensionValidator(Cols,Rows)){    /* Check dimensions>=2 and dimensions<=50  */
                    break;
                }else
                    JOptionPane.showMessageDialog(null, "اعداد باید بین بازه 2 تا50 باشند","ورودی نامعتبر!",JOptionPane.ERROR_MESSAGE);
            }else
                    JOptionPane.showMessageDialog(null, "شما فقط مجاز به واردکردن اعداد مثبت هستید","ورودی نامعتبر!",JOptionPane.ERROR_MESSAGE);
        }

        Game.setBoard(new Board(Cols, Rows));
    }

    private void createLayeredPane(){
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        faseleOfoghi = Rows * (TILE_HEIGHT - 1);
        int scrollHeight = ((Rows+Cols) * (TILE_HEIGHT )/2) + faseleAmoodi;
        int scrollWidth = faseleOfoghi + Cols * (TILE_HEIGHT ) + 80;
        BACKGROUND_IMAGE = new ImageIcon("src/graphic/image/background.jpg").getImage().getScaledInstance(scrollWidth+500,scrollHeight+400,Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(BACKGROUND_IMAGE));
        background.setBounds(0,0,scrollWidth+500,scrollHeight+400);

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                int width = component.getWidth();
                int height = component.getHeight();
                Image scaledBackground = BACKGROUND_IMAGE.getScaledInstance(width,height,Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledBackground));
                background.setBounds(0,0,width,height);
            }
        });
        layeredPane.add(background,new Integer(0));

        /* Providing a scrollable view of a lightweight component */
        JScrollPane scrollPane = new JScrollPane(layeredPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        layeredPane.setPreferredSize(new Dimension(scrollWidth,scrollHeight));
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

    }

    private void createBoard() {
        Icon imgIcon = new ImageIcon(TILE_IMAGE);
        /* preparing game board and put the game board on the first layer */
        for ( int i = 0 ; i <Rows ; i++ ) {
            for ( int j = 0 ; j < Cols ; j++ ) {
                int isoX , isoY ;
                JLabel label;
                int cartX = j * TILE_HEIGHT ;
                int cartY = i * TILE_HEIGHT ;
                isoX = ( cartX - cartY );
                isoY = ( cartX + cartY ) / 2;
                label = new JLabel( imgIcon );
                label.setBounds( (isoX + faseleOfoghi) ,(isoY + faseleAmoodi) , TILE_WIDTH , TILE_HEIGHT ); /* (x , y , length , height ) */
                label.addMouseListener(new TileListener( i , j ));
                layeredPane.add(label,new Integer( 1 ));
            }
        }
    }

    private void createMenu() {
        /* These are about the game Designing section ( while the game is not started yet ! ) */
        JPanel menu= new JPanel();
        menu.setLayout(new BoxLayout( menu , BoxLayout.Y_AXIS ));
        menu.setBackground(new Color( 0 ,  48 ,  0 ));
        frame.getContentPane().add(menu,BorderLayout.EAST,1);

        int buttonsWidth = 58;
        int buttonsHeight = 60 ;

        /* Preparing images (GIFs)  */
        Icon starIcon = new ImageIcon(STAR_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight,Image.SCALE_DEFAULT)) ;
        Icon speedlimiter = new ImageIcon(SPEEDLIMITER_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight,Image.SCALE_DEFAULT)) ;
        Icon player1 = new ImageIcon(PLAYER1_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight+10,Image.SCALE_SMOOTH)) ;
        Icon player2 = new ImageIcon(PLAYER2_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight+10,Image.SCALE_SMOOTH)) ;
        Icon wall = new ImageIcon(WALL_IMAGE.getScaledInstance(buttonsWidth,buttonsHeight,Image.SCALE_SMOOTH)) ;

        /* Adding needed buttons to our menu ( our objects that should be put , on the game board )  */
        JButton wallButton = CreateButton("دیوار", wall ,"wall" );
        JButton starButton = CreateButton("ستاره" , starIcon ,"star" );
        JButton speedLimiterButton = CreateButton("سرعتگیر" , speedlimiter ,"speedlimiter" );
        JButton p1Button = CreateButton("بازیکن 1" , player1 ,"p1" );
        JButton p2Button = CreateButton("بازیکن 2", player2 ,"p2" );
        menu.add(wallButton);
        menu.add(starButton);
        menu.add(speedLimiterButton);
        menu.add(p1Button);
        menu.add(p2Button);
        JButton startButton = CreateButton("", new ImageIcon("src/graphic/image/startgame.gif") ,"");
        startButton.setEnabled(false);
        startButton.addActionListener(e -> {
            if (startButton.isEnabled())
                new GameGUI();
        });
        menu.add(startButton);
    }


    private JButton CreateButton( String nameFa , Icon icon , String nameEn ){
        JButton button;
        if ( icon == null ) {
            button = new JButton( nameFa );
        }
          else {
            button = new JButton( nameFa , icon );
        }
        //button.setSelected( true );
        button.setBackground( Color.WHITE );
        button.setSize(new Dimension(70 ,70 ));

        Border line = new LineBorder( Color.BLACK );
        Border margin = new EmptyBorder(2, 2, 2, 2);
        Border compound = new CompoundBorder( line , margin );
        button.setBorder(compound);
        button.setHorizontalTextPosition( SwingConstants.CENTER );
        button.setVerticalTextPosition( SwingConstants.TOP );

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component button = e.getComponent();
                if(button.isEnabled()) {
                    selected = nameEn;
                    Container menu = button.getParent();
                    for (Component component : menu.getComponents()) {
                        component.setBackground( Color.white );
                        component.setForeground( Color.black );
                    }
                    button.setBackground( Color.blue );
                    button.setForeground( Color.white ); /* Setting the text color */
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               JButton jButton = (JButton) e.getComponent();
               jButton.setText(nameFa);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (icon!=null) {
                    JButton jButton = (JButton) e.getComponent();
                    jButton.setText("");
                }
            }
        });
        return button;
    }

    public static void drawItem( int i , int j , String type ){
        drawItem( i , j , type , -1 );
    }


    public static void drawItem( int i , int j , String type , int limit ){
        int cartX = j * ( TILE_HEIGHT );
        int cartY = i * ( TILE_HEIGHT );
        int isoX = ( cartX - cartY );
        int isoY = ( cartX + cartY ) / 2;

        Image player1 = PLAYER1_IMAGE.getScaledInstance(36 ,50 , Image.SCALE_SMOOTH );
        Image player2 = PLAYER2_IMAGE.getScaledInstance(36 , 50 , Image.SCALE_SMOOTH );
        Image wall = WALL_IMAGE.getScaledInstance(TILE_WIDTH , TILE_HEIGHT + 8 , Image.SCALE_SMOOTH );
        Image star = STAR_IMAGE.getScaledInstance(30 , 30 , Image.SCALE_DEFAULT);
        Image speedlimiter = SPEEDLIMITER_IMAGE.getScaledInstance(60 , 68 , Image.SCALE_DEFAULT );

        JLabel item;
        Container container= frame.getContentPane();
        JPanel menu = (JPanel) container.getComponent(1);
        int layer; /* To put different objects on different layers!  */

        switch (type){
            case "star":
                item = new JLabel(new ImageIcon(star));
                item.setBounds((faseleOfoghi + isoX ),isoY - 13 + faseleAmoodi , TILE_WIDTH , TILE_HEIGHT);
                item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Star.decreaseCount();
                    }
                });
                /* Each type of object has its own layer ! */
                layer = 3;
                break;

            case "speedlimiter":
                item = new JLabel( new ImageIcon( speedlimiter ));
                item.setVerticalTextPosition( SwingConstants.TOP );
                item.setToolTipText(String.valueOf(limit));
                ToolTipManager.sharedInstance().setInitialDelay(50); /* The text displays when the cursor lingers over the component. */
                item.setBounds( (faseleOfoghi + isoX ) , isoY - 27 + faseleAmoodi , TILE_WIDTH , TILE_HEIGHT + 12 );
                layer = 4;
                break;

            case "p1":
                item = new JLabel(new ImageIcon(player1));
                item.setBounds(( faseleOfoghi + isoX ),isoY - 13 + faseleAmoodi , TILE_WIDTH , TILE_HEIGHT);
                item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component playerButton = menu.getComponent(3);
                        playerButton.setEnabled(true);
                    }
                });
                layer = 6;
                break;

            case "p2":
                item = new JLabel( new ImageIcon(player2) );
                item.setBounds(( faseleOfoghi + isoX ) , isoY - 13 + faseleAmoodi , TILE_WIDTH, TILE_HEIGHT );
                item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Component playerButton = menu.getComponent(4);
                        playerButton.setEnabled(true);

                    }
                });
                layer = 5;
                break;

            case "wall":
                item = new JLabel(new ImageIcon(wall));
                item.setBounds((faseleOfoghi +isoX ),isoY-8+ faseleAmoodi, TILE_WIDTH, TILE_HEIGHT+8);
                layer = 2;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /* If the user clicks on a object on the board , it will be removed */
                layeredPane.remove(e.getComponent());
                layeredPane.repaint();
                Game.getBoardInstance().setBoard(null,i,j);
                checkStartPermission(); //if  star or player is removed, start button will be disabled;
            }
        });
        int index = Cols-j-1;
        layeredPane.add(item, layer,index);
    }

    static void checkStartPermission(){
        Container container= frame.getContentPane();
        JPanel menu = (JPanel) container.getComponent(1);
        Component P1Button=menu.getComponent(3);
        Component P2Button=menu.getComponent(4);
        Component startButton=menu.getComponent(5);
        if(!P1Button.isEnabled() && !P2Button.isEnabled() && Star.getCount()>0){
            startButton.setEnabled(true);
        }else
            startButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new GameMainMenu();
    }
}

class TileListener implements MouseListener{
    private final int i;
    private final int j;

    public TileListener(int i, int j){
        this.i=i;
        this.j=j;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Board board = Game.getBoardInstance();
        Container container = GameCreator.frame.getContentPane();
        JPanel menu= (JPanel) container.getComponent(1);

        /* Setting the board's beads according to the place that the user clicked on ! */
        if( board.getBoardElement(i,j) == null ) {
            switch ( GameCreator.selected ) {
                case "wall":
                    GameCreator.drawItem( i , j , "wall" );
                    board.setBoard( new Wall( i , j ) , i , j );
                    break;

                case "star":
                    GameCreator.drawItem( i , j , "star" );
                    board.setBoard( new Star( i , j ) , i , j );
                    break;

                case "speedlimiter":
                    String input = JOptionPane.showInputDialog("مقدار محدودیت را وارد کنید");  /* Shows a question-message dialog requesting input from the user */
                    if (input != null && input.matches("[0-9]+")) {
                        int limit = Integer.parseInt(input);
                        if(limit>0) {
                            GameCreator.drawItem( i , j , "speedlimiter" , limit );
                            board.setBoard( new SpeedLimiter( i , j , limit) , i , j );
                        }
                    }else if(input!=null)
                        JOptionPane.showMessageDialog(null, "شما فقط مجاز به واردکردن اعداد مثبت هستید","ورودی نامعتبر!",JOptionPane.WARNING_MESSAGE);
                    break;

                case "p1":
                    GameCreator.drawItem( i , j , "p1" );
                    Player.getP1().setPointOfPlayer( i , j );
                    board.setBoard( Player.getP1() , i , j );
                    JButton button= (JButton) menu.getComponent(3);
                    button.setBackground( Color.white );
                    button.setEnabled( false );
                    GameCreator.selected = "" ;
                    break;

                case "p2":
                    GameCreator.drawItem( i , j , "p2" );
                    Player.getP2().setPointOfPlayer( i , j );
                    board.setBoard( Player.getP2() , i , j );
                    button= (JButton) menu.getComponent(4);
                    button.setBackground( Color.white);
                    button.setEnabled( false );
                    GameCreator.selected = "";
                    break;
            }
        }
        GameCreator.checkStartPermission();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Icon imgIcon = new ImageIcon( GameCreator.SElECTED_IMAGE );
        ((JLabel)e.getComponent()).setIcon( imgIcon );
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Icon imgIcon = new ImageIcon( GameCreator.TILE_IMAGE );
        ((JLabel)e.getComponent()).setIcon( imgIcon );
    }
}