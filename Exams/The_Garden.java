import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class The_Garden {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        int dimensions = Integer.parseInt(bfr.readLine());

        char[][] garden = new char[dimensions][];

        for (int i = 0; i < dimensions; i++) {
            garden[i] = bfr.readLine().replaceAll(" ", "").toCharArray();
        }
        int carrots = 0;
        int lettuce = 0;
        int potatoes = 0;
        int harmedVegies = 0;
        String input;

        while (!"End of Harvest".equals(input = bfr.readLine())) {
            String[] line = input.split(" ");
            String command = line[0];
            int row = Integer.parseInt(line[1]);
            int col = Integer.parseInt(line[2]);

            switch (command) {
                case "Harvest":
                    if (validCoordinates(garden, row, col)) {
                        if (garden[row][col] == 'C') {
                            carrots++;
                        } else if (garden[row][col] == 'P') {
                            potatoes++;
                        } else if (garden[row][col] == 'L') {
                            lettuce++;
                        }
                        garden[row][col] = ' ';
                    }
                    break;
                case "Mole":
                    if (!validCoordinates(garden, row, col)) {
                        continue;
                    }
                    String direction = line[3];
                    switch (direction) {
                        case "up":
                            while (row > 0) {
                                harmedVegies += moleMayhem(garden, row, col);
                                row -= 2;
                            }
                            break;
                        case "down":
                            while (row < garden.length) {
                                harmedVegies += moleMayhem(garden, row, col);
                                row += 2;
                            }
                            break;
                        case "right":
                            while (col < garden[row].length) {
                                harmedVegies += moleMayhem(garden, row, col);
                                col += 2;
                            }
                            break;
                        case "left":
                            while (col > 0) {
                                harmedVegies += moleMayhem(garden, row, col);
                                col -= 2;
                            }
                    }
            }
        }
        for (int i = 0; i < garden.length; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < garden[i].length; j++) {
                line.append(garden[i][j]).append(" ");
            }
            System.out.println(line.toString().trim());
        }
        System.out.println(String.format("Carrots: %d%nPotatoes: %d%nLettuce: %d%nHarmed vegetables: %d"
                , carrots, potatoes, lettuce, harmedVegies));
    }

    private static boolean validCoordinates(char[][] garden, int row, int col) {
        return row >= 0 && row < garden.length && col >= 0 && col < garden[row].length;
    }

    private static int moleMayhem(char[][] garden, int row, int col) {
        int harmedVegies = 0;
        try {
            if (garden[row][col] == 'P' || garden[row][col] == 'C' || garden[row][col] == 'L') {
                harmedVegies++;
            }
            garden[row][col] = ' ';
        } catch (Exception ignored) {
        }
        return harmedVegies;
    }
}