/*
DAN ZHANG
dzhang29
LINAN LI
dzhang29@u.rochester.edu
MW 2:00-3:15pm
I did not collaborate with anyone on this assignment.
 */

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Game game = new Game();

        while (true) {
            System.out.println("maximum move: " + game.move);
            System.out.print("Please enter your movement: ");

            Scanner scanner = new Scanner(System.in);
            char dir = scanner.next().charAt(0);
            scanner.nextLine();

            if (dir == 'a' || dir == 'd' || dir == 'w' || dir == 's'){
                game.runningSum2DArray(dir);
            } else if (dir == 'r'){
                game.restart();
            }else if (dir == 'q'){
                break;
            } else {
                game.refresh();
                System.out.println("INVALID INPUT!");
                System.out.println();
                game.printArray(game.arr);
            }

        }

    }
}
