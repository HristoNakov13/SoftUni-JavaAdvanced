package threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bfr.readLine().split(" ");
        List<Threeuple> tuples = new ArrayList<>();

        Threeuple<String, String, String> nameAddress = new Threeuple(String.format("%s %s", input[0], input[1]), input[2], input[3]);
        tuples.add(nameAddress);

        input = bfr.readLine().split(" ");
        boolean drunk = false;
        if (input[2].equals("drunk")) {
            drunk = true;
        }
        Threeuple<String, Integer, Boolean> nameBeer = new Threeuple<>(input[0], Integer.parseInt(input[1]), drunk);
        tuples.add(nameBeer);

        input = bfr.readLine().split(" ");
        Threeuple<String, Double, String> numbers = new Threeuple(input[0], Double.parseDouble(input[1]), input[2]);
        tuples.add(numbers);

        for (Threeuple threeuple : tuples) {
            System.out.println(String.format("%s -> %s -> %s"
                    , threeuple.getItem1()
                    , threeuple.getItem2()
                    , threeuple.getItem3()));
        }
    }
}