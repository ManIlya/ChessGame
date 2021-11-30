package ru.vsu.cs.manukovsky;

public class Main {

    public static void main(String[] args) {
        /*ChessBoard board = new ChessBoard();
        board.newBoard();
        board.paint();
        while(true){
            Scanner sc= new Scanner(System.in);
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            board.move(r1, c1, r2, c2);
            board.paint();
        }*/
        ChessFrame cf = new ChessFrame();
        cf.setVisible(true);
        //MainFrame mf = new MainFrame();
        //mf.setVisible(true);
    }
}
