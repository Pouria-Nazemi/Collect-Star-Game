public class GameController {

    public boolean pathCheck(int xGoal, int yGoal , Player turn ) {
        if(Game.getBoardInstance().getBoardElement(xGoal, yGoal) != null) {
            if (Game.getBoardInstance().getBoardElement(xGoal, yGoal).getClass().getName().equals("Player")) {
                return false;
            }
        }
        if (turn.getPointOfPlayer().x < xGoal) {//down
            for (int i = turn.getPointOfPlayer().x + 1; i <= xGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null) {
                    if ("Wall".equals(Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        else {//up
            for (int i = turn.getPointOfPlayer().x - 1; i >= xGoal; i--) {//up
                if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null) {
                    if ("Wall".equals(Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        if (turn.getPointOfPlayer().y < yGoal) {//rast
            for (int i = turn.getPointOfPlayer().y+ 1; i <= yGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) != null) {
                    if ("Wall".equals(Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        if (turn.getPointOfPlayer().y > yGoal) {//chap
            for (int i = turn.getPointOfPlayer().y - 1; i >= yGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i) != null) {
                    if ("Wall".equals(Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean destinationValidator(int xGoal, int yGoal , Player turn) {
        if (turn.getPointOfPlayer() .x == xGoal) {
            return true;
        } else if (turn.getPointOfPlayer().y == yGoal) {
            return true;
        }
        else {
            return false;
        }

    }


    public void starCounter(int xGoal, int yGoal , Player turn ) {
        if (turn.getPointOfPlayer().x< xGoal) {
            if (destinationValidator(xGoal, yGoal  , turn  )) {
                for (int i = turn.getPointOfPlayer().x + 1; i <= xGoal; i++) {
                    if(Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null,i,turn.getPointOfPlayer().y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("SpeedLimiter")){
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
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("Star")){
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null,i,turn.getPointOfPlayer().y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPointOfPlayer().y).getClass().getName().equals("SpeedLimiter")){
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
                        if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName().equals("Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null, turn.getPointOfPlayer().x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i).getClass().getName().equals("SpeedLimiter")){
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
                        if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x, i).getClass().getName().equals("Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null, turn.getPointOfPlayer().x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPointOfPlayer().x,i).getClass().getName().equals("SpeedLimiter")){
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
    public boolean speedLimiterCheck(int xGoal, int yGoal , Player turn ){
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
