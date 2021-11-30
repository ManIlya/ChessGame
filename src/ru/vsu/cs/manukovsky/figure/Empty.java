package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.figure.Figure;

import java.awt.*;
import java.io.File;

public class Empty extends Figure {

    public Empty(Point point) {
        super(point);
        color = ColorFigure.EMPTY;
    }
    @Override
    public File getFile() {
        return new File( "D:/Morgoth/University/3_semester/OOP/ChessGame/chess24/E.png");
    }
}
