package ru.vsu.cs.manukovsky.game;

import ru.vsu.cs.manukovsky.util.JTableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParamsDialog extends JDialog {
    private JPanel panelMain;
    private JSlider sliderCellSize;
    private JButton buttonCancel;
    private JButton buttonNewGame;
    private JButton buttonOk;


    private JTable gameFieldJTable;
    private ActionListener newGameAction;

    private int oldCellSize;


    public ParamsDialog(JTable gameFieldJTable, ActionListener newGameAction) {
        this.setTitle("Параметры");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();

        this.setResizable(false);

        this.gameFieldJTable = gameFieldJTable;
        this.newGameAction = newGameAction;

        this.oldCellSize = gameFieldJTable.getRowHeight();
        sliderCellSize.addChangeListener(e -> {
            int value = sliderCellSize.getValue();
            JTableUtils.resizeJTableCells(gameFieldJTable, value, value);
        });
        buttonCancel.addActionListener(e -> {
            JTableUtils.resizeJTableCells(gameFieldJTable, oldCellSize, oldCellSize);
            this.setVisible(false);
        });
        buttonNewGame.addActionListener(e -> {
            buttonOk.doClick();
            if (newGameAction != null) {
                newGameAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "newGame"));
            }
        });
        buttonOk.addActionListener(e -> {
            oldCellSize = gameFieldJTable.getRowHeight();
            this.setVisible(false);
        });
    }

    public void updateView() {
        sliderCellSize.setValue(gameFieldJTable.getRowHeight());
    }
}
