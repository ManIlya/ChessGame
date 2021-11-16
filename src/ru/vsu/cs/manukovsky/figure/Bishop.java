package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;

public class Bishop extends Figure{
    public Bishop(ColorFigure color, Figure[][] board, Point point) {
        super(color, board, point);
    }

    @Override
    public char paint() {
        if (color != ColorFigure.WHITE)
            return '♝';
        return '♗';
    }

    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.diagonal(board[point.x][point.y], toPiece, board);
    }
}
