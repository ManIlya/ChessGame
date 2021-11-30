package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;
import java.io.File;

public class Knight extends Figure {
    public Knight(ColorFigure color, Figure[][] board, Point point) {
        super(color, board, point);
    }

    @Override
    public char paint() {
        if (color != ColorFigure.WHITE)
            return '♞';
        return '♘';
    }

    @Override
    public File getFile() {
        String str;
        if (color != ColorFigure.WHITE) {
            str = "bN.png";
        } else {
            str = "wN.png";
        }
        return new File(super.getFile(), str);
    }

    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.knight(board[point.x][point.y], toPiece);
    }
}
