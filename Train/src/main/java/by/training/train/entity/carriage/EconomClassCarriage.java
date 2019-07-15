package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;

public class EconomClassCarriage extends PassengerCarriage {
    /**Number of bays in the carrriage.*/
    private int countBays;
    /**Type of the economic class carriage.*/
    private EconomClassEnum economClassEnum;
    /**Default count of places in the carriage.*/
    public static final int DEFAULT_PLACES_IN_BAY = 6;
    /**Default count of the bays in the carriage.*/
    public static final int DEFAULT_COUNT_OF_BAYS = 9;
    /**Maximum baggage weighth(in kg).*/
    public static final double MAX_BAGGAGE_WEIGHTH = 40.5;

    /**Def constructor.*/
    public EconomClassCarriage() {
        super();
        countBays = DEFAULT_COUNT_OF_BAYS;
        economClassEnum = EconomClassEnum.COMMON;
    }
    /**@param newBrigade - count of the carriage stuff.
     * @param newBaggage - allowed weight of baggage per person(in kg)
     * @param newService - type of the service in carriage.
     * @param hasConditioner - conditioner in the carriage.
     * @param newBay - count of bays in the train.
     * @param newEconomClassEnum - type of the economic class carriage.*/
    public EconomClassCarriage(final int newBrigade,
            final ServiceEnum newService, final double newBaggage,
            final boolean hasConditioner, final int newBay,
            final EconomClassEnum newEconomClassEnum) {
        super(newBrigade, newBay * DEFAULT_PLACES_IN_BAY,
                newBaggage, hasConditioner, newService);
        this.countBays = newBay;
        this.economClassEnum = newEconomClassEnum;
    }
    /**@param econClassCarriageNew -*/
    public EconomClassCarriage(final EconomClassCarriage econClassCarriageNew) {
        super(econClassCarriageNew.getBrigade(),
                econClassCarriageNew.getCountOfPassengers(),
                econClassCarriageNew.getBagageOnPassenger(),
                econClassCarriageNew.isConditioner(),
                econClassCarriageNew.getServiceEnum());
        this.countBays = econClassCarriageNew.countBays;
        this.economClassEnum = econClassCarriageNew.economClassEnum;
    }
    /**@return count of bays.*/
    public int getCountBays() {
        return countBays;
    }
    /**@param bays -*/
    public void setCountBays(final int bays) {
        this.countBays = bays;
    }
    /**@return type of the econom-class carriage.*/
    public EconomClassEnum getEconomClassEnum() {
        return economClassEnum;
    }
    /**@param economEnum -*/
    public void setEconomClassEnum(final EconomClassEnum economEnum) {
        this.economClassEnum = economEnum;
    }

    /**
     * @param o - The reference object with which to compare.
     * @return result of the method.
     * */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        EconomClassCarriage that = (EconomClassCarriage) o;
        return countBays == that.countBays
                && economClassEnum == that.economClassEnum;
    }
    /**@return hashCode*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countBays, economClassEnum);
    }
    /**@return string.*/
    @Override
    public String toString() {
        return "EconomClassCarriage{"
                + "countBays=" + countBays
                + ", economClassEnum=" + economClassEnum
                + '}';
    }
}
