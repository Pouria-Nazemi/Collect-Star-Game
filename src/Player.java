import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static Player p1 = null;
    private static Player p2 = null;
    private int score = 0;
    private ArrayList <Integer> limit = new ArrayList<>();
    private static boolean turn = true; // bayad joori match beshe ke har harekati anjam shod tabe changeTurn farakhani beshe !
    private Coordinates point;

    public ArrayList<Integer> getLimit() {
        return limit;
    }

    public Coordinates getPoint() {
        return point;
    }

    public Player getPl1(){
        return p1 ;
    }

    public Player getPl2(){
        return p2;
    }

    private Player() {

    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score++;
    }


    public void setPointOfPlayer(int x, int y) {
        this.point = new Coordinates(x, y);
    }

    public static Player getP1() {
        if (p1 == null)
            p1 = new Player();
        return p1;
    }

    public static Player getP2() {
        if (p2 == null)
            p2 = new Player();
        return p2;
    }

    public void updateBoard() {//test
        //this.limit.add(5);
        //Game.getBoardInstance().setBoard(new Wall(3,1),3,1);
        //Game.getBoardInstance().setBoard(null,3,1);
        //System.out.println(getP1().horizontalDirectionCheck(1,2));
    }

    public static void gettingDestinationAndMove() {
        Scanner input = new Scanner(System.in);
        GameController gameController = new GameController();
        Player turn = changeTurn();
        String playerTurn = turn == p1 ? "PLAYER 1 " : "PLAYER 2";
        System.out.println(playerTurn + " Enter Destination: ");
        System.out.print("X: ");
        int XGoal = input.nextInt() - 1;
        System.out.print("\nY: ");
        int YGoal = input.nextInt() - 1;
        if (gameController.destinationValidator(XGoal, YGoal , turn ) && gameController.pathCheck(XGoal, YGoal , turn) && gameController.speedLimiterCheck(XGoal, YGoal , turn )) {
                gameController.starCounter(XGoal, YGoal , turn);
                gameController.Move(XGoal, YGoal , turn);
                Game.getBoardInstance().showBoard();
        }
        else{
            System.out.println("false Destination, Enter correct Destination: ");
            do {
                System.out.println(playerTurn);
                System.out.print("X: ");
                XGoal = input.nextInt() - 1;
                System.out.print("\nY: ");
                YGoal = input.nextInt() - 1;
            }while(!(gameController.destinationValidator(XGoal, YGoal,turn) && gameController.pathCheck(XGoal, YGoal , turn) && gameController.speedLimiterCheck(XGoal, YGoal , turn)));
            gameController.starCounter(XGoal, YGoal , turn);
            gameController.Move(XGoal, YGoal , turn);
            Game.getBoardInstance().showBoard();
        }
    }
    public static Player changeTurn() {
        if (turn) { // true stands for p1 turn
            turn = false;
            return Player.getP1();
        } else { // false stands for p2 turn
            turn = true;
            return Player.getP2();
        }
    }

    // ghabl az farakhani in tabe bayad do se ta tabe ghabl "true" bashan ta badesh in ejra she .

}
