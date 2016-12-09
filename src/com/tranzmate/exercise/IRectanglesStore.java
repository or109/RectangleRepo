package com.tranzmate.exercise;

import java.util.Collection;

public interface IRectanglesStore {
    void initialize(IRectangle bounds, Collection<IRectangle> rectangles);
    IRectangle findRectangleAt(int x, int y);
}
