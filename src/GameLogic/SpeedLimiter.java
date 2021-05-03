package GameLogic;

public class SpeedLimiter {
    private Coordinates point;
    private int limitingValue;

    public SpeedLimiter( int x , int y , int limitingValue ) {
        this.point = new Coordinates( x , y );
        this.limitingValue = limitingValue;  /* The limit of the speed limiter that will be given by the user */
    }

    public int getLimitingValue() {
        return limitingValue;
    }
}
