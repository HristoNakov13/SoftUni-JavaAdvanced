package RawData;

public class Tire {
    private Integer age;
    private Double pressure;

    public Tire(Integer age, Double pressure) {
        this.age = age;
        this.pressure = pressure;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
}
