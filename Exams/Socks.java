package Exams;

import java.util.*;
public class Socks {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> leftSocks = new ArrayDeque<>();
        ArrayDeque<Integer> rightSocks = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).forEach(leftSocks::push);
        Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).forEach(rightSocks::offer);

        List<Integer> pairs = new ArrayList<>();
        int biggestPair = Integer.MIN_VALUE;

        while (leftSocks.size() != 0 && rightSocks.size() != 0) {

            int left = leftSocks.peek();
            int right = rightSocks.peek();

            if (left > right) {

                int pair = left + right;
                pairs.add(pair);
                if (pair > biggestPair) {
                    biggestPair = pair;

                }
                leftSocks.pop();
                rightSocks.poll();
            } else if (left == right) {

                rightSocks.poll();
                leftSocks.pop();
                leftSocks.push(++left);
            } else {

                leftSocks.pop();
            }
        }
        System.out.println(biggestPair);
        pairs.forEach(pair -> System.out.print(pair + " "));
    }
}