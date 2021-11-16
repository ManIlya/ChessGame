package ru.vsu.cs.manukovsky.figure;

import java.awt.*;

public abstract class Figure {
    protected ColorFigure color;
    protected Figure[][] board;
    protected Point point;
    protected boolean doNotMove;

    public Figure(ColorFigure color, Figure[][] board, Point point) {
        this.color = color;
        this.board = board;
        this.point = point;
    }

    public Figure(Point point) {
        this.point = point;
    }

    public ColorFigure getColor() {
        return color;
    }

    public void setColor(ColorFigure color) {
        this.color = color;
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void setBoard(Figure[][] board) {
        this.board = board;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    protected boolean checkMove(Figure toPiece){
        return false;
    }
    public void move(Figure toPiece){
        if(checkMove(toPiece)){
            int x=toPiece.getPoint().x;
            int y= toPiece.getPoint().y;
            board[x][y]=board[point.x][point.y];
            board[point.x][point.y]=new Empty(point);
            point = new Point(x, y);
            doNotMove= false;
        }else{
            System.out.println("False");
        }
    }
    public char paint(){
        if((point.x+point.y)%2==0){
            return '▬';
        }
        return'▭';
    }
    public  boolean isDoNotMove(){
        return doNotMove;
    }
    public void notMove(){
        doNotMove=false;
    }
}
