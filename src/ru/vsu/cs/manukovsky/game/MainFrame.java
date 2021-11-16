package ru.vsu.cs.manukovsky.game;

import ru.vsu.cs.manukovsky.figure.Figure;
import ru.vsu.cs.manukovsky.util.DrawUtils;
import ru.vsu.cs.manukovsky.util.JTableUtils;
import ru.vsu.cs.manukovsky.util.SwingUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame{
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelMoveCount;
    private JLabel labelMoveCount;
    private JButton buttonNewGame;
    private JTable tableGameField;
    private JLabel labelStatus;

        private static final int DEFAULT_GAP = 10;
        private static final int DEFAULT_CELL_SIZE = 70;
        // private static final int DEFAULT_NEW_GAME_BUTTON_SIZE = 50;

        //private static final RubikGame.SapperCell NULL_CELL = new RubikGame.SapperCell(RubikGame.CellState.NULL, Color.WHITE, ' ');

        private ChessGame game = new ChessGame();

        private ParamsDialog dialogParams;

        public MainFrame() {
            this.setTitle("Шахматы");
            this.setContentPane(panelMain);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();

            setJMenuBar(createMenuBar());
            this.pack();

            SwingUtils.setShowMessageDefaultErrorHandler();
            labelMoveCount.setFont(new Font("Comic Sans MS", Font.PLAIN, labelMoveCount.getFont().getSize()));
            labelMoveCount.setForeground(new Color(128, 0, 0));
            panelMoveCount.setBackground(Color.LIGHT_GRAY);

            tableGameField.setRowHeight(DEFAULT_CELL_SIZE);
            JTableUtils.initJTableForArray(tableGameField, DEFAULT_CELL_SIZE, false, false, false, false);
            tableGameField.setIntercellSpacing(new Dimension(0, 0));
            tableGameField.setEnabled(false);

            tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                final class DrawComponent extends Component {
                    private int row = 0, column = 0;

                    @Override
                    public void paint(Graphics gr) {
                        Graphics2D g2d = (Graphics2D) gr;
                        int width = getWidth() - 2;
                        int height = getHeight() - 2;
                        paintCell(row, column, g2d, width, height);
                    }
                }

                DrawComponent comp = new DrawComponent();

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus, int row, int column) {
                    comp.row = row;
                    comp.column = column;
                    return comp;
                }
            });

            newGame();

            updateWindowSize();
            updateView();
            buttonNewGame.addActionListener(e -> {
                newGame();
            });
            final Point[] p = {null};
            final boolean[] check = {false};
            //dialogParams = new ParamsDialog(tableGameField, e -> newGame());
            tableGameField.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = tableGameField.rowAtPoint(e.getPoint());
                    int col = tableGameField.columnAtPoint(e.getPoint());
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if(game.leftMouseClickCheck(row, col)){
                            if (!check[0]) {
                                check[0] = game.leftMouseClick1(row, col);
                                System.out.println(check[0]);
                                game.getChessBoard().paint();
                            } else {
                                game.leftMouseClick2(row, col);
                                game.getChessBoard().paint();
                                check[0]= false;
                            }
                        }
                        //game.getChessBoard().paint();
                        updateView();
                    }
                }
            });
        }

        private JMenuItem createMenuItem(String text, String shortcut, Character mnemonic, ActionListener listener) {
            JMenuItem menuItem = new JMenuItem(text);
            menuItem.addActionListener(listener);
            if (shortcut != null) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut.replace('+', ' ')));
            }
            if (mnemonic != null) {
                menuItem.setMnemonic(mnemonic);
            }
            return menuItem;
        }

        private JMenuBar createMenuBar() {
            JMenuBar menuBarMain = new JMenuBar();

            JMenu menuGame = new JMenu("Игра");
            menuBarMain.add(menuGame);
            menuGame.add(createMenuItem("Новая", "ctrl+N", null, e -> {
                newGame();
            }));
            menuGame.add(createMenuItem("Параметры", "ctrl+P", null, e -> {
                dialogParams.updateView();
                dialogParams.setVisible(true);
            }));
            menuGame.addSeparator();
            menuGame.add(createMenuItem("Выход", "ctrl+X", null, e -> {
                System.exit(0);
            }));

            JMenu menuView = new JMenu("Вид");
            menuBarMain.add(menuView);
            menuView.add(createMenuItem("Подогнать размер окна", null, null, e -> {
                updateWindowSize();
            }));
            menuView.addSeparator();
            SwingUtils.initLookAndFeelMenu(menuView);

            JMenu menuHelp = new JMenu("Справка");
            menuBarMain.add(menuHelp);
            menuHelp.add(createMenuItem("Правила", "ctrl+R", null, e -> {
                SwingUtils.showInfoMessageBox(
                        "Правила игры «2D Кубик рубика»:" +
                                "\n 1)Перемещайте цветные плитки с помощью 5 серых кнопок" +
                                "\n 2)Цель игры сгрупировать одинаковые цвета вокруг каждой крайней кнопки.", "Правила");
            }));
            menuHelp.add(createMenuItem("О программе", "ctrl+A", null, e -> {
                SwingUtils.showInfoMessageBox(
                        "Игра «2D Кубик рубика»" +
                                "\n– пример логической игры на Экзамен первого учебного семестра факультета ФКН" +
                                "\n\nАвтор: Мануковский И. С. 6 группа" +
                                "\nE-mail: DogMan113636@gmail.com",
                        "О программе"
                );
            }));

            return menuBarMain;
        }

        private void updateWindowSize() {
            int menuSize = this.getJMenuBar() != null ? this.getJMenuBar().getHeight() : 0;
            SwingUtils.setFixedSize(
                    this,
                    tableGameField.getWidth() + 2 * DEFAULT_GAP + 60,
                    tableGameField.getHeight() + panelMain.getY() + panelTop.getHeight() + labelStatus.getHeight() +
                            menuSize + 3 * DEFAULT_GAP + 2 * DEFAULT_GAP + 60
            );
            this.setMaximumSize(null);
            this.setMinimumSize(null);
        }

        private void updateView() {
            labelMoveCount.setText("" + (game.getTapCount()));
            tableGameField.repaint();
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


        private Font font = null;

        private Font getFont(int size) {
            if (font == null || font.getSize() != size) {
                font = new Font("Times New Roman MS", Font.BOLD, size);
            }
            return font;
        }

        private void paintCell(int row, int column, Graphics2D g2d, int cellWidth, int cellHeight) {
            Figure figure = game.getBoard()[row][column];

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /*if (figure == null) {
            figure = NULL_CELL;
        }*/
            int size = Math.min(cellWidth, cellHeight);

            Color backColor;
            if ((figure.getPoint().x+figure.getPoint().y)%2==0) {
                backColor = Color.green;
            } else backColor = Color.yellow;

            g2d.setColor(backColor);
            g2d.fillRect(0, 0, size, size);

            //Color color = DrawUtils.getContrastColor(Color.LIGHT_GRAY);
            char ch = figure.paint();
            g2d.setColor(Color.black);
            int bound = (int) Math.round(size * 0.1);
            Font font = getFont(size - 2 * bound);
            DrawUtils.drawStringInCenter(g2d, font, "" + ch, 0, 0, cellWidth, (int) Math.round(cellHeight * 0.95));
        }

        private void newGame() {
            game.newGame();
            JTableUtils.resizeJTable(tableGameField,
                    game.getSize(), game.getSize(),
                    tableGameField.getRowHeight(), tableGameField.getRowHeight()
            );
            game.resetTapCount();
            updateView();
        }
}
