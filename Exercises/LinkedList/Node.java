package LinkedList;

public class Node {
    Node next;
    Node previous;
    int element;

    Node(int value) {
        this.element = value;
        this.next = null;
        this.previous = null;
    }
}
