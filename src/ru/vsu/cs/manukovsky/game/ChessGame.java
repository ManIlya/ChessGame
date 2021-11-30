package ru.vsu.cs.manukovsky.game;

import ru.vsu.cs.manukovsky.board.ChessBoard;
import ru.vsu.cs.manukovsky.figure.ColorFigure;
import ru.vsu.cs.manukovsky.figure.Figure;

import java.awt.*;
import java.util.ArrayList;

public class ChessGame {
    private GameState state = GameState.NOT_STARTED;
    private int tapCount = 0;
    private ChessBoard chessBoard;
    private Point LMS = null;
    private ArrayList<Object> listBoard;
    private Figure[][] board;
    private int size;

    public ChessGame() {
        chessBoard = new ChessBoard();
        listBoard = new ArrayList<>();
    }

    public void newGame() {
        chessBoard.newBoard();
        board = chessBoard.getBoard();
        size = ChessBoard.size;
        state = GameState.MOVE_OF_WHITE;

    }

    public boolean leftMouseClickCheck(int row, int col) {
        if (row < 0 || row > size ||
                col < 0 || col > size) {//если клик не на доске
            return false;
        }
        if (!(state == GameState.MOVE_OF_WHITE || state == GameState.MOVE_OF_BLACK)) {
            return false;
        }
        return true;
    }

    public boolean leftMouseClick1(int row, int col) {
        if (board[row][col].getColor() == ColorFigure.EMPTY) {
            return false;
        }
        if (board[row][col].getColor() == ColorFigure.WHITE && state == GameState.MOVE_OF_WHITE ||
                board[row][col].getColor() == ColorFigure.BLACK && state == GameState.MOVE_OF_BLACK) {
             LMS = new Point(row, col);
            return true;
            //сделать возможность показа всех возможных ходов
        }
        return false;
    }

    public boolean leftMouseClick2(int row, int col) {
        if (((board[row][col].getColor() == ColorFigure.WHITE && state == GameState.MOVE_OF_BLACK) ||
                (board[row][col].getColor() == ColorFigure.BLACK && state == GameState.MOVE_OF_WHITE)||
                (board[row][col].getColor() == ColorFigure.EMPTY)) &&
                chessBoard.move(LMS.x, LMS.y, row, col)) {
            listBoard.add(board.clone());
            board = chessBoard.getBoard();
            if(state == GameState.MOVE_OF_BLACK){
                tapCount++;
                state = GameState.MOVE_OF_WHITE;
            }else{
                state = GameState.MOVE_OF_BLACK;
            }
            LMS = null;
            return true;
            //сделать возможность показа всех возможных ходов
        }
        return false;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getTapCount() {
        return tapCount;
    }

    public void setTapCount(int tapCount) {
        this.tapCount = tapCount;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Point getLMS() {
        return LMS;
    }

    public void setLMS(Point LMS) {
        this.LMS = LMS;
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void setBoard(Figure[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void resetTapCount() {
        tapCount=0;
    }

}
