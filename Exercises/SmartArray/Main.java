package SmartArray;

import SmartArray.SmartArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        SmartArray lol = new SmartArray();
        lol.add(500);
        lol.remove(0);
        lol.add(5);
        System.out.println(lol.get(0));
        lol.add(4);
        lol.add(1, 43);
//        lol.forEach(System.out::print);
        System.out.println(lol.indexOf(4));
        lol.add(4);
        lol.add(4);
        lol.set(50, 0);
        System.out.println(lol.lastIndexOf(4));

        List<String> list = new ArrayList<>();
        list.indexOf("bet");
        lol.removeIf(e -> e < 5);
        list.add(String.valueOf(4));
        list.add(String.valueOf(4));
        UnaryOperator<String> wtf = e -> e = "wtf";
        list.replaceAll(wtf);
        System.out.println(list.toString());

        System.out.println(lol.toString());

    }
}