package ru.vsu.cs.manukovsky.board;

import ru.vsu.cs.manukovsky.figure.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private Figure[][] board;
    public static final int size = 8;
    private List<Figure> listWhite;
    private List<Figure> listBlack;
    //*private Map <Class<? extends Figure>, Figure> map = new HashMap<>();
    //private Map<String, Integer> map1 = new HashMap<>();

    public ChessBoard() {
        board = new Figure[size][size];
        for (int i = 0; i < size; i++) {//создаем и заполняем доску пустыми клетками
            for (int j = 0; j < size; j++) {
                board[i][j] = new Empty(new Point(i, j));
            }
        }
    }

    public void newBoard() {
        int i1 = 1, i2 = 6;
        for (int j = 0; j < size; j++) {
            board[i1][j] = new Pawn(ColorFigure.WHITE, board, new Point(i1, j));//белые пешки
            //map.put(board[i1][j].getClass(), board[i1][j]);
            board[i2][j] = new Pawn(ColorFigure.BLACK, board, new Point(i2, j));//черные пешки
        }
        board[0][4] = new King(ColorFigure.WHITE, board, new Point(0, 4));
        board[7][4] = new King(ColorFigure.BLACK, board, new Point(7, 4));

        board[0][0] = new Rook(ColorFigure.WHITE, board, new Point(0, 0));
        board[0][1] = new Knight(ColorFigure.WHITE, board, new Point(0, 1));
        board[0][2] = new Bishop(ColorFigure.WHITE, board, new Point(0, 2));
        board[0][3] = new Queen(ColorFigure.WHITE, board, new Point(0, 3));
        board[0][5] = new Bishop(ColorFigure.WHITE, board, new Point(0, 5));
        board[0][6] = new Knight(ColorFigure.WHITE, board, new Point(0, 6));
        board[0][7] = new Rook(ColorFigure.WHITE, board, new Point(0, 7));

        board[7][0] = new Rook(ColorFigure.BLACK, board, new Point(7, 0));
        board[7][1] = new Knight(ColorFigure.BLACK, board, new Point(7, 1));
        board[7][2] = new Bishop(ColorFigure.BLACK, board, new Point(7, 2));
        board[7][3] = new Queen(ColorFigure.BLACK, board, new Point(7, 3));
        board[7][5] = new Bishop(ColorFigure.BLACK, board, new Point(7, 5));
        board[7][6] = new Knight(ColorFigure.BLACK, board, new Point(7, 6));
        board[7][7] = new Rook(ColorFigure.BLACK, board, new Point(7, 7));

    }

    public void paint() {
        System.out.println("―――――――――");
        for (int i = size - 1; i >= 0; i--) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].paint());
            }
            System.out.println("|");
        }
        System.out.println("―――――――――");
    }

    public boolean move(int raw1, int col1, int raw2, int col2) {
        return board[raw1][col1].move(board[raw2][col2]);
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void setBoard(Figure[][] board) {
        this.board = board;
    }

}
