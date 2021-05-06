package GameLogic;

import java.awt.Point;

public class SpeedLimiter {

    private Point point;
    private int limitingValue;

    public SpeedLimiter( int x , int y , int limitingValue ) {
        this.point = new Point( x , y );
        this.limitingValue = limitingValue;  /* The limit of the speed limiter that will be given by the user */
    }

    public int getLimitingValue() {
        return limitingValue;
    }
}
