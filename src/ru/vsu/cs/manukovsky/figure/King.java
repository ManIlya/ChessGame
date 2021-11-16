package ru.vsu.cs.manukovsky.figure;

import ru.vsu.cs.manukovsky.board.CheckMove;

import java.awt.*;

import static java.lang.Math.abs;

public class King extends Figure{
    public King(ColorFigure color, Figure[][] board, Point point) {
        super(color, board, point);
        doNotMove = true;
    }

    @Override
    public char paint() {
        if (color != ColorFigure.WHITE)
            return '♚';
        return '♔';
    }

    @Override
    protected boolean checkMove(Figure toPiece) {
        return CheckMove.king(board[point.x][point.y], toPiece, board);
    }

    @Override
    public void move(Figure toPiece) {
        if (checkMove(toPiece)){
            if(abs(toPiece.getPoint().y-point.y)>1){
                int x=point.x;
                int y1= toPiece.getPoint().y==2?0:7;
                int y2= toPiece.getPoint().y==2?3:5;
                board[x][y2]=board[x][y1];
                board[x][y1]=new Empty(board[x][y1].getPoint());
                board[x][y2].notMove();
                board[x][y2].setPoint(new Point(x, y2));
            }
            int x=toPiece.getPoint().x;
            int y= toPiece.getPoint().y;
            board[x][y]=board[point.x][point.y];
            board[point.x][point.y]=new Empty(point);
            point = new Point(x, y);
            doNotMove=false;

        }else {
            System.out.println("False");
        }
    }
}
