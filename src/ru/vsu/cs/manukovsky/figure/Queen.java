package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;

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
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.horizontal(board[point.x][point.y], toPiece, board)||
                CheckMove.vertical(board[point.x][point.y], toPiece, board)||
                CheckMove.diagonal(board[point.x][point.y], toPiece, board);
    }

}
