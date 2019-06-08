import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mission {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("M.*I.*S.*S.*I.*O.*N.*");
        String input;
        String bestSuccessfulName = "";
        int bestSuccessfulRating = -1;
        String bestFailedName = "";
        int bestFailedRating = -1;

        while (!"Decrypt".equals(input = bfr.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (!matcher.find()) {
                continue;
            }
            int missionRating = 0;
            StringBuilder name = new StringBuilder();
            boolean successfulMission = true;

            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                if (Character.isDigit(currentChar)) {
                    missionRating += Character.getNumericValue(currentChar);
                } else if (Character.isLowerCase(currentChar)) {
                    name.append(currentChar);
                } else if (currentChar == 'X') {
                    successfulMission = false;
                }
            }
            if (successfulMission) {
                if (bestSuccessfulRating < missionRating) {
                    bestSuccessfulName = name.toString();
                    bestSuccessfulRating = missionRating;
                }
            } else {
                if (bestFailedRating < missionRating) {
                    bestFailedRating = missionRating;
                    bestFailedName = name.toString();
                }
            }
        }
        System.out.println(String.format("Completed mission %s with rating: %d"
                , bestSuccessfulName, bestSuccessfulRating));

        System.out.println(String.format("Failed Mission %s with rating: %d"
                , bestFailedName, bestFailedRating));

    }
}
