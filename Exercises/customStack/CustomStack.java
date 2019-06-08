package customStack;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CustomStack {
    private static final int INITIAL_CAPACITY = 4;
    private int[] data;
    private int capacity;
    private int size;

    public CustomStack() {
        this.data = new int[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    public void push(int element) {
        if (this.size == capacity) {
            this.grow();
        }
        this.data[this.size] = element;
        this.size++;
    }

    public int pop() {
        if (this.size == 0) {
            String message = ("Pop call on empty stack");
            throw new NoSuchElementException(message);
        }
        this.size--;
        if (this.size == this.capacity / 4) {
            this.shrink();
        }
        return this.data[this.size];
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.data[i]);
        }
    }

    public int peek() {
        return this.data[this.size - 1];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(int element) {
        for (int i = 0; i < this.size; i++) {
            if (element == this.data[i]) {
                return true;
            }
        }
        return false;
    }

    private void shrink() {
        this.capacity /= 2;
        int[] copy = new int[this.capacity];
        for (int i = 0; i < this.size; i++) {
            copy[i] = this.data[i];
        }
        this.data = copy;
    }

    private void grow() {
        this.capacity *= 2;
        int[] copy = new int[this.capacity];
        for (int i = 0; i < this.size; i++) {
            copy[i] = this.data[i];
        }
        this.data = copy;
    }
}
