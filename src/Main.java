import com.tranzmate.exercise.IRectangle;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by OrMan on 04/12/2016.
 */
public class Main {

    public static void main(String[] args) {

        MyRectangle borders = new MyRectangle(0, 0, 10, 10);
        MyRectangelesStore store = new MyRectangelesStore();
        LinkedList lnkLst = new LinkedList();

        // create a store of rectangles
        lnkLst.add(new MyRectangle(3, 0, 10, 10)); // {Top=10, Left=3, Bottom=0, Right=10}
        lnkLst.add(new MyRectangle(2, 4, 6, 6)); // {Top=6, Left=2, Bottom=4, Right=6}
        lnkLst.add(new MyRectangle(3, 0, 5, 10)); // {Top=10, Left=3, Bottom=0, Right=5}

        fillStoreOneDot(lnkLst, 5, 5);
        //fillStoreRand(borders, lnkLst);

        // initialize the store with all the Rectangles in the list
        store.initialize(borders, lnkLst);

        printBorders(store);
        //printAllXY(store);
        printAllTheCollection(store);
    }

    private static void fillStoreRand(MyRectangle borders, LinkedList lnkLst) {
        MyRectangle y3;

        for (int i = 0; i < 10; i++) {
            y3 = new MyRectangle(randInt(0, borders.getRight()),
                    randInt(0, borders.getTop()),
                    randInt(0, borders.getRight()),
                    randInt(0, borders.getTop()));
            lnkLst.add(y3);
        }
    }

    private static void fillStoreOneDot(LinkedList lnkLst, int x, int y) {
        for (int i = 0; i < 88; i++) {
            lnkLst.add(new MyRectangle(x, y, x, y));
        }
    }

    private static void printBorders(MyRectangelesStore store) {
        MyRectangle item = store.getBounds();
        IRectangle rec;
        MyRectangle myRec;
        int index;

        for (int i = item.getLeft(); i <= item.getRight(); i++) {
            for (int j = item.getBottom(); j <= item.getTop(); j++) {
                rec = store.findRectangleAt(i, j);

                if (rec != null) {
                    myRec = (MyRectangle) rec;
                    index = myRec.getIndex();
                } else
                    index = 0;
                System.out.format("%3d", index);
            }
            System.out.println();
        }
    }

    private static void printAllXY(MyRectangelesStore store) {
        MyRectangle item = store.getBounds();
        IRectangle rec;
        MyRectangle myRec;
        int index;

        for (int i = item.getLeft(); i <= item.getRight(); i++) {
            for (int j = item.getBottom(); j <= item.getTop(); j++) {
                rec = store.findRectangleAt(i, j);
                if (rec != null) {
                    myRec = (MyRectangle) rec;
                    index = myRec.getIndex();
                } else
                    index = 0;

                if (index != 0) {
                    System.out.println("(" + i + "," + j + ") - " + index + " " +
                            rec.getProperties());
                } else
                    System.out.println("(" + i + "," + j + ") - NONE");
            }
        }
    }

    private static void printAllTheCollection(MyRectangelesStore store) {
        MyRectangle myRec;
        int index;

        for (IRectangle item : store.getRectangles()) {
            myRec = (MyRectangle) item;
            myRec.getProperties();
            System.out.println("Rec #" + myRec.getIndex() + " - " + myRec.getProperties());
        }
    }

    private static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}