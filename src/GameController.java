public class GameController {

    public boolean pathCheck(int xGoal, int yGoal , Player turn ) {//amoodi
        if (turn.getPoint().x < xGoal) {//down
            for (int i = turn.getPoint().x + 1; i <= xGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y) != null) {
                    switch (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y).getClass().getName()) {
                        case "Wall":
                            return false;
                    }
                }
            }
        }
        else {//up
            for (int i = turn.getPoint().x - 1; i >= xGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y) != null) {
                    switch (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y).getClass().getName()) {
                        case "Wall":
                            return false;
                    }
                }
            }
        }
        if (turn.getPoint().y < yGoal) {//rast
            for (int i = turn.getPoint().y+ 1; i <= yGoal; i++) {
                if (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i) != null) {
                    switch (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i).getClass().getName()) {
                        case "Wall":
                            return false;
                    }
                }
            }
        }
        if (turn.getPoint().y > yGoal) {//chap
            for (int i = turn.getPoint().y - 1; i >= yGoal; i--) {
                if (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i) != null) {
                    switch (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i).getClass().getName()) {
                        case "Wall":
                            return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean destinationValidator(int xGoal, int yGoal , Player turn) {
        if (turn.getPoint() .x == xGoal) {
            //horizontalDirectionCheck(xGoal,yGoal);
            return true;
        } else if (turn.getPoint().y == yGoal) {
            //verticalDirectionCheck(xGoal,yGoal);
            return true;
        }
        else {
            return false;
        }

    }

    public void starCounter(int xGoal, int yGoal , Player turn ) {
        if (turn.getPoint().x< xGoal) {
            if (destinationValidator(xGoal, yGoal  , turn  )) {
                for (int i = turn.getPoint().x + 1; i <= xGoal; i++) {
                    if(Game.getBoardInstance().getBoardElement(i, turn.getPoint().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y).getClass().getName().equals("Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null,i,turn.getPoint().y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, turn.getPoint().y);
                            Game.getBoardInstance().setBoard(null,i,turn.getPoint().y);
                            if(turn == Player.getP1()){
                                Player.getP1().getLimit().add(sl.getLimitingValue());
                            }
                            else if(turn == Player.getP2()){
                                Player.getP1().getLimit().add(sl.getLimitingValue());
                            }
                        }
                    }
                }
            }
        }
        else if ( turn.getPoint().x > xGoal ) {
            if (destinationValidator(xGoal,yGoal , turn)){
                for (int i = turn.getPoint().x - 1 ; i >= xGoal ; i--) {
                    if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y) != null){
                        if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y).getClass().getName().equals("Star")){
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null,i,turn.getPoint().y);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(i, turn.getPoint().y).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(i, turn.getPoint().y);
                            Game.getBoardInstance().setBoard(null,i,turn.getPoint().y);
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
        }else if (  turn.getPoint().y < yGoal ){
            if( destinationValidator(xGoal,yGoal , turn) ){
                for (int i = turn.getPoint().y + 1; i <= yGoal; i++) {
                    if (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i) != null){
                        if (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i).getClass().getName().equals("Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null, turn.getPoint().x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPoint().x,i).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(turn.getPoint().x,i);
                            Game.getBoardInstance().setBoard(null,turn.getPoint().x,i);
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
        else if (turn.getPoint().y > yGoal){
            if( destinationValidator(xGoal,yGoal , turn) ){
                for (int i = turn.getPoint().y - 1; i >=  yGoal; i--) {
                    if (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i) != null){
                        if (Game.getBoardInstance().getBoardElement(turn.getPoint().x, i).getClass().getName().equals("Star")) {
                            turn.addScore();
                            Game.getBoardInstance().setBoard(null, turn.getPoint().x, i);
                            Star.count--;
                        }
                        else if (Game.getBoardInstance().getBoardElement(turn.getPoint().x,i).getClass().getName().equals("SpeedLimiter")){
                            SpeedLimiter sl = (SpeedLimiter) Game.getBoardInstance().getBoardElement(turn.getPoint().x,i);
                            Game.getBoardInstance().setBoard(null,turn.getPoint().x,i);
                            if(turn == Player.getP1()){
                                Player.getP2().getLimit().add(sl.getLimitingValue());
                            }
                            else if(turn == Player.getP2()){
                                Game.getPlayer().getPl1().getLimit().add(sl.getLimitingValue());
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
            if (xGoal == turn.getPoint().x) {
                if(Math.abs(yGoal - turn.getPoint().y) <= limit){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if (yGoal == turn.getPoint().y) {
                if(Math.abs(xGoal - turn.getPoint().x) <= limit){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    public void Move(int xGoal , int yGoal , Player turn) {
        if(turn.getLimit().size() != 0) {
            turn.getLimit().remove(0);
        }
        Game.getBoardInstance().setBoard(null,turn.getPoint().x,turn.getPoint().y);
        turn.setPointOfPlayer(xGoal,yGoal);
        Game.getBoardInstance().setBoard(this,xGoal,yGoal);
    }
}
