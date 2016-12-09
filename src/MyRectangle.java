import java.util.Properties;

/**
 * Created by OrMan on 04/12/2016.
 */
class MyRectangle implements com.tranzmate.exercise.IRectangle {

    private int x1, y1, x2, y2;

    public MyRectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public int getLeft() {
        return (x1 < x2) ? x1 : x2;
    }

    @Override
    public int getTop() {
        return (y1 > y2) ? y1 : y2;
    }

    @Override
    public int getRight() {
        return (x1 > x2) ? x1 : x2;
    }

    @Override
    public int getBottom() {
        return (y1 < y2) ? y1 : y2;
    }

    @Override
    public Properties getProperties() {
        Properties prop = new Properties();

        prop.setProperty("Left", String.valueOf(getLeft()));
        prop.setProperty("Top", String.valueOf(getTop()));
        prop.setProperty("Right", String.valueOf(getRight()));
        prop.setProperty("Bottom", String.valueOf(getBottom()));

        return prop;
    }

    public boolean isRectangleInXY(int x, int y) {
        return (x >= getLeft() && x <= getRight() && y >= getBottom() && y <= getTop());
    }

    @Override
    public String toString() {
        return
                "toString - (" + x1 +
                        "," + y1 +
                        ") (" + x2 +
                        "," + y2 +
                        ')';
    }
}