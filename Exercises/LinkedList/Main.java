package LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        list.addFirst(4);
        list.addAfter(4, 2);
        list.addFirst(5);
        list.forEach(e -> System.out.print(e + " "));
        System.out.println("\n---------------");

        System.out.println(list.contains(7));
        System.out.println(list.contains(4));
        System.out.println("---------------");

        list.remove(2);
        list.forEach(System.out::println);
        System.out.println("---------------");

        for (int i = 0; i < 5; i++) {
            list.addLast(i);
        }
        list.forEach(e -> System.out.print(e + " "));
    }
}
