package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;
import java.io.File;

public class Queen extends Figure {
    public Queen(ColorFigure color, Figure[][] board, Point point) {
        super(color, board, point);
    }
    @Override
    public char paint() {
        if (color != ColorFigure.WHITE)
            return '♛';
        return '♕';
    }
    @Override
    public File getFile() {
        String str;
        if (color != ColorFigure.WHITE) {
            str = "bQ.png";
        } else {
            str = "wQ.png";
        }
        return new File(super.getFile(), str);
    }
    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.horizontal(board[point.x][point.y], toPiece, board)||
                CheckMove.vertical(board[point.x][point.y], toPiece, board)||
                CheckMove.diagonal(board[point.x][point.y], toPiece, board);
    }

}
