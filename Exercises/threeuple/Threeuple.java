package threeuple;

public class Threeuple<T, K, G> extends Tuple {
    private G item3;

    public Threeuple(T item1, K item2, G itme3) {
        super(item1, item2);
        this.item3 = itme3;
    }

    public G getItem3() {
        return item3;
    }

    public void setItem3(G item3) {
        this.item3 = item3;
    }
}
