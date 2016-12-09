import com.tranzmate.exercise.IRectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by OrMan on 04/12/2016.
 */
public class Main {

    public static void main(String[] args) {

        MyRectangle borders = new MyRectangle(0, 0, 100, 100);

        List lnkLst = new LinkedList();
        lnkLst.add(new MyRectangle(3, 0, 10, 10));
        lnkLst.add(new MyRectangle(2, 4, 6, 6));
        lnkLst.add(new MyRectangle(3, 0, 5, 10));


        // create a store of rectangles
        MyRectangelesStore k1 = new MyRectangelesStore();
        k1.initialize(borders, lnkLst);

        int x = 10;
        int y = 10;

        IRectangle result = k1.findRectangleAt(x, y);

        if (result != null) {
            MyRectangle mostTop = (MyRectangle) result;

            System.out.println("The MyRectangle in (" + x + "," + y + ") is - " + mostTop.getProperties());
        } else
            System.out.println("There is no MyRectangle at (" + x + "," + y + ")");


        MyRectangle y3;
        for (int i = 0; i < 10; i++) {
            y3 = new MyRectangle(randInt(0, borders.getRight()),
                    randInt(0, borders.getTop()),
                    randInt(0, borders.getRight()),
                    randInt(0, borders.getTop()));
            lnkLst.add(y3);
        }
        k1.initialize(borders, lnkLst);

        IRectangle rec;
        int x1;
        int y1;
        for (int i = 0; i < 10; i++) {
            x1 = randInt(0, borders.getRight());
            y1 = randInt(0, borders.getTop());
            rec = k1.findRectangleAt(x1, y1);
            if (rec != null)
                System.out.println("(" + x1 + "," + y1 + ") " + rec);
            else
                System.out.println("(" + x1 + "," + y1 + ") there is nothing");

        }

        //printMat();
    }

    public static void printMat() {

    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
