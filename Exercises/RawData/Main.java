package RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        int inputs = Integer.parseInt(bfr.readLine());
        List<Car> catalogue = new ArrayList<>();

        for (int i = 0; i < inputs; i++) {

            String[] carData = bfr.readLine().split(" ");

            int speed = Integer.parseInt(carData[1]);
            int power = Integer.parseInt(carData[2]);
            Engine engine = new Engine(speed, power);

            int weight = Integer.parseInt(carData[3]);
            String type = carData[4];
            Cargo cargo = new Cargo(weight, type);

            int tireAge;
            double tirePressure;
            Tire tire;
            List<Tire> tires = new ArrayList<>();

            tireAge = Integer.parseInt(carData[6]);
            tirePressure = Double.parseDouble(carData[5]);
            tire = new Tire(tireAge, tirePressure);
            tires.add(tire);

            tireAge = Integer.parseInt(carData[8]);
            tirePressure = Double.parseDouble(carData[7]);
            tire = new Tire(tireAge, tirePressure);
            tires.add(tire);

            tireAge = Integer.parseInt(carData[10]);
            tirePressure = Double.parseDouble(carData[9]);
            tire = new Tire(tireAge, tirePressure);
            tires.add(tire);

            tireAge = Integer.parseInt(carData[12]);
            tirePressure = Double.parseDouble(carData[11]);
            tire = new Tire(tireAge, tirePressure);
            tires.add(tire);

            String model = carData[0];

            Car car = new Car(model, engine, cargo, tires);
            catalogue.add(car);
        }
        String print = bfr.readLine();

        if ("fragile".equals(print)) {
            for (Car car : catalogue) {
                for (Tire tire : car.getTires()) {
                    if (tire.getPressure() < 1) {
                        System.out.println(car.getModel());
                        break;
                    }
                }
            }
        }else if ("flamable".equals(print)) {

            for (Car car : catalogue) {
                if (car.getEngine().getPower() > 250) {
                    System.out.println(car.getModel());
                }
            }

        }
    }
}
