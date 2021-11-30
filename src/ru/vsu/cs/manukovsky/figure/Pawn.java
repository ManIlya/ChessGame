package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;
import java.io.File;

public class Pawn extends Figure {


    public Pawn(ColorFigure color, Figure[][] board, Point point) {
        super(color, board, point);
    }

    @Override
    public char paint() {
        if (color != ColorFigure.WHITE)
            return '♟';
        return '♙';
    }

    @Override
    public File getFile() {
        String str;
        if (color != ColorFigure.WHITE) {
            str = "bP.png";
        } else {
            str = "wP.png";
        }
        return new File(super.getFile(), str);
    }
/*@Override
    public boolean isDoNotMove() {
        return false;
    }*/

    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.pawn(board[point.x][point.y], toPiece, getBoard());
    }
}
