package fi.jaakko;

import fi.jaakko.game.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    /**
     * Tekstikäyttöliittymä testausta varten.
     * @param args komentorivi argumentit, ei käytetä
     */
    public static void main(final String[] args) {
        //testamista varten; ei lopullinen
        Board board = new Board(true);
        Game game = new Game(board);
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
            if (board.board()[x][y] == null || x > 7 || y > 7 || x < 0 || y < 0) {
                System.out.println("Kohdassa ei ole pelinappulaa.");
                continue;
            }
            if (board.board()[x][y].getColour() != game.getCurrentColour()) {
                System.out.print("Ei ole ");
                if (board.board()[x][y].getColour().value() == 1) {
                    System.out.println(" valkoisen vuoro!");
                } else {
                    System.out.println(" mustan vuoro!");
                }
                continue;
            }
            if (board.board()[x][y].moves().isEmpty()) {
                System.out.println("Tälle nappulalle ei ole sallittuja siirtoja!");
                continue;
            }
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
                boolean b = game.tryMove(x, y, x2, y2);
                if (b) {
                    game.nextTurn();
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
