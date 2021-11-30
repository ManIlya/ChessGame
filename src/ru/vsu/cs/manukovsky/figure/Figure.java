package ru.vsu.cs.manukovsky.figure;

import java.awt.*;
import java.io.File;

public abstract class Figure {
    protected ColorFigure color;
    protected Figure[][] board;
    protected Point point;
    protected boolean doNotMove;
    protected File file;

    public Figure(ColorFigure color, Figure[][] board, Point point) {
        this.color = color;
        this.board = board;
        this.point = point;
        file = new File("D:/Morgoth/University/3_semester/OOP/ChessGame/chess24/");
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

    public File getFile() {
        return file;
    }

    protected boolean checkMove(Figure toPiece){
        return false;
    }
    public boolean move(Figure toPiece){
        if(checkMove(toPiece)){
            int x=toPiece.getPoint().x;
            int y= toPiece.getPoint().y;
            board[x][y]=board[point.x][point.y];
            board[point.x][point.y]=new Empty(point);
            point = new Point(x, y);
            doNotMove= false;
            return true;
        }else{
            System.out.println("False");
            return false;
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
