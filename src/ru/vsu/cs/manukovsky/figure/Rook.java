package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;
import java.io.File;

public class Rook extends Figure{

    public Rook(ColorFigure color, Figure[][] board, Point point) {
        super(color, board, point);
        doNotMove = true;
    }

    @Override
    public char paint() {
        if (color != ColorFigure.WHITE)
            return '♜';
        return '♖';
    }
    @Override
    public File getFile() {
        String str;
        if (color != ColorFigure.WHITE) {
            str = "bR.png";
        } else {
            str = "wR.png";
        }
        return new File(super.getFile(), str);
    }

    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.vertical(board[point.x][point.y], toPiece, board)||CheckMove.horizontal(board[point.x][point.y], toPiece, board);
    }
}
