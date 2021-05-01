package GameLogic;

public class GameController {

    private static boolean turn = true;

    public static boolean gettingDestinationAndMove(int XGoal,int YGoal) {
       Player turn = changeTurn();
        if (destinationValidator(XGoal, YGoal , turn ) && pathCheck(XGoal, YGoal , turn) && speedLimiterCheck(XGoal, YGoal , turn )) {
            starCounter(XGoal, YGoal , turn);
            turn.Move(XGoal, YGoal);
            return true;
        }
        else{
            return false;
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

    public static boolean pathCheck(int xGoal, int yGoal , Player turn ) {
        if(Game.getBoardInstance().getBoardElement(xGoal, yGoal) != null) {
            if (Game.getBoardInstance().getBoardElement(xGoal, yGoal).getClass().getName().equals("GameLogic.Player")) {
                return false;
            }
        }
        if (turn.getPointOfPlayer().x < xGoal) {//down
            for (int i = turn.getPointOfPlayer().x + 1; i <= xGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null) {
                    if ("GameLogic.Wall".equals(Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        else {//up
            for (int i = turn.getPointOfPlayer().x - 1; i >= xGoal; i--) {//up
                if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null) {
                    if ("GameLogic.Wall".equals(Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        if (turn.getPointOfPlayer().y < yGoal) {//rast
            for (int i = turn.getPointOfPlayer().y+ 1; i <= yGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) != null) {
                    if ("GameLogic.Wall".equals(Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        if (turn.getPointOfPlayer().y > yGoal) {//chap
            for (int i = turn.getPointOfPlayer().y - 1; i >= yGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) != null) {
                    if ("GameLogic.Wall".equals(Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean destinationValidator(int xGoal, int yGoal , Player turn) {
        if (turn.getPointOfPlayer() .x == xGoal) {
            return true;
        } else if (turn.getPointOfPlayer().y == yGoal) {
            return true;
        }
        else {
            return false;
        }

    }


    public static void starCounter(int xGoal, int yGoal , Player turn ) {
        if (turn.getPointOfPlayer().x< xGoal) {
            if (destinationValidator(xGoal, yGoal  , turn  )) {
                for (int i = turn.getPointOfPlayer().x + 1; i <= xGoal; i++) {
                    if(Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("GameLogic.Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null,i,turn.getPointOfPlayer().y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("GameLogic.SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y);
                            Game.getBoardInstance().setBoard(null,i,turn.getPointOfPlayer().y);
                            if(turn == Player.getP1()){
                                Player.getP2().getLimit().add(sl.getLimitingValue());
                            }
                            else if(turn == Player.getP2()){
                                Player.getP1().getLimit().add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
        else if ( turn.getPointOfPlayer().x > xGoal ) {
            if (destinationValidator(xGoal,yGoal , turn)){
                for (int i = turn.getPointOfPlayer().x - 1; i >= xGoal ; i--) {
                    if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("GameLogic.Star")){
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null,i,turn.getPointOfPlayer().y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("GameLogic.SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y);
                            Game.getBoardInstance().setBoard(null,i,turn.getPointOfPlayer().y);
                            if(turn == Player.getP1()){
                                Player.getP2().getLimit().add(sl.getLimitingValue());
                            }
                            else if(turn == Player.getP2()){
                                Player.getP1().getLimit().add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }else if (  turn.getPointOfPlayer().y < yGoal ){
            if( destinationValidator(xGoal,yGoal , turn) ){
                for (int i = turn.getPointOfPlayer().y + 1; i <= yGoal; i++) {
                    if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) != null){
                        if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName().equals("GameLogic.Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null, turn.getPointOfPlayer().x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i).getClass().getName().equals("GameLogic.SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i);
                            Game.getBoardInstance().setBoard(null,turn.getPointOfPlayer().x,i);
                            if(turn == Player.getP1()){
                                Player.getP2().getLimit().add(sl.getLimitingValue());
                            }
                            else if(turn == Player.getP2()){
                                Player.getP1().getLimit().add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
        else if (turn.getPointOfPlayer().y > yGoal){
            if( destinationValidator(xGoal,yGoal , turn) ){
                for (int i = turn.getPointOfPlayer().y - 1; i >=  yGoal; i--) {
                    if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) != null){
                        if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName().equals("GameLogic.Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null, turn.getPointOfPlayer().x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i).getClass().getName().equals("GameLogic.SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i);
                            Game.getBoardInstance().setBoard(null,turn.getPointOfPlayer().x,i);
                            if(turn == Player.getP1()){
                                Player.getP2().getLimit().add(sl.getLimitingValue());
                            }
                            else if(turn == Player.getP2()){
                                Player.getP1().getLimit().add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
    }
    public static boolean boardDimensionValidator(int width,int height){
        return width>=2 && height>=2 ;
    }
    public static boolean speedLimiterCheck(int xGoal, int yGoal , Player turn ){
        if(turn.getLimit().size()!= 0) {
            int limit = turn.getLimit().get(0);
            if (xGoal == turn.getPointOfPlayer().x) {
                if(Math.abs(yGoal - turn.getPointOfPlayer().y) <= limit){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if (yGoal == turn.getPointOfPlayer().y) {
                if(Math.abs(xGoal - turn.getPointOfPlayer().x) <= limit){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}
