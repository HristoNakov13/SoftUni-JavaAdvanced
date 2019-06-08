import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BombField {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(bfr.readLine());
        String[] movementCommands = bfr.readLine().split(",");

        char[][] field = new char[size][size];
        int[] playerPosition = new int[2];
        int bombCount = 0;

        for (int i = 0; i < size; i++) {

            char[] current = bfr.readLine().replaceAll(" ", "").toCharArray();
            field[i] = current;

            for (int j = 0; j < current.length; j++) {
                if ('B' == current[j]) {
                    bombCount++;
                } else if ('s' == current[j]) {
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                }
            }
        }
        boolean endPointReached = false;
        for (String command : movementCommands) {
            int row = playerPosition[0];
            int col = playerPosition[1];

            switch (command) {
                case "up":
                    if (outOfBoundsCheck(field, row - 1, col)) {
                        --row;
                        if (field[row][col] == 'e') {
                            endPointReached = true;
                        } else if (bombCheck(field, row, col)) {
                            bombCount--;
                        }
                    }
                    break;
                case "down":
                    if (outOfBoundsCheck(field, row + 1, col)) {
                        ++row;
                        if (field[row][col] == 'e') {
                            endPointReached = true;
                        } else if (bombCheck(field, row, col)) {
                            bombCount--;
                        }
                    }
                    break;
                case "left":
                    if (outOfBoundsCheck(field, row, col - 1)) {
                        --col;
                        if (field[row][col] == 'e') {
                            endPointReached = true;
                        } else if (bombCheck(field, row, col)) {
                            bombCount--;
                        }
                    }
                    break;
                case "right":
                    if (outOfBoundsCheck(field, row, col + 1)) {
                        ++col;
                        if (field[row][col] == 'e') {
                            endPointReached = true;
                        } else if (bombCheck(field, row, col)) {
                            bombCount--;
                        }
                    }
                    break;
            }
            if (endPointReached || bombCount == 0) {
                break;
            }
            playerPosition[0] = row;
            playerPosition[1] = col;
        }
        if (bombCount > 0 && endPointReached) {
            System.out.println(String.format("END! %d bombs left on the field", bombCount));
        } else if (bombCount == 0 && !endPointReached) {
            System.out.println("Congratulations! You found all bombs!");
        } else {
            System.out.println(String.format("%d bombs left on the field. Sapper position: (%d,%d)"
                    , bombCount, playerPosition[0], playerPosition[1]));
        }

    }

    private static boolean outOfBoundsCheck(char[][] field, int row, int col) {
        return row >= 0 && row < field.length && col >= 0 && col < field[0].length;
    }

    private static boolean bombCheck(char[][] field, int row, int col) {
        if (field[row][col] == 'B') {
            System.out.println("You found a bomb!");
            field[row][col] = '+';
            return true;
        }
        return false;
    }
}
