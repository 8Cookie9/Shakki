package fi.jaakko;

import fi.jaakko.board.Board;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        //testamista varten; ei lopullinen
        Board board = new Board(true);
        Scanner scanner = new Scanner(System.in);
        System.out.println(board);
        String s;
        while (true) {
            System.out.print("X: ");
            s = scanner.nextLine();
            if (s.isEmpty()) {
                break;
            }
            int x = Integer.parseInt(s);
            System.out.print("Y: ");
            s = scanner.nextLine();
            if (s.isEmpty()) {
                break;
            }
            int y = Integer.parseInt(s);
            System.out.println("Sallitut siirrot: " + prnt(board.board()[x][y].moves()));
            while (true) {
                System.out.print("X2: ");
                s = scanner.nextLine();
                if (s.isEmpty()) {
                    break;
                }
                int x2 = Integer.parseInt(s);
                System.out.print("Y2: ");
                s = scanner.nextLine();
                if (s.isEmpty()) {
                    break;
                }
                int y2 = Integer.parseInt(s);
                boolean b = board.movePiece(x, y, x2, y2);
                if (b) {
                    break;
                } else {
                    System.out.println("Ei sallittu siirto.");
                }
            }
            System.out.println(board);
        }
    }

    private static String prnt(List<int[]> i) {
        String s = "";
        for (int[] a : i) {
            s += "{" + a[0] + ", " + a[1] + "}";
        }
        return s;
    }
}
