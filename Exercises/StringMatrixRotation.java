import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringMatrixRotation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("[A-Za-z]+\\((?<degree>\\d+)\\)");
        Matcher matcher = pattern.matcher(scanner.nextLine());

        int degrees;

        if (matcher.find()) {
            degrees = Integer.parseInt(matcher.group("degree")) % 360;
        }else {
            return;
        }


        List<String> words = new ArrayList<>();
        String input;
        int cols = -1;
        int rows = 0;


        while (!"END".equals(input = scanner.nextLine())) {

            rows++;
            words.add(input);

            if (input.length() > cols) {
                cols = input.length();
            }
        }

        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (j < words.get(i).length()) {
                    matrix[i][j] = Character.toString(words.get(i).charAt(j));
                }else {
                    matrix[i][j] = " ";
                }
            }

        }
        int temp = rows;

        if (degrees == 90 || degrees == 270) {
            rows = cols;
            cols = temp;
        }
        String[][] rotated = new String[rows][cols];
        System.out.println(print(matrix, degrees));

    }

    private static String print (String[][] matrix, int degrees) {

        StringBuilder print = new StringBuilder();

        switch (degrees) {
            case 0:
                for (int i = 0; i < matrix.length; i++) {

                    for (int j = 0; j < matrix[0].length; j++) {
                        print.append(matrix[i][j]);
                    }
                    print.append("\n");

                }
                break;
            case 90:
                for (int i = 0; i < matrix[0].length; i++) {

                    for (int j = 0; j < matrix.length; j++) {
                        print.append(matrix[matrix.length - 1 - j][i]);
                    }
                    print.append("\n");
                }
                break;
            case 180:
                for (int i = matrix.length - 1; i >= 0 ; i--) {

                    for (int j = matrix[0].length - 1; j >= 0 ; j--) {
                        print.append(matrix[i][j]);

                    }
                    print.append("\n");
                }
                break;
            case 270:
                for (int i = matrix[0].length - 1; i >= 0; i--) {

                    for (int j = 0; j < matrix.length; j++) {

                        print.append(matrix[j][i]);
                    }
                    print.append("\n");
                }

                break;
        }

        return print.toString();
    }
}