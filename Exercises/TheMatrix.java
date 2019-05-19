import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

public class TheMatrix {

    private static ArrayDeque<int[]> enqueued = new ArrayDeque<>();
    private static ArrayDeque<int[]> queueCheck = new ArrayDeque<>();
    private static HashSet<String> changedCells = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(buffer.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < rows; i++) {

            String[] current = buffer.readLine().split(" ", cols);
            matrix[i] = current;
        }
        String fillChar = buffer.readLine();
        int[] initialPosition = Arrays.stream(buffer.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int startRow = initialPosition[0];
        int startCol = initialPosition[1];

        enqueued.offer(initialPosition);
        queueCheck.offer(initialPosition);
        changedCells.add(startRow + " " + startCol);
        String startChar = matrix[startRow][startCol];

        checkCell(matrix, startChar);

        while (!enqueued.isEmpty()) {
            int[] current = enqueued.poll();
            matrix[current[0]][current[1]] = fillChar;
        }
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

    }
    private static void checkCell (String[][] matrix, String startChar) {
        while (!queueCheck.isEmpty()) {

            int counter = 1;
            boolean left = false;
            boolean right = false;
            boolean up = false;
            boolean down = false;

            int[] position = queueCheck.poll();
            int startRow = position[0];
            int startCol = position[1];

            while (true) {
                int row, col;

                row = startRow + counter;
                col = startCol;
                if (!up && outOfBoundsCheck(row, col, matrix.length, matrix[0].length) && matrix[row][col].equals(startChar)
                        && !changedCells.contains(String.format("%d %d",row, col ))) {
                    enqueueCells(matrix, row, col);
                    checkCell(matrix, startChar);
                }else {
                    up = true;
                }
                row = startRow - counter;
                col = startCol;
                if (!down && outOfBoundsCheck(row, col, matrix.length, matrix[0].length) && matrix[row][col].equals(startChar)
                        && !changedCells.contains(String.format("%d %d",row, col ))) {
                    enqueueCells(matrix, row, col);
                    checkCell(matrix, startChar);
                }else {
                    down = true;
                }
                row = startRow;
                col = startCol + counter;
                if (!left && outOfBoundsCheck(row, col, matrix.length, matrix[0].length) && matrix[row][col].equals(startChar)
                        && !changedCells.contains(String.format("%d %d",row, col ))) {
                    enqueueCells(matrix, row, col);
                    checkCell(matrix, startChar);
                }else {
                    left = true;
                }
                row = startRow;
                col = startCol - counter;

                if (!right && outOfBoundsCheck(row, col, matrix.length, matrix[0].length) && matrix[row][col].equals(startChar)
                        && !changedCells.contains(String.format("%d %d",row, col ))) {
                    enqueueCells(matrix, row, col);
                    checkCell(matrix, startChar);
                }else {
                    right = true;
                }
                if (right && left && down && up) {
                    break;
                }
                counter++;
            }
        }
    }
    private static void enqueueCells (String[][] matrix, int row, int col) {
        changedCells.add(String.format("%d %d", row, col));
        int[] putInQueue = {row, col};
        enqueued.offer(putInQueue);
        queueCheck.offer(putInQueue);
    }
    private static boolean outOfBoundsCheck (int row, int col, int rowLength, int colLength) {
        return row >= 0 && row < rowLength && col >= 0 && col < colLength;
    }
}