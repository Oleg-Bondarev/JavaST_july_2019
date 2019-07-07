package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.CarriageType;
import by.training.train.entity.enums.PassengerCarriageType;

public class PassengerCarriage extends RailwayCarriage {
    /***/
    private PassengerCarriageType passengerCarriageType;
    /***/
    private int countPassengersInCarriage;
    /**Luggage weight per person in kg.*/
    private double luggageWeight;
    /***/
    private final static int defaultCountPassengersInCommonCarr = 81;
    private final static int defaultWeight = 5;

    public PassengerCarriage() {
        super();
        passengerCarriageType = PassengerCarriageType.O;
        countPassengersInCarriage = defaultCountPassengersInCommonCarr;
        luggageWeight = defaultWeight;
    }

    public PassengerCarriage(final CarriageType carriageType,
                final PassengerCarriageType passengerCarrType,
                final int countPassengersInCarr, final double lugWeight) {
        super(carriageType);
        this.passengerCarriageType = passengerCarrType;
        this.countPassengersInCarriage = countPassengersInCarr;
        this.luggageWeight = lugWeight;
    }

    public PassengerCarriageType getPassengerCarriageType() {
        return passengerCarriageType;
    }

    public void setPassengerCarriageType(
            PassengerCarriageType passengerCarriageType) {
        this.passengerCarriageType = passengerCarriageType;
    }

    public int getCountPassengersInCarriage() {
        return countPassengersInCarriage;
    }

    public void setCountPassengersInCarriage(int countPassengersInCarriage) {
        this.countPassengersInCarriage = countPassengersInCarriage;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(double luggageWeight) {
        this.luggageWeight = luggageWeight;
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
        PassengerCarriage that = (PassengerCarriage) o;
        return countPassengersInCarriage == that.countPassengersInCarriage
                && Double.compare(that.luggageWeight, luggageWeight) == 0
                && passengerCarriageType == that.passengerCarriageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerCarriageType,
                countPassengersInCarriage, luggageWeight,
                defaultCountPassengersInCommonCarr, defaultWeight);
    }

    @Override
    public String toString() {
        return "PassengerCarriage{"
                + "passengerCarriageType=" + passengerCarriageType
                + ", countPassengersInCarriage=" + countPassengersInCarriage
                + ", luggageWeight=" + luggageWeight
                + ", defaultCountPassengersInCommonCarr="
                + defaultCountPassengersInCommonCarr
                + ", defaultWeight=" + defaultWeight
                + '}';
    }
}
