package CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        int enginesCount = Integer.parseInt(bfr.readLine());
        Map<String, Engine> enginesCatalogue = new HashMap<>();

        for (int i = 0; i < enginesCount; i++) {
            String[] lineData = bfr.readLine().split(" ");

            String model = lineData[0];
            int power = Integer.parseInt(lineData[1]);
            int displacement;
            String efficiency;

            Engine engine = new Engine(model, power);

            if (lineData.length == 3) {
                try {
                    displacement = Integer.parseInt(lineData[2]);
                    engine.setDisplacement(String.valueOf(displacement));
                }catch (Exception e) {
                    engine.setEfficiency(lineData[2]);
                }
            }else if (lineData.length == 4) {
                displacement = Integer.parseInt(lineData[2]);
                efficiency = lineData[3];
                engine.setDisplacement(String.valueOf(displacement));
                engine.setEfficiency(efficiency);
            }
            enginesCatalogue.putIfAbsent(model, engine);
        }
        int carsCount = Integer.parseInt(bfr.readLine());

        List<Car> carsCatalogue = new ArrayList<>();

        for (int i = 0; i < carsCount; i++) {
            String[] carData = bfr.readLine().split(" ");
            String model = carData[0];
            String engineModel = carData[1];

            Engine engine = enginesCatalogue.get(engineModel);
            Car car = new Car(model, engine);

            int weight;
            String color;
            if (carData.length == 3) {
                try {
                    weight = Integer.parseInt(carData[2]);
                    car.setWeight(String.valueOf(weight));
                }catch (Exception e) {
                    color = carData[2];
                    car.setColor(color);
                }
            }else if (carData.length == 4) {
                weight = Integer.parseInt(carData[2]);
                color = carData[3];
                car = new Car(model, engine, String.valueOf(weight), color);
            }
            carsCatalogue.add(car);
        }

        for (Car car : carsCatalogue) {
            System.out.print(car.toString());
        }


    }
}
