import com.tranzmate.exercise.IRectangle;

import java.util.Collection;

/**
 * Created by OrMan on 04/12/2016.
 */

public class MyRectangelesStore implements com.tranzmate.exercise.IRectanglesStore {
    private MyRectangle bounds;
    private MyRectangle mostTop;
    private Collection<IRectangle> rectangles;
    //private ArrayList arrFriends = new ArrayList();
    private MyRectangelesStore[] arrFriends = new MyRectangelesStore[4];


    @Override
    public void initialize(IRectangle bounds, Collection<IRectangle> rectangles) {
        this.bounds = (MyRectangle) bounds;
        this.rectangles = rectangles;
        MyRectangle tmpRec;
        boolean found = false;

        //this.mostTop;
        for (int i = rectangles.size() - 1; i >= 0 && !found; i--) {
            tmpRec = (MyRectangle) rectangles.toArray()[i];
            if (isAHofefB(tmpRec, (MyRectangle) bounds)) {
                this.mostTop = tmpRec;
                found = true;
            }
        }

        if (found) {
            //System.out.println("this.mostTop - " + this.mostTop.getProperties());

            if (!isACoversB(this.mostTop, (MyRectangle) bounds)) {
                //System.out.println("need recursia here ....");

                // LEFT
                if (bounds.getLeft() < this.mostTop.getLeft()) {
                    MyRectangle rec = new MyRectangle(bounds.getLeft(),
                            bounds.getBottom(),
                            this.mostTop.getLeft() - 1,
                            bounds.getTop());
                    MyRectangelesStore myRecs = new MyRectangelesStore();
                    this.arrFriends[0] = myRecs;
                    myRecs.initialize(rec, rectangles);
                }

                // UP
                if (bounds.getTop() > this.mostTop.getTop()) {
                    MyRectangle rec = new MyRectangle(bounds.getLeft(),
                            this.mostTop.getTop() + 1,
                            bounds.getRight(),
                            bounds.getTop());
                    MyRectangelesStore myRecs = new MyRectangelesStore();
                    this.arrFriends[1] = myRecs;
                    myRecs.initialize(rec, rectangles);
                }

                // DOWN
                if (bounds.getBottom() < this.mostTop.getBottom()) {
                    MyRectangle rec = new MyRectangle(bounds.getLeft(),
                            this.mostTop.getBottom() - 1,
                            bounds.getRight(),
                            bounds.getBottom());
                    MyRectangelesStore myRecs = new MyRectangelesStore();

                    this.arrFriends[2] = myRecs;
                    myRecs.initialize(rec, rectangles);
                }

                // RIGHT
                if (this.mostTop.getRight() < bounds.getRight()) {
                    MyRectangle rec = new MyRectangle(this.mostTop.getRight() + 1,
                            bounds.getBottom(),
                            bounds.getRight(),
                            bounds.getTop());
                    MyRectangelesStore myRecs = new MyRectangelesStore();
                    this.arrFriends[3] = myRecs;
                    myRecs.initialize(rec, rectangles);
                }
            }
        }
    }

    @Override
    public IRectangle findRectangleAt(int x, int y) {
        MyRectangle r = null;

        if (this.mostTop != null) {
            if (isXYinRec(this.mostTop, x, y))
                r = this.mostTop;
            else {
                if (x < this.mostTop.getLeft())
                    r = (MyRectangle) this.arrFriends[0].findRectangleAt(x, y); // NEED TO FILL
                else if (x > this.mostTop.getRight())
                    r = (MyRectangle) this.arrFriends[3].findRectangleAt(x, y); // NEED TO FILL
                else if (y < this.mostTop.getBottom())
                    r = (MyRectangle) this.arrFriends[2].findRectangleAt(x, y); // NEED TO FILL
                else if (y > this.mostTop.getTop())
                    r = (MyRectangle) this.arrFriends[1].findRectangleAt(x, y); // NEED TO FILL
            }
        }

        return r;
    }

    public MyRectangle getBounds() {
        return bounds;
    }

    public Collection<IRectangle> getRectangles() {
        return rectangles;
    }

    private boolean isXYinRec(MyRectangle rec, int x, int y) {
        return (x >= rec.getLeft() && x <= rec.getRight()) &&
                (y >= rec.getBottom() && y <= rec.getTop());
    }

    private boolean isAHofefB(MyRectangle recA, MyRectangle recB) {
        return isAHasPinaB(recA, recB) || isAHasPinaB(recB, recA);
    }

    private boolean isAHasPinaB(MyRectangle recA, MyRectangle recB) {
        return isXYinRec(recA, recB.getLeft(), recB.getBottom()) ||
                isXYinRec(recA, recB.getLeft(), recB.getTop()) ||
                isXYinRec(recA, recB.getRight(), recB.getTop()) ||
                isXYinRec(recA, recB.getRight(), recB.getBottom());
    }

    private boolean isACoversB(MyRectangle recA, MyRectangle recB) {
        return isXYinRec(recA, recB.getLeft(), recB.getBottom()) &&
                isXYinRec(recA, recB.getRight(), recB.getTop());
    }
}