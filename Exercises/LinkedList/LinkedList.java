package LinkedList;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.function.Consumer;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;


    LinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void addFirst(int element) {

        Node addEle = new Node(element);

        if (this.size == 0) {
            this.tail = addEle;
            this.head = addEle;
        }else {
            addEle.next = this.head;
            this.head.previous = addEle;
            this.head = addEle;
        }
        this.size++;
    }
    public void addAfter (int searchElement, int newElement) {

        Node addElement = new Node(newElement);

        if (this.size == 0) {
            throw new InvalidDnDOperationException("AddAfter on empty list");
        }
        Node current = this.head;

        while (current != null) {

            if (current.element == searchElement) {

                addElement.next = current.next;
                addElement.previous = current;

                if (current.next != null) {
                    current.next.previous = addElement;
                }else {
                    this.tail = addElement;
                }
                current.next = addElement;
                this.size++;
                return;
            }
            current = current.next;
        }
        throw new InvalidDnDOperationException("Search Element not found");
    }
    public void addLast(int element) {

        Node addEle = new Node(element);

        if (size == 0) {
            this.tail = addEle;
            this.head = addEle;
        }else {
            this.tail.next = addEle;
            addEle.previous = this.tail;
            this.tail = addEle;
        }
        this.size++;

    }

    public int removeFirst() {
        if (this.size == 0) {
            throw new InvalidDnDOperationException("Remove called for collection with size 0");
        }
        int firstElement = this.head.element;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        }else {
            this.head = this.head.next;
            this.head.previous = null;
        }
        this.size--;
        return firstElement;
    }
    public int removeLast() {
        if (this.size == 0) {
            throw new InvalidDnDOperationException("Remove called for collection with size 0");
        }
        int removeEle = this.tail.element;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        }else {
            this.tail = this.tail.previous;
            this.tail.next = null;
        }
        this.size--;
        return removeEle;
    }
    public int removeAfter (int removeElement) {

        if (isEmpty()) {

        }
        Node current = this.head;
        while (current != null) {

            if (current.element == removeElement) {

                if (current.next != null) {
                    Node deleteEle = current.next;

                    if (current.next.next == null) {
                        this.tail = current;
                        current.next = null;
                    }else {
                        current.next = current.next.next;
                        current.next.previous = current;
                    }
                    this.size--;
                    return deleteEle.element;
                }
            }
            current = current.next;
        }
        throw new InvalidDnDOperationException("Search Element not found");
    }
    public void remove (int removeElement) {
        Node current = this.head;

        while (current != null) {

            if (current.element == removeElement) {

                if (current.next == null) {
                    this.tail = current.previous;
                    current.previous.next = null;
                }else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                return;
            }
            current = current.next;
        }
        throw new InvalidDnDOperationException("Element not found");

    }

    public int size() {
        return this.size;
    }
    public int[] toArray() {

        int[] array = new int[this.size];
        int index = 0;
        Node current = this.head;

        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }
    public void forEach(Consumer<Integer> consumer) {

        Node tempNode = this.head;

        while (tempNode != null) {

            consumer.accept(tempNode.element);
            tempNode = tempNode.next;
        }
    }

    public boolean contains(int searchElement) {

        Node current = this.head;

        while (current != null) {

            if (current.element == searchElement) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getFirst() {
        return this.head.element;
    }
    public int getLast() {
        return this.tail.element;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
