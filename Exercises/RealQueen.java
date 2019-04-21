
import java.util.Scanner;

public class RealQueen {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] chessBoard = new char[8][8];

        for (int i = 0; i < 8; i++) {

            String[]currentLine = scanner.nextLine().split(" ");

            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = currentLine[j].charAt(0);
            }
        }

        boolean found = false;

        search: for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                char currentChar = chessBoard[i][j];

                if (currentChar == 'q') {

                    if (!validSearch(chessBoard, i, j,"Search row")) {
                        break;
                    }
                    if (!validSearch(chessBoard, i, j, "wtf")) {
                        break;
                    }
                    if (!validDiagonalSearch(chessBoard, i, j)) {
                        break;
                    }
                    System.out.println(String.format("%d %d", i, j));
                    break search;
                }
            }
        }
    }
    private static boolean validSearch (char[][] matrix, int position, int row, String aspect) {

        String currentChar;

        if (aspect.equals("Search row")) {
            for (int i = 0; i < 8; i++) {

                currentChar = "" + matrix[position][i];
                if (currentChar.equals("q") && i != row) {
                    return false;
                }
            }
        }else {
            for (int i = 0; i < 8; i++) {

                currentChar = "" + matrix[i][row];
                if (currentChar.equals("q") && i != position) {
                    return false;
                }
            }
        }
        return true;
    }

    //                        firstHalf = position - 1
//                        secondHalf = 8 - position

    private static boolean validDiagonalSearch (char[][] matrix, int row, int col) {

        int colRight = col;
        int colLeft = col;

        for (int i = row - 1; i >= 0 ; i--) {

            if (colLeft >= 1) {
                char currentChar = (char)matrix[i][--colLeft];
                if ( currentChar == 'q') {
                    return false;
                }
            }

            if (colRight < 7) {
                char currentChar = matrix[i][++colRight];
                if (currentChar == 'q') {
                    return false;
                }
            }
        }

        int temp = col;

        for (int i = row + 1; i < 8; i++) {

            if (temp >= 1) {
                char currentChar = matrix[i][--temp];
                if (currentChar == 'q') {
                    return false;
                }
            }
            if (col < 7) {
                char currentChar = matrix[i][++col];
                if (currentChar == 'q') {
                    return false;
                }
            }

        }
        return true;
    }
}
