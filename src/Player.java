
public class Player {
    private static Player p1 = null;
    private static Player p2 = null;
    private int score = 0;
    private int[] limit;
    private boolean turn = true ; // bayad joori match beshe ke har harekati anjam shod tabe changeTurn farakhani beshe !
    private String direction ; //ino moghe har harekat bayad az karbar beporsim (to noskhe gheir graphiki ) !
    private Coordinates point;
    private Player() {

    }

    public void setDirection(String direction) {
        this.direction = direction;
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
    }

    public void changeTurn (){
        if (turn = turn ){ // true stands for p1 turn
            turn = false ;
        }
        else{ // false stands for p1 turn
            turn = true ;
        }
    }

    public void Move(int x, int y) {
        if (turn == true) {
            Game.getBoardInstance().getBoard()[p1.point.y][p1.point.x] = null ;
            Game.getBoardInstance().getBoard()[y][x] = p1 ;
        }
        else if ( turn == false) {
            Game.getBoardInstance().getBoard()[p2.point.y][p2.point.x] = null ;
            Game.getBoardInstance().getBoard()[y][x] = p2 ;
        }
    }

    public boolean basicValidMoveCheck (int x , int y){
        if ( Game.getBoardInstance().getBoardElement(x, y) == null || Game.getBoardInstance().getBoardElement(x, y).getClass().getName() == "Star"  ){
            return true ;
        }else {
            return false ;
        }
    }

    public boolean verticalDirectionCheck(int x , int y) {
        if (turn) {
            if (p1.point.y < y) {
                for (int i = p1.point.y; i < y; i++) {
                    if (Game.getBoardInstance().getBoard()[i][p1.point.x] != null) {
                        switch (Game.getBoardInstance().getBoard()[i][p1.point.x].getClass().getName()) {
                            case "Wall":
                                return false;
                            case "Player":
                                if (Game.getBoardInstance().getBoard()[i][p1.point.x] == p2) {
                                    return false ;
                                }
                            }
                        }
                     }
                }
            else{
                for (int i = p1.point.y; i > y; i--) {
                    if (Game.getBoardInstance().getBoard()[i][p1.point.x] != null) {
                        switch (Game.getBoardInstance().getBoard()[i][p1.point.x].getClass().getName()) {
                            case "Wall":
                                return false;
                            case "Player":
                                if (Game.getBoardInstance().getBoard()[i][p1.point.x] == p2) {
                                    return false ;
                                }
                        }
                    }
                }
            }
            }
        else if (!turn) {
            if (p2.point.y < y) {
                for (int i = p2.point.y; i < y; i++) {
                    if (Game.getBoardInstance().getBoard()[i][p2.point.x] != null) {
                        switch (Game.getBoardInstance().getBoard()[i][p2.point.x].getClass().getName()) {
                            case "Wall":
                                return false;
                            case "Player":
                                if (Game.getBoardInstance().getBoard()[i][p2.point.x] == p2) {
                                    return false ;
                                }
                        }
                    }
                }
            }
            else{
                for (int i = p2.point.y; i > y; i--) {
                    if (Game.getBoardInstance().getBoard()[i][p2.point.x] != null) {
                        switch (Game.getBoardInstance().getBoard()[i][p2.point.x].getClass().getName()) {
                            case "Wall":
                                return false;
                            case "Player":
                                if (Game.getBoardInstance().getBoard()[i][p2.point.x] == p2) {
                                    return false ;
                                }
                        }
                    }
                }
            }
        }
        return true ;
    }

    public boolean horizontalDirectionCheck(int x , int y) {
        if (turn) {
            if (p1.point.x < x) {
                for (int i = p1.point.x; i < x; i++) {
                    if (Game.getBoardInstance().getBoard()[p1.point.y][i] != null) {
                        switch (Game.getBoardInstance().getBoard()[p1.point.y][i].getClass().getName()) {
                            case "Wall":
                                return false;
                            case "Player":
                                if (Game.getBoardInstance().getBoard()[p1.point.y][i] == p2) {
                                    return false ;
                                }
                        }
                    }
                }
            }
        }
        else if (!turn) {
            if (p2.point.y < y) {
                for (int i = p2.point.x; i < x; i++) {
                    if (Game.getBoardInstance().getBoard()[p2.point.x][i] != null) {
                        switch (Game.getBoardInstance().getBoard()[p2.point.y][i].getClass().getName()) {
                            case "Wall":
                                return false;
                            case "Player":
                                if (Game.getBoardInstance().getBoard()[p2.point.y][i] == p2) {
                                    return false ;
                                }
                        }
                    }
                }
            }
        }
        return true ;
    }

    public void chooseDirection(String direction , int x , int y ){
        if (direction.equals("Horizontal")){
            horizontalDirectionCheck(x,y);
        }else if (direction.equals("Vertical")){
            verticalDirectionCheck(x,y);
        }
    }

    // ghabl az farakhani in tabe bayad do se ta tabe ghabl "true" bashan ta badesh in ejra she .
    public void starCounter(int x , int y){
      // har ja resid be setare oon harekat mad nazar ro bokone
    }

}
