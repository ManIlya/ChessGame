package ru.vsu.cs.manukovsky;

import ru.vsu.cs.manukovsky.draw.DrawPanel;
import ru.vsu.cs.manukovsky.figure.Empty;
import ru.vsu.cs.manukovsky.figure.Figure;
import ru.vsu.cs.manukovsky.game.ChessGame;
import ru.vsu.cs.manukovsky.game.GameState;
import ru.vsu.cs.manukovsky.util.DrawUtils;
import ru.vsu.cs.manukovsky.util.JTableUtils;
import ru.vsu.cs.manukovsky.util.SwingUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ChessFrame extends JFrame{
    private JButton button1;
    private JPanel panelMain;
    private JLabel labelMoveCount;
    private JPanel boardPanel;
    private JList list1;
    private JLabel labelStatus;

    private DrawPanel dp;


    private ChessGame game = new ChessGame();
    private static final int  DEFAULT_CELL_SIZE = 50;

    public ChessFrame() {
        this.setTitle("Шахматы");
        this.setContentPane(panelMain);
        game.newGame();
        dp = new DrawPanel(game, DEFAULT_CELL_SIZE, this);
        boardPanel.setLayout(new BorderLayout());
        boardPanel.add(dp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
    public void updateView(){
        labelMoveCount.setText(game.getTapCount()+" ");
        dp.repaint();
        if (game.getState() == GameState.MOVE_OF_BLACK){
            labelStatus.setForeground(Color.BLACK);
            labelStatus.setText("ход черных");
        }else if(game.getState() == GameState.MOVE_OF_WHITE){
            labelStatus.setForeground(Color.BLACK);
            labelStatus.setText("ход белых");
        }else {
            labelStatus.setText("");
            if (game.getState() == GameState.WIN_WHITE) {
                labelStatus.setForeground(Color.RED);
                labelStatus.setText("Победил белый (не забудь добавить нечью)");
            }else if (game.getState() == GameState.WIN_BLACK) {
                labelStatus.setForeground(Color.RED);
                labelStatus.setText("Победил черный (не забудь добавить нечью)");
            }
        }
    }


}
