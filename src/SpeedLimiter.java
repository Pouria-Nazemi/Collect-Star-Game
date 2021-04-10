
public class SpeedLimiter {
    private Coordinates point;
    private int limitingValue;

    public SpeedLimiter(int x,int y , int limitingValue) {
        this.point = new Coordinates(x,y);
        this.limitingValue = limitingValue;
    }

    public int getLimitingValue() {
        return limitingValue;
    }
    
    
}
