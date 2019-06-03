package CarSalesman;

public class Engine {
    private String model;
    private Integer power;
    private String displacement;
    private String efficiency;
    private static final String undefined = "n/a";

    public Engine(String model, Integer power) {
        this.model = model;
        this.power = power;
        this.displacement = undefined;
        this.efficiency = undefined;
    }
    public Engine(String model, Integer power, String displacement) {
        this(model, power);
        this.displacement = displacement;
        this.efficiency = undefined;
    }
    public Engine(String model, Integer power, String displacement, String efficiency) {
        this(model, power, displacement);
        this.efficiency = efficiency;

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
    @Override
    public String toString() {
        return String.format("%s:%nPower: %s%nDisplacement: %s%nEfficiency: %s",
                this.model, this.power, this.displacement, this.efficiency);
    }
}
