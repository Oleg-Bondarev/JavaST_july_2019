package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.CarriageType;

public abstract class RailwayCarriage {
    /***/
    private CarriageType carriageType;

    /***/
    //можно ли так сделать?
    public RailwayCarriage() {
        carriageType = CarriageType.PASSENGER;
    }
    /**@param type -*/
    public RailwayCarriage(final CarriageType type) {
        this.carriageType = type;
    }

    /**@return type of the carriage*/
    public CarriageType getCarriageType() {
        return carriageType;
    }
    /**@param carrType -*/
    public void setCarriageType(final CarriageType carrType) {
        this.carriageType = carrType;
    }

    /***/
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RailwayCarriage that = (RailwayCarriage) o;
        return carriageType == that.carriageType;
    }
    /***/
    @Override
    public int hashCode() {
        return Objects.hash(carriageType);
    }
    /***/
    @Override
    public String toString() {
        return "RailwayCarriage{"
                + "carriageType=" + carriageType
                + '}';
    }
}
