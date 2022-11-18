package lessons.comparableAndComparator;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Car[] cars = {
            new Car("BMW", 240, "****"),
            new Car("Peugeot", 200, "*****"),
            new Car("Volkswagen", 210, "**"),
            new Car("Mercedes-Benz", 220, "***"),
            new Car("Opel", 180, "*")
        };

        // Comparable
        TreeSet<Car> cars01 = new TreeSet<Car>();
        cars01.addAll(List.of(cars));

        printCars("\n——= Comparable TreeSet =——", cars01);

        // Comparator

        ArrayList<Car> cars02 = new ArrayList<>();
        cars02.addAll(List.of(cars));

        cars02.sort(new CarByBrandComparator());
        printCars("\n——= Comparator Array sort by brand =——", cars02);

        cars02.sort(new CarByMaxSpeedComparator());
        printCars("\n——= Comparator Array sort by maximal speed =——", cars02);

        cars02.sort(new CarByCuteComparator());
        printCars("\n——= Comparator Array sort by cute =——", cars02);

        TreeSet<Car> cars03 = new TreeSet<Car>(new CarByCuteComparator());
        cars03.addAll(List.of(cars));

        printCars("\n——= Comparator TreeSet =——", cars03);
    }

    static void printCars(String header, AbstractCollection<Car> cars) {
        System.out.println(header);
        for (Car car: cars) {
            System.out.printf(
                    "Brand: %s. Max. speed: %d km/h. Cute: %s.\n",
                    car.getBrand(), car.getMaxSpeed(), car.getCute()
            );
        }
    }
}

class Car implements Comparable<Car> {
    private String brand;
    private int maxSpeed;
    private String cute;

    Car(String brand, int maxSpeed, String cute) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        this.cute = cute;
    }

    String getBrand() {
        return brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getCute() {
        return cute;
    }

    public int compareTo(Car c) {
        return brand.compareTo(c.getBrand());
//        return c.getMaxSpeed() - maxSpeed;
//        return c.getCute().length() - cute.length();
    }
}

class CarByBrandComparator implements Comparator<Car> {
    // Compare by brand (increase)
    public int compare(Car carA, Car carB) {
        return carA.getBrand().compareTo(carB.getBrand());
    }
}

class CarByMaxSpeedComparator implements Comparator<Car> {
    // Compare by maximal speed (decrease)
    public int compare(Car carA, Car carB) {
        int mSpeedA = carA.getMaxSpeed();
        int mSpeedB = carB.getMaxSpeed();
        return mSpeedA < mSpeedB ? 1 : mSpeedA > mSpeedB ? -1 :0;
    }
}

class CarByCuteComparator implements Comparator<Car> {
    // Compare by stars of cute (decrease)
    public int compare(Car carA, Car carB) {
        int cuteQtyA = carA.getCute().length();
        int cuteQtyB = carB.getCute().length();

        if (cuteQtyA < cuteQtyB ) { return 1; }
        if (cuteQtyA > cuteQtyB) { return -1; }
        return 0;
    }
}
