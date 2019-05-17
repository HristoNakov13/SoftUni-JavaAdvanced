import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringMatrixRotation {
    public static void main(String[] args) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        int rotation = Integer.parseInt(buffer.readLine().replaceAll("[A-Za-z()]+", "")) % 360;
        String input;
        int maxLength = -1;
        List<String> inputLines = new ArrayList<>();

        while (!"END".equals(input = buffer.readLine())) {

            inputLines.add(input);
            if (input.length() > maxLength) {
                maxLength = input.length();
            }
        }
        char[][] matrix = new char[inputLines.size()][maxLength];

        for (int i = 0; i < inputLines.size(); i++) {
            for (int j = 0; j < maxLength; j++) {
                if (j < inputLines.get(i).length()) {
                    matrix[i][j] = inputLines.get(i).charAt(j);
                }else {
                    matrix[i][j] = ' ';
                }
            }
        }
        while (!"END".equals(input = buffer.readLine())) {

            matrix = matrixRotation(matrix, rotation);
            for (char[] chars : matrix) {

                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(chars[j]);
                }
                System.out.println();
            }
            rotation = Integer.parseInt(input);
        }
    }
    private static char[][] matrixRotation(char[][] matrix, int rotation) {

        char[][] rotatedMatrix;
        int rows, cols;

        switch (rotation) {
            case 90:
                rotatedMatrix = new char[matrix[0].length][matrix.length];
                rows = rotatedMatrix.length;
                cols = rotatedMatrix[0].length;

                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < cols; j++) {

                        rotatedMatrix[i][j] = matrix[rotatedMatrix[0].length - 1 - j][i];
                    }
                }
                break;
            case 180:
                rotatedMatrix = new char[matrix.length][matrix[0].length];
                rows = rotatedMatrix.length;
                cols = rotatedMatrix[0].length;

                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < cols; j++) {
                        rotatedMatrix[i][j] = matrix[matrix.length - 1 - i][matrix[0].length - 1 - j];
                    }
                }
                break;
            case 270:
                rotatedMatrix = new char[matrix[0].length][matrix.length];
                rows = rotatedMatrix.length;
                cols = rotatedMatrix[0].length;

                for (int i = 0; i < rows; i++) {

                    for (int j = 0; j < cols; j++) {
                        rotatedMatrix[i][j] = matrix[j][matrix[0].length - 1 - i];
                    }
                }
                break;
            default:
                rotatedMatrix = matrix;
                break;
        }
        return rotatedMatrix;
    }
}
