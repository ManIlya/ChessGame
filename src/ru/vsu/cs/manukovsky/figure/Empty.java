package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.figure.Figure;

import java.awt.*;

public class Empty extends Figure {

    public Empty(Point point) {
        super(point);
        color = ColorFigure.EMPTY;
    }
}
