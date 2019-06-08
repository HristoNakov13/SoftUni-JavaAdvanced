package SmartArray;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class SmartArray {
    private static final int INITIAL_CAPACITY = 4;
    private int size;
    private int capacity;
    private int[] data;

    public SmartArray() {
        this.data = new int[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    public void add(int element) {
        if (this.size == this.capacity) {
            this.grow();
        }
        this.data[this.size] = element;
        this.size++;
    }

    public int remove(int index) {
        checkIndex(index);
        if (this.size == this.capacity / 4) {
            this.shrink();
        }
        int element = this.data[index];
        this.shiftLeft(index);
        this.data[this.size - 1] = 0;
        this.size--;
        return element;
    }

    public void removeIf(Predicate<Integer> predicate) {
        for (int i = 0; i < this.size; i++) {
            if (predicate.test(this.data[i])) {
                remove(i);
                i--;
            }
        }
    }

    public void add(int index, int element) {
        if (index == this.size) {
            this.add(element);
        } else {
            checkIndex(index);
        }
        if (this.size == this.capacity) {
            this.grow();
        }
        int temp = this.data[this.size - 1];
        this.shiftRight(index);
        this.size++;
        this.data[index] = element;
        this.data[this.size - 1] = temp;
    }

    public void set(int index, int element) {
        checkIndex(index);
        this.data[index] = element;
    }

    public void replaceAll(UnaryOperator<Integer> unaryOperator) {
        for (int i = 0; i < this.size; i++) {
            unaryOperator.apply(this.data[i]);
        }
    }

    public int get(int index) {
        checkIndex(index);

        return this.data[index];
    }

    public int indexOf(int element) {
        for (int i = 0; i < this.size; i++) {
            if (element == this.data[i]) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int element) {
        int value = -1;
        for (int i = 0; i < this.size; i++) {
            if (element == this.data[i]) {
                value = i;
            }
        }
        return value;
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.data[i]);
        }
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


    public int size() {
        return this.size;
    }

    public void clear() {
        this.data = new int[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();
        asString.append("[");
        for (int i = 0; i < this.size; i++) {
            asString.append(String.valueOf(this.data[i]));
            if (i != this.size - 1) {
                asString.append(", ");
            }
        }
        asString.append("]");
        return asString.toString();
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

    private void shiftLeft(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
    }

    private void shiftRight(int index) {
        for (int i = this.size - 1; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            String message = String.format("Index %d out of bounds for length %d"
                    , index, this.size);
            throw new IndexOutOfBoundsException(message);
        }
    }
}
