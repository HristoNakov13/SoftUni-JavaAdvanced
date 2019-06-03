package CarSalesman;

public class Car {
    private String model;
    private String color;
    private Engine engine;
    private String weight;
    private static final String undefined = "n/a";

    public Car (String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.color = undefined;
        this.weight = undefined;
    }
    public Car (String model, Engine engine, String weight) {
        this(model, engine);
        this.weight = weight;
        this.color = undefined;
    }

    public Car(String model, Engine engine, String weight, String color) {
        this(model, engine, weight);
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    @Override
    public  String toString() {
        return String.format("%s:%n%s%nWeight: %s%nColor: %s%n"
        , this.model, this.engine.toString(), this.weight, this.color);
    }

}
