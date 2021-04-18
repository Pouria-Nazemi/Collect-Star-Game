import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static Player p1 = null;
    private static Player p2 = null;
    private int score = 0;
    private ArrayList limit = new ArrayList();
    private static boolean turn = true; // bayad joori match beshe ke har harekati anjam shod tabe changeTurn farakhani beshe !
    private Coordinates point;

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
        //Game.getBoardInstance().setBoard(new Wall(3,1),3,1);
        //Game.getBoardInstance().setBoard(null,3,1);
        //System.out.println(getP1().horizontalDirectionCheck(1,2));
    }

    public static void gettingDestinationAndMove() {
        Scanner input = new Scanner(System.in);
        Player turn = changeTurn();
        String playerTurn = turn == p1 ? "PLAYER 1 " : "PLAYER 2";
        System.out.println(playerTurn + " Enter Destination: ");
        System.out.print("X: ");
        int XGoal = input.nextInt() - 1;
        System.out.print("\nY: ");
        int YGoal = input.nextInt() - 1;
        if (turn.destinationValidator(XGoal, YGoal) && turn.pathCheck(XGoal, YGoal)) {
                turn.starCounter(XGoal, YGoal);
                turn.Move(XGoal, YGoal);
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
            }while(!turn.destinationValidator(XGoal, YGoal) && turn.pathCheck(XGoal, YGoal));
            turn.starCounter(XGoal, YGoal);
            turn.Move(XGoal, YGoal);
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
    public void Move(int xGoal , int yGoal) {
        Game.getBoardInstance().setBoard(null,this.point.x,this.point.y);
        this.setPointOfPlayer(xGoal,yGoal);
        Game.getBoardInstance().setBoard(this,xGoal,yGoal);
    }

   /* public boolean basicValidMoveCheck(int x, int y) {
        if (Game.getBoardInstance().getBoardElement(x, y) == null || Game.getBoardInstance().getBoardElement(x, y).getClass().getName() == "Star") {
            return true;
        }
        else {
            return false;
        }
    }*/

    public boolean pathCheck(int xGoal, int yGoal) {//amoodi
        if (this.point.x < xGoal) {//down
            for (int i = this.point.x + 1; i <= xGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(i, this.point.y) != null) {
                    switch (Game.getBoardInstance().getBoardElement(i, this.point.y).getClass().getName()) {
                        case "Wall":
                            return false;
                        case "Player":
                            if (Game.getBoardInstance().getBoardElement(i, this.point.y) == p2) {
                                return xGoal > i; //barrasi rad shodan az player digar
                            }
                    }
                }
            }
        }
        else {//up
            for (int i = this.point.x - 1; i >= xGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(i, this.point.y) != null) {
                    switch (Game.getBoardInstance().getBoardElement(i, this.point.y).getClass().getName()) {
                        case "Wall":
                            return false;
                        case "Player":
                            if (Game.getBoardInstance().getBoardElement(i, this.point.y) == p2) {
                                return xGoal < i; //barrasi rad shodan az player digar
                            }
                    }
                }
            }
        }
        if (this.point.y < yGoal) {//rast
            for (int i = this.point.y + 1; i <= yGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(this.point.x, i) != null) {
                    switch (Game.getBoardInstance().getBoardElement(this.point.x, i).getClass().getName()) {
                        case "Wall":
                            return false;
                        case "Player":
                            if (Game.getBoardInstance().getBoardElement(this.point.x, i) == p2) {
                                return yGoal > i; //barrasi rad shodan az player digar
                            }
                    }
                }
            }
        }
        if (this.point.y > yGoal) {//chap
            for (int i = this.point.y - 1; i >= yGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(this.point.x, i) != null) {
                    switch (Game.getBoardInstance().getBoardElement(this.point.x, i).getClass().getName()) {
                        case "Wall":
                            return false;
                        case "Player":
                            if (Game.getBoardInstance().getBoardElement(this.point.x, i) == p2) {
                                return yGoal < i; //barrasi rad shodan az player digar
                            }
                    }
                }
            }
        }
        return true;
    }

    /*public boolean horizontalDirectionCheck(int xGoal, int yGoal) {//ofoghi
        if (this.point.y < yGoal) {//rast
            for (int i = this.point.y + 1; i <= yGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(this.point.x, i) != null) {
                    switch (Game.getBoardInstance().getBoardElement(this.point.x, i).getClass().getName()) {
                        case "Wall":
                            return false;
                        case "Player":
                            if (Game.getBoardInstance().getBoardElement(this.point.x, i) == p2) {
                                return yGoal > i; //barrasi rad shodan az player digar
                            }
                    }
                }
            }
        }
        if (this.point.y > yGoal) {//chap
            for (int i = this.point.y - 1; i >= yGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(this.point.x, i) != null) {
                    switch (Game.getBoardInstance().getBoardElement(this.point.x, i).getClass().getName()) {
                        case "Wall":
                            return false;
                        case "Player":
                            if (Game.getBoardInstance().getBoardElement(this.point.x, i) == p2) {
                                return yGoal < i; //barrasi rad shodan az player digar
                            }
                    }
                }
            }
        }
        return true;
    }*/

    public boolean destinationValidator(int xGoal, int yGoal) {
        if (this.point.x == xGoal) {
            //horizontalDirectionCheck(xGoal,yGoal);
            return true;
        } else if (this.point.y == yGoal) {
            //verticalDirectionCheck(xGoal,yGoal);
            return true;
        }
        else {
            return false;
        }

    }

    // ghabl az farakhani in tabe bayad do se ta tabe ghabl "true" bashan ta badesh in ejra she .


    public void starCounter(int xGoal, int yGoal) {
        if (this.point.x < xGoal) {
            if (destinationValidator(xGoal, yGoal)) {
                for (int i = this.point.x + 1; i <= xGoal; i++) {
                    if(Game.getBoardInstance().getBoardElement(i, this.point.y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, this.point.y).getClass().getName().equals("Star")) {
                            this.addScore();
                            Game.getBoardInstance().setBoard(null,i,this.point.y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, this.point.y).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, this.point.y);
                            Game.getBoardInstance().setBoard(null,i,this.point.y);
                            if(this == p1){
                                p2.limit.add(sl.getLimitingValue());
                            }
                            else if(this == p2){
                                p1.limit.add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
        else if ( this.point.x > xGoal ) {
            if (destinationValidator(xGoal,yGoal)){
                for (int i = this.point.x - 1 ; i >= xGoal ; i--) {
                    if (Game.getBoardInstance().getBoardElement(i, this.point.y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, this.point.y).getClass().getName().equals("Star")){
                            this.addScore();
                            Game.getBoardInstance().setBoard(null,i,this.point.y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, this.point.y).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, this.point.y);
                            Game.getBoardInstance().setBoard(null,i,this.point.y);
                            if(this == p1){
                                p2.limit.add(sl.getLimitingValue());
                            }
                            else if(this == p2){
                                p1.limit.add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }else if (  this.point.y < yGoal ){
            if( destinationValidator(xGoal,yGoal) ){
                for (int i = this.point.y + 1; i <= yGoal; i++) {
                    if (Game.getBoardInstance().getBoardElement(this.point.x, i) != null){
                        if (Game.getBoardInstance().getBoardElement(this.point.x, i).getClass().getName().equals("Star")) {
                            this.addScore();
                            Game.getBoardInstance().setBoard(null, this.point.x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(this.point.x,i).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(this.point.x,i);
                            Game.getBoardInstance().setBoard(null,this.point.x,i);
                            if(this == p1){
                                p2.limit.add(sl.getLimitingValue());
                            }
                            else if(this == p2){
                                p1.limit.add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
        else if (this.point.y > yGoal){
            if( destinationValidator(xGoal,yGoal) ){
                for (int i = this.point.y - 1; i >=  yGoal; i--) {
                    if (Game.getBoardInstance().getBoardElement(this.point.x, i) != null){
                        if (Game.getBoardInstance().getBoardElement(this.point.x, i).getClass().getName().equals("Star")) {
                            this.addScore();
                            Game.getBoardInstance().setBoard(null, this.point.x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(this.point.x,i).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(this.point.x,i);
                            Game.getBoardInstance().setBoard(null,this.point.x,i);
                            if(this == p1){
                                p2.limit.add(sl.getLimitingValue());
                            }
                            else if(this == p2){
                                p1.limit.add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
    }
}
