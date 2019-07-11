package by.training.demo_functional_interface;

import java.util.Objects;

public class Car {
    private String name;
    private boolean isFullDrive;
    private boolean isGasEngine;

    public Car(String nameNew, boolean isFullDriveNew, boolean isGasEngineNew) {
        name = nameNew;
        isFullDrive = isFullDriveNew;
        isGasEngine = isGasEngineNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameNew) {
        name = nameNew;
    }

    public boolean isFullDrive() {
        return isFullDrive;
    }

    public void setFullDrive(boolean fullDriveNew) {
        isFullDrive = fullDriveNew;
    }

    public boolean isGasEngine() {
        return isGasEngine;
    }

    public void setGasEngine(boolean gasEngineNew) {
        isGasEngine = gasEngineNew;
    }

    @Override
    public boolean equals(Object oNew) {
        if (this == oNew)
            return true;
        if (oNew == null || getClass() != oNew.getClass())
            return false;
        final Car car = (Car) oNew;
        return isFullDrive == car.isFullDrive &&
                isGasEngine == car.isGasEngine &&
                name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isFullDrive, isGasEngine);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", isFullDrive=" + isFullDrive +
                ", isGasEngine=" + isGasEngine +
                '}';
    }
}
