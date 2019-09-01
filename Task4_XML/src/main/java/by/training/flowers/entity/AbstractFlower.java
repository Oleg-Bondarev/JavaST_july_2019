package by.training.flowers.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Abstract flower class.
 * */
public class AbstractFlower {
    /**
     * Flower ID.
     * */
    private String identificator;
    /**
     * Flower name.
     * */
    private String flowerName;
    /**
     * Type of flower in medical using.
     * */
    private boolean isMedical;
    /**
     * Type of flower multiplying.
     * */
    private Multiplying multiplying;
    /**
     * Soil type.
     * */
    private Soil soil;
    /**
     * Steam color.
     * */
    private String steamColor;
    /**
     * Leaf color.
     * */
    private String leafColor;
    /**
     * Average flower size.
     * */
    private int avgSize;
    /**
     * Temperature.
     * */
    private int temperature;
    /**
     * Watering.
     * */
    private int watering;
    /**
     * Discovery year.
     * */
    private LocalDate discoveryYear;

    /**
     * Def. constructor.
     * */
    public AbstractFlower() { }
    /**
     * @return id.
     * */
    public String getIdentificator() {
        return identificator;
    }
    /**
     * @param id -id.
     * */
    public void setIdentificator(final String id) {
        this.identificator = id;
    }
    /**
     * @return name.
     * */
    public String getFlowerName() {
        return flowerName;
    }
    /**
     * @param name -namme.
     * */
    public void setFlowerName(final String name) {
        this.flowerName = name;
    }
    /**
     * @return bool.
     * */
    public boolean isMedical() {
        return isMedical;
    }
    /**
     * @param medical -bool.
     * */
    public void setMedical(final boolean medical) {
        isMedical = medical;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getSteamColor() {
        return steamColor;
    }

    public void setSteamColor(String steamColor) {
        this.steamColor = steamColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public int getAvgSize() {
        return avgSize;
    }

    public void setAvgSize(int avgSize) {
        this.avgSize = avgSize;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public LocalDate getDiscoveryYear() {
        return discoveryYear;
    }

    public void setDiscoveryYear(LocalDate discoveryYear) {
        this.discoveryYear = discoveryYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFlower that = (AbstractFlower) o;
        return isMedical == that.isMedical &&
                avgSize == that.avgSize &&
                temperature == that.temperature &&
                watering == that.watering &&
                identificator.equals(that.identificator) &&
                flowerName.equals(that.flowerName) &&
                multiplying.equals(that.multiplying) &&
                soil.equals(that.soil) &&
                steamColor.equals(that.steamColor) &&
                leafColor.equals(that.leafColor) &&
                discoveryYear.equals(that.discoveryYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificator, flowerName, isMedical, multiplying, soil, steamColor, leafColor, avgSize, temperature, watering, discoveryYear);
    }

    @Override
    public String toString() {
        return "AbstractFlower{" +
                "identificator='" + identificator + '\'' +
                ", flowerName='" + flowerName + '\'' +
                ", isMedical=" + isMedical +
                ", multiplying=" + multiplying +
                ", soil=" + soil +
                ", steamColor='" + steamColor + '\'' +
                ", leafColor='" + leafColor + '\'' +
                ", avgSize=" + avgSize +
                ", temperature=" + temperature +
                ", watering=" + watering +
                ", discoveryYear=" + discoveryYear +
                '}';
    }
}
