package ru.vsu.cs.manukovsky.game;

import java.util.Scanner;

public class ConsoleGame {
    public static void main(String[] args) {
        ChessGame cg = new ChessGame();
        cg.newGame();
        Scanner in = new Scanner(System.in);
        cg.getChessBoard().paint();
        int x1, x2, y1, y2;
        while (cg.getState() == GameState.MOVE_OF_WHITE ||
                cg.getState() == GameState.MOVE_OF_BLACK) {
            if(cg.getState() == GameState.MOVE_OF_WHITE){
            }
            System.out.println("Введите ходы:");
            String str = in.nextLine();
            int[] crd = converter(str);
            cg.leftMouseClick1(crd[0], crd[1]);
            cg.leftMouseClick2(crd[2], crd[3]);
            cg.getChessBoard().paint();
        }
    }
    public int[] getCell(String cord) {
        char[] ch = cord.toCharArray();
        int i = ch[1] - '1';
        int c = ch[0] - 'a';
        int[] coard = new int[2];
        coard[0]=i;
        coard[1]=c;
        return coard;
    }
    public static int[] converter(String str){
        char[] ch =str.toCharArray();
        int[] crd = new int[4];
        crd[0] = ch[1] - '1';
        crd[1] = ch[0] - 'a';
        crd[2] = ch[4] - '1';
        crd[3] = ch[3] - 'a';
        return crd;
    }
}
