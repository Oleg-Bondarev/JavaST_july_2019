package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.CargoCarriageType;
import by.training.train.entity.enums.CargoHazardType;
import by.training.train.entity.enums.CarriageType;

public class CargoCarriage extends RailwayCarriage {
    /***/
    private CargoHazardType cargoHazardType;
    /***/
    private CargoCarriageType cargoCarriageType;
    /**Maximum payload of the carriage in tons.*/
    private double maximumCargoWeighth;
    private double defaultMaxCargoWeighth = 27.5;

    public CargoCarriage() {
        super();
        cargoHazardType = CargoHazardType.NINTH_LEVEL;
        cargoCarriageType = CargoCarriageType.OPEN;
        maximumCargoWeighth = defaultMaxCargoWeighth;
    }
    public CargoCarriage(final CarriageType carrType,
            final CargoHazardType hazType, final CargoCarriageType cargoCarrType,
            final double maxWeighth) {
        super(carrType);
        this.cargoHazardType = hazType;
        this.cargoCarriageType = cargoCarrType;
        this.maximumCargoWeighth = maxWeighth;
    }

    public CargoHazardType getCargoHazardType() {
        return cargoHazardType;
    }

    public void setCargoHazardType(
            CargoHazardType cargoHazardType) {
        this.cargoHazardType = cargoHazardType;
    }

    public CargoCarriageType getCargoCarriageType() {
        return cargoCarriageType;
    }

    public void setCargoCarriageType(
            CargoCarriageType cargoCarriageType) {
        this.cargoCarriageType = cargoCarriageType;
    }

    public double getMaximumCargoWeighth() {
        return maximumCargoWeighth;
    }

    public void setMaximumCargoWeighth(double maximumCargoWeighth) {
        this.maximumCargoWeighth = maximumCargoWeighth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CargoCarriage that = (CargoCarriage) o;
        return Double.compare(that.maximumCargoWeighth, maximumCargoWeighth) == 0
                && Double.compare(that.defaultMaxCargoWeighth,
                        defaultMaxCargoWeighth) == 0
                && cargoHazardType == that.cargoHazardType
                && cargoCarriageType == that.cargoCarriageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargoHazardType, cargoCarriageType,
                        maximumCargoWeighth, defaultMaxCargoWeighth);
    }

    @Override
    public String toString() {
        return "CargoCarriage{"
                + "cargoHazardType=" + cargoHazardType
                + ", cargoCarriageType=" + cargoCarriageType
                + ", maximumCargoWeighth=" + maximumCargoWeighth
                + ", defaultMaxCargoWeighth=" + defaultMaxCargoWeighth
                + '}';
    }
}
