package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;

public class Knight extends Figure{
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
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.knight(board[point.x][point.y], toPiece);
    }
}
