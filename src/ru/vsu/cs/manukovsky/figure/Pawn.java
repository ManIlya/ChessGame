package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;

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

    /*@Override
    public boolean isDoNotMove() {
        return false;
    }*/

    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.pawn(board[point.x][point.y], toPiece);
    }
}
