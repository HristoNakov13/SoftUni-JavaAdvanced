import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserLogs {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String input;
        Map<String, LinkedHashMap<String, Integer>> IPLog = new TreeMap<>();

        Pattern pattern = Pattern.compile("IP=(?<IP>[A-Za-z0-9:.]+).+user=(?<user>.{3,50})");

        while (!"end".equals(input = scanner.nextLine())) {
            Matcher matcher = pattern.matcher(input);
            String IP, username;

            if (matcher.find()) {
                IP = matcher.group("IP");
                username = matcher.group("user");
            }else {
                continue;
            }
            IPLog.putIfAbsent(username, new LinkedHashMap<>());
            IPLog.get(username).putIfAbsent(IP, 0);
            IPLog.get(username).put(IP, IPLog.get(username).get(IP) + 1);
        }
        IPLog.entrySet().forEach(user -> {

            System.out.println(user.getKey() + ":");
            StringBuilder print = new StringBuilder();

            user.getValue().entrySet().stream()
                    .forEach(ip -> print.append(String.format("%s => %d, ", ip.getKey(), ip.getValue())));
            System.out.println(print.delete(print.length() - 2, print.length()).append("."));
        });

    }
}