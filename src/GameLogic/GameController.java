package GameLogic;

/* In this class , all the rules will be checked and everything will be controlled  */
public class GameController {

    private static int turn = 1;  // value 1 means turn of player 1 and value 2 means turn of player 2

    public static int getTurn() {
        return turn;
    }

    public static boolean gettingDestinationAndMove(int XGoal, int YGoal) {
       Player turn = whoseTurn();
       /* Check if these 3 rules are controlled ( TRUE ) and there is no problem to continue */
        if (destinationValidator(XGoal , YGoal , turn ) && pathCheck(XGoal , YGoal , turn) && speedLimiterCheck(XGoal , YGoal , turn )) {
            starAndSpeedLimiterCollector( XGoal , YGoal , turn );
            turn.Move( XGoal , YGoal );
            changeTurn(); /* If the move was valid and happend , the turn will change */
            return true;
        }
        else{
            return false;
        }
    }

    //changing turn
    public static void changeTurn() {
        if (turn==1) {
            turn = 2;
        } else if(turn==2) {
            turn = 1;
        }
    }

    /* whose turn is it ?! */
    public static Player whoseTurn(){
        if(turn==1){
            return Player.getP1();
        }
        else if(turn==2){
            return Player.getP2();
        }
        return null;
    }

    public static boolean pathCheck(int xGoal, int yGoal , Player turn ) {
        if(Game.getBoardInstance().getBoardElement(xGoal , yGoal) != null) {
            if (Game.getBoardInstance().getBoardElement(xGoal, yGoal) instanceof GameLogic.Player) {
                return false;
            }
        }
        if (turn.getPointOfPlayer().x < xGoal) {  /* Check up to down direction */
            for (int i = turn.getPointOfPlayer().x + 1 ; i <= xGoal ; i++) { /* Start checking the direction from where the player is , to the place he/she wanna go */
                if (Game.getBoardInstance().getBoardElement(i , turn.getPointOfPlayer().y) != null) {
                   if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) instanceof GameLogic.Wall) {
                        return false;
                    }
                }
            }
        }
        else { /* Check Down to up direction */
            for (int i = turn.getPointOfPlayer().x - 1 ; i >= xGoal ; i--) { /* Check Down to up direction */
                if (Game.getBoardInstance().getBoardElement(i , turn.getPointOfPlayer().y) != null) {
                    if ((Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) instanceof GameLogic.Wall)) {
                        return false;
                    }
                }
            }
        }
        if (turn.getPointOfPlayer().y < yGoal) { /* Check left to right direction */
            for (int i = turn.getPointOfPlayer().y+ 1 ; i <= yGoal ; i++) {
                if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x , i) != null) {
                    if ((Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) instanceof GameLogic.Wall)) {
                        return false;
                    }
                }
            }
        }
        if (turn.getPointOfPlayer().y > yGoal) { /* Check right to left direction */
            for (int i = turn.getPointOfPlayer().y - 1 ; i >= yGoal ; i--) {
                if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x , i) != null) {
                    if ((Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) instanceof GameLogic.Wall)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean destinationValidator(int xGoal , int yGoal , Player turn) {
        if (turn.getPointOfPlayer() .x == xGoal) {
            return true;
        }
        else if (turn.getPointOfPlayer().y == yGoal) {
            return true;
        }
        else {
            return false;
        }
    }

    /* How this method checks , is just like the previous part , but here we also count the stars and also pay attention to the SpeedLimiters in the different directions */
    public static void starAndSpeedLimiterCollector(int xGoal , int yGoal , Player turn ) {
        if (turn.getPointOfPlayer().x < xGoal) {
            if (destinationValidator(xGoal , yGoal  , turn  )) {
                for (int i = turn.getPointOfPlayer().x + 1 ; i <= xGoal ; i++) {
                    if(Game.getBoardInstance().getBoardElement(i , turn.getPointOfPlayer().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) instanceof GameLogic.Star) {
                            turn.addScore();  /*  means : startCount ++  */
                            Game.getBoardInstance().setBoardElement(null,i , turn.getPointOfPlayer().y);  /* When the player steals the star , that star will be deleted */
                            Star.decreaseCount();  /*  reduce One star from total stars that were available in the game */
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) instanceof GameLogic.SpeedLimiter){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y);
                            Game.getBoardInstance().setBoardElement(null,i,turn.getPointOfPlayer().y);
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
        else if ( turn.getPointOfPlayer().x > xGoal ) {  /* Just like the previous part , but in another directions! */
            if (destinationValidator(xGoal,yGoal , turn)){
                for (int i = turn.getPointOfPlayer().x - 1; i >= xGoal ; i--) {
                    if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) instanceof GameLogic.Star){
                            turn.addScore();
                            Game.getBoardInstance().setBoardElement(null,i,turn.getPointOfPlayer().y);
                            Star.decreaseCount();
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) instanceof GameLogic.SpeedLimiter){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y);
                            Game.getBoardInstance().setBoardElement(null,i,turn.getPointOfPlayer().y);
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
                        if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) instanceof GameLogic.Star) {
                            turn.addScore();
                            Game.getBoardInstance().setBoardElement(null, turn.getPointOfPlayer().x, i);
                            Star.decreaseCount();
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i) instanceof GameLogic.SpeedLimiter){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i);
                            Game.getBoardInstance().setBoardElement(null,turn.getPointOfPlayer().x,i);
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
                        if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) instanceof GameLogic.Star) {
                            turn.addScore();
                            Game.getBoardInstance().setBoardElement(null, turn.getPointOfPlayer().x, i);
                            Star.decreaseCount();
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i) instanceof GameLogic.SpeedLimiter){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i);
                            Game.getBoardInstance().setBoardElement(null,turn.getPointOfPlayer().x,i);
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
        if (width>=2 && width<=50  && height>=2 && height<=50)
             return true;
        else
            return false;
    }

    public static boolean speedLimiterCheck(int xGoal, int yGoal , Player turn ){
        if(turn.getLimit().size()!= 0) {
            int limit = turn.getLimit().get(0);
            if (xGoal == turn.getPointOfPlayer().x) {
                if(Math.abs( yGoal - turn.getPointOfPlayer().y ) <= limit){  /* The player should not move more the limit of the previous speed limiter */
                    return true;
                }
                else{
                    return false;
                }
            }
            else if ( yGoal == turn.getPointOfPlayer().y ) {
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
