import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Agents {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        Deque<String> IDs = new ArrayDeque<>();
        Arrays.stream(bfr.readLine().split(" ")).forEach(IDs::push);

        Deque<String> agents = new ArrayDeque<>();
        Arrays.stream(bfr.readLine().split(" ")).forEach(agents::offer);

        String input;

        while (!"stop".equals(input = bfr.readLine())) {

            String[] line = input.split("-");
            if (line.length == 1) {
                continue;
            }
            String[] data = line[1].split(" ");

            if ("add".equals(line[0])) {

                if ("ID".equals(data[0])) {
                    IDs.push(data[1]);
                } else if ("agent".equals(data[0])) {
                    agents.offer(data[1]);
                }
                continue;
            }
            if ("remove".equals(line[0]) && "ID".equals(data[0])) {
                if (!IDs.isEmpty()) {
                    System.out.println(String.format("%s has been removed."
                            , IDs.removeFirst()));
                }
            } else if ("agent".equals(data[0])) {
                if (!agents.isEmpty()) {
                    System.out.println(String.format("%s has left the queue."
                            , agents.removeLast()));
                }
            }
            if ("sort".equals(line[0]) && "ID".equals(data[0])) {
                String[] temp = new String[IDs.size()];
                transferToArray(IDs, temp);
                Arrays.stream(temp).sorted().forEach(IDs::addFirst);
            }
        }
        if (IDs.size() >= agents.size()) {
            while (!agents.isEmpty()) {
                String print = String.format("%s takes ID number: %s"
                , agents.poll(), IDs.pop());
                System.out.println(print);
            }
            if (!IDs.isEmpty()) {
                System.out.println("No more agents left.");
            }
            while (!IDs.isEmpty()) {
                System.out.println(String.format("ID number: %s"
                , IDs.pop()));
            }
        }else {
            while (!IDs.isEmpty()) {
                String print = String.format("%s takes ID number: %s"
                        , agents.poll(), IDs.pop());
                System.out.println(print);
            }
            while (!agents.isEmpty()) {
                System.out.println(String.format("%s does not have an ID."
                , agents.poll()));
            }
        }
    }
    private static void transferToArray(Deque<String> deque, String[] array) {
        int counter = 0;

        while (!deque.isEmpty()) {
            array[counter++] = deque.pop();
        }
    }
}
