package ru.vsu.cs.manukovsky.draw;

import ru.vsu.cs.manukovsky.ChessFrame;
import ru.vsu.cs.manukovsky.board.ChessBoard;
import ru.vsu.cs.manukovsky.figure.Empty;
import ru.vsu.cs.manukovsky.figure.Figure;
import ru.vsu.cs.manukovsky.game.ChessGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Observer;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    private Figure[][] board;
    private int step;
    private Point first, second;
    private ChessGame game;
    private ChessFrame cf;

    public DrawPanel(ChessGame game, int step, ChessFrame cf) {
        super();
        this.game = game;
        this.board = game.getBoard();
        this.step = step;
        this.cf = cf;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setSize(step * board.length, step * board.length);
    }


    @Override
    public void paint(Graphics gr) {
        int w = getWidth();
        int h = getHeight();
        super.paint(gr);
        Graphics2D g = (Graphics2D) gr;
        super.paint(g);
        drawBoard(g);
        activate(g);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                paintImage(g, i, j);
            }
        }
    }

    private void paintImage(Graphics2D g, int i, int j) {
        File file = board[i][j].getFile();
        BufferedImage bImg = null;
        try {
            bImg = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Ошибка расположения файла");
        }
        Image img = bImg.getScaledInstance(step, step, Image.SCALE_DEFAULT);
        g.drawImage(img, j * step, (board.length - i - 1) * step, this);
    }

    private void drawBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, step * board.length, step * board.length);
        g.setColor(Color.WHITE);
        for (int raw = 0; raw < board.length; raw++) {
            for (int cal = 0; cal < board.length; cal++) {
                if ((raw + cal) % 2 != 0) {
                    drawCell(g, raw, cal);
                }
            }
        }
    }

    private void drawCell(Graphics2D g, int raw, int cal) {
        g.fillRect(cal * step, (board.length - raw - 1) * step, step, step);
    }

    private void activate(Graphics2D g) {
        if (first != null) {
            g.setColor(Color.YELLOW);
            drawCell(g, first.x, first.y);
            if (second != null) {

                first = null;
                second = null;

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int raw = board.length - e.getY() / step - 1;
        int cal = e.getX() / step;
        if (raw < 0 || raw >= board.length || cal < 0 || cal >= board.length) {
            return;
        }
        if (first == null) {
            if (game.leftMouseClick1(raw, cal)) {
                first = new Point(raw, cal);
            }
        } else {
            if (game.leftMouseClick2(raw, cal)) {
                second = new Point(raw, cal);
            } else {
                first = null;
            }
        }
        cf.updateView();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
