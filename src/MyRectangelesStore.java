import com.tranzmate.exercise.IRectangle;

import java.util.Collection;

/**
 * Created by OrMan on 04/12/2016.
 */

public class MyRectangelesStore implements com.tranzmate.exercise.IRectanglesStore {
    private MyRectangle bounds;
    private MyRectangle mostTop;
    private Collection<IRectangle> rectangles;
    private MyRectangelesStore[] arrFriends = new MyRectangelesStore[4]; // [left, top, bottom, right]

    @Override
    public void initialize(IRectangle bounds, Collection<IRectangle> rectangles) {
        this.bounds = (MyRectangle) bounds;
        this.rectangles = rectangles;
        MyRectangle tmpRec;
        boolean found = false;

        // Get the last relevant (in borders) Rectangle
        for (int i = rectangles.size() - 1; i >= 0 && !found; i--) {
            tmpRec = (MyRectangle) rectangles.toArray()[i];
            tmpRec.setIndex(i + 1);

            if (checkIfAHofefB(tmpRec, (MyRectangle) bounds)) {
                this.mostTop = tmpRec;
                found = true;
            }
        }

        // Check if there is one Rectangle (in borders)
        if (found) {
            if (!checkIfACoversB(this.mostTop, (MyRectangle) bounds)) {

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
        MyRectangle rec = null;

        if (this.mostTop != null) {
            if (isXYinRec(this.mostTop, x, y))
                rec = this.mostTop;
            else {
                if (x < this.mostTop.getLeft())
                    rec = (MyRectangle) this.arrFriends[0].findRectangleAt(x, y);
                else if (y > this.mostTop.getTop())
                    rec = (MyRectangle) this.arrFriends[1].findRectangleAt(x, y);
                else if (y < this.mostTop.getBottom())
                    rec = (MyRectangle) this.arrFriends[2].findRectangleAt(x, y);
                else if (x > this.mostTop.getRight())
                    rec = (MyRectangle) this.arrFriends[3].findRectangleAt(x, y);
            }
        }

        return rec;
    }

    public MyRectangle getBounds() {
        return this.bounds;
    }

    public Collection<IRectangle> getRectangles() {
        return this.rectangles;
    }

    private boolean isXYinRec(MyRectangle rec, int x, int y) {
        return (x >= rec.getLeft() && x <= rec.getRight()) &&
                (y >= rec.getBottom() && y <= rec.getTop());
    }

    private boolean checkIfAHofefB(MyRectangle recA, MyRectangle recB) {
        return checkIfAHasPinaB(recA, recB) || checkIfAHasPinaB(recB, recA);
    }

    private boolean checkIfAHasPinaB(MyRectangle recA, MyRectangle recB) {
        return isXYinRec(recA, recB.getLeft(), recB.getBottom()) ||
                isXYinRec(recA, recB.getLeft(), recB.getTop()) ||
                isXYinRec(recA, recB.getRight(), recB.getTop()) ||
                isXYinRec(recA, recB.getRight(), recB.getBottom());
    }

    private boolean checkIfACoversB(MyRectangle recA, MyRectangle recB) {
        return isXYinRec(recA, recB.getLeft(), recB.getBottom()) &&
                isXYinRec(recA, recB.getRight(), recB.getTop());
    }
}