package by.training.demo_functional_interface;

public class Runer {
    public static void main(String[] args) {
        Car audiA3 = new Car("AudiA3", true, true);
        Car audiA6 = new Car("AudiA6", true, false);

        CheckCar checker = new CheckCar() {
            @Override
            public boolean test(Car car) {
                return car.isFullDrive();
            }
        };

        /*printTest(audiA3, checker);
        printTest(audiA6, checker);
        */
        printTest(audiA3, car -> car.isFullDrive());
        printTest(audiA3, Car::isFullDrive);
    }

    private static void printTest(Car car, CheckCar checker) {
        if (checker.test(car)) {
            System.out.println(car);
        }
    }
}
