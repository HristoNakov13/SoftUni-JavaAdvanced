import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class RadioactiveMutantVampireBunnies {
    private static ArrayDeque<int[]> changeIndexes = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(buffer.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        String[][] lair = new String[rows][cols];

        boolean playerNotFound = true;
        int[] playerPosition = new int[2];

        for (int i = 0; i < rows; i++) {
            String[] current = new String[cols];
            String line = buffer.readLine();

            for (int j = 0; j < line.length(); j++) {
                current[j] = String.valueOf(line.charAt(j));

                if (playerNotFound) {
                    if (current[j].equals("P")) {
                        playerNotFound = false;
                        playerPosition[0] = i;
                        playerPosition[1] = j;
                    }
                }
            }
            lair[i] = current;
        }
        int turnCounter = 0;
        String movementInput = buffer.readLine();
        boolean dead = false;
        boolean winner = false;
        int[] lastKnownPosition = new int[2];

        while (true) {

            char moveCommand = movementInput.charAt(turnCounter++);
            lair[playerPosition[0]][playerPosition[1]] = ".";

            lastKnownPosition[0] = playerPosition[0];
            lastKnownPosition[1] = playerPosition[1];

            switch (moveCommand) {
                case 'U':
                    playerPosition[0] -= 1;
                    break;
                case 'L':
                    playerPosition[1] -= 1;
                    break;
                case 'R':
                    playerPosition[1] += 1;
                    break;
                case 'D':
                    playerPosition[0] += 1;
                    break;
            }
            boolean outOfLair = playerPosition[0] < 0 || playerPosition[0] >= rows
                    || playerPosition[1] < 0 || playerPosition[1] >= cols;

            if(!outOfLair) {
                if (lair[playerPosition[0]][playerPosition[1]].equals("B")) {
                    dead = true;
                }else {
                    lair[playerPosition[0]][playerPosition[1]] = "P";
                }
            }else {
                winner = true;
            }

            for (int i = 0; i < rows; i++) {

                for (int j = 0; j < cols; j++) {

                    if (lair[i][j].equals("B")) {
                        bunnyMultiplication(lair, i, j);
                    }
                }
            }
            while (!changeIndexes.isEmpty()) {
                int[] currentPosition = changeIndexes.pop();
                if (lair[currentPosition[0]][currentPosition[1]].equals("killed")) {
                    dead = true;
                }
                lair[currentPosition[0]][currentPosition[1]] = "B";
            }
            if (outOfLair || dead) {
                break;
            }
        }
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                System.out.print(lair[i][j]);
            }
            System.out.println();
        }
        if (!winner) {
            System.out.println(String.format("dead: %d %d", playerPosition[0], playerPosition[1]));
        }else {
            System.out.println(String.format("won: %d %d", lastKnownPosition[0], lastKnownPosition[1]));
        }
    }
    private static void bunnyMultiplication (String[][] lair, int row, int col) {

        if (row - 1 >= 0) {
            bunnySpawnCellCheck(lair, row - 1, col);
        }
        if (row + 1 < lair.length) {
            bunnySpawnCellCheck(lair, row + 1, col);
        }
        if (col + 1 < lair[0].length) {
            bunnySpawnCellCheck(lair, row, col + 1) ;
        }
        if (col - 1 >= 0) {
            bunnySpawnCellCheck(lair, row, col - 1);
        }
    }
    private static void bunnySpawnCellCheck (String[][] lair, int row, int col) {
        if (lair[row][col].equals("P")) {
            lair[row][col] = "killed";
            createBunnyAt(row, col);
        }else if (lair[row][col].equals(".")){
            lair[row][col] = "B1";
            createBunnyAt(row, col);
        }
    }
    private static void createBunnyAt (int row, int col) {
        int[] coordinates = new int[2];
        coordinates[0] = row;
        coordinates[1] = col;
        changeIndexes.push(coordinates);
    }
}