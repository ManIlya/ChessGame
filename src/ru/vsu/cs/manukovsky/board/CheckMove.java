package ru.vsu.cs.manukovsky.board;

import ru.vsu.cs.manukovsky.figure.ColorFigure;
import ru.vsu.cs.manukovsky.figure.Figure;

import java.util.LinkedList;

import static java.lang.Math.*;

public class CheckMove {

    public static boolean horizontal(Figure piece, Figure toPiece, Figure[][] board) {
        if(piece.getPoint().x==toPiece.getPoint().x){
            int j1 = piece.getPoint().y, j2 = toPiece.getPoint().y;
            for (int j = min(j1, j2) + 1; j < max(j1, j2); j++) {
                if (board[piece.getPoint().x][j].getColor() != ColorFigure.EMPTY) {
                    return false;
                }
            }
            return toPiece.getColor() != piece.getColor();
        }
        return false;
    }

    public static boolean vertical(Figure piece, Figure toPiece, Figure[][] board) {
        if(piece.getPoint().y==toPiece.getPoint().y){
            int i1 = piece.getPoint().x, i2 = piece.getPoint().x;//задаю конци
            for (int i = min(i1, i2) + 1; i < max(i1, i2); i++) {
                if (board[i][piece.getPoint().y].getColor() == piece.getColor()) {
                    return false;
                }
            }
            return toPiece.getColor() != piece.getColor();
        }
        return false;
    }

    public static boolean diagonal(Figure piece, Figure toPiece, Figure[][] board) {
        int i1 = piece.getPoint().x, i2 = toPiece.getPoint().x,
                j1 = piece.getPoint().y, j2 = toPiece.getPoint().y;
        if (toPiece.getPoint().x > piece.getPoint().x &&
                toPiece.getPoint().y > piece.getPoint().y) {//с верху вниз с права на лево
            for (int i = 1; i < i2 - i1; i++) {
                if (board[i1 + i][j1 + i].getColor() != ColorFigure.EMPTY) {
                    return false;
                }
            }
        } else if (toPiece.getPoint().x > piece.getPoint().x &&
                toPiece.getPoint().y < piece.getPoint().y) {//с низу вверх с права на лево
            for (int i = 1; i < i2 - i1; i++) {
                if (board[i1 + i][j1 - i].getColor() != ColorFigure.EMPTY) {
                    return false;
                }
            }
        } else if (toPiece.getPoint().x < piece.getPoint().x &&
                toPiece.getPoint().y > piece.getPoint().y) {//с верху вниз с лева на право
            for (int i = 1; i < i2 - i1; i++) {
                if (board[i1 - i][j1 + i].getColor() != ColorFigure.EMPTY) {
                    return false;
                }
            }
        } else {//с низу вверх  с лева на право
            for (int i = 1; i < i2 - i1; i++) {
                if (board[i1 - i][j1 - i].getColor() != ColorFigure.EMPTY) {
                    return false;
                }
            }
        }
        return toPiece.getColor()!=piece.getColor();
    }

    public static boolean knight(Figure piece, Figure toPiece) {
        return abs(toPiece.getPoint().x - piece.getPoint().x) + abs(toPiece.getPoint().y - piece.getPoint().y) == 3 &&
                toPiece.getPoint().x != piece.getPoint().x &&
                toPiece.getPoint().y != piece.getPoint().y &&
                toPiece.getColor() != piece.getColor();
    }

    public static boolean pawn(Figure piece, Figure toPiece) {
        int i = piece.getColor() == ColorFigure.WHITE ? 1 : -1;

        if (toPiece.getPoint().x - piece.getPoint().x == i) {
            if (abs(toPiece.getPoint().y - piece.getPoint().y) == 1 &&
                    toPiece.getColor() != piece.getColor() &&
                    toPiece.getColor() != ColorFigure.EMPTY) {
                return true;
            } else if (toPiece.getPoint().y == piece.getPoint().y &&
                    toPiece.getColor() == ColorFigure.EMPTY) {
                return true;
            }
        } else if (toPiece.getPoint().x - piece.getPoint().x == i * 2 &&
                (piece.getPoint().x == 1 || piece.getPoint().x == 6) &&
                toPiece.getColor() == ColorFigure.EMPTY) {
            return true;
        }
        return false;

    }

    public static boolean king(Figure piece, Figure toPiece, Figure[][] board) {
        if (abs((toPiece.getPoint().x - piece.getPoint().x)) <= 1 &&
                abs((toPiece.getPoint().y - piece.getPoint().y)) <= 1) {
            return piece.getColor()!=toPiece.getColor();
        } else if (toPiece.getPoint().x == piece.getPoint().x &&
                abs(piece.getPoint().y - toPiece.getPoint().y) == 2 &&piece.isDoNotMove()) {
            //рокеровка
            int i=toPiece.getPoint().x;
            int j=toPiece.getPoint().y;
            if (toPiece.getPoint().y == 6) {
                return toPiece.getColor()==ColorFigure.EMPTY &&
                        board[i][j - 1].getColor()==ColorFigure.EMPTY &&
                        board[i][j + 1].isDoNotMove();
            } else if (toPiece.getPoint().y == 2) {
                return toPiece.getColor()==ColorFigure.EMPTY &&
                        board[i][j + 1].getColor()==ColorFigure.EMPTY &&
                        board[i][j - 1].getColor()==ColorFigure.EMPTY &&
                        board[i][j - 2].isDoNotMove();
            }
        }
        return false;
    }

    //проверка на шаг


}
