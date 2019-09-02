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
    private String temperature;
    /**
     * Watering.
     * */
    private int watering;
    /**
     * Lighting.
     * */
    private boolean lighting;
    /**
     * Discovery year.
     * */
    private LocalDate discoveryYear;

    /**
     * Default constructor.
     * */
    public AbstractFlower() { }
    /**
     * @return boolean.
     * */
    public boolean getLightning() {
        return lighting;
    }
    /**
     * @param light -bool.
     * */
    public void setLighting(final boolean light) {
        this.lighting = light;
    }
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
    /**
     * @return mult.
     * */
    public Multiplying getMultiplying() {
        return multiplying;
    }
    /**
     * @param mult -mult.
     * */
    public void setMultiplying(final Multiplying mult) {
        this.multiplying = mult;
    }
    /**
     * @return soil.
     * */
    public Soil getSoil() {
        return soil;
    }
    /**
     * @param newsoil -soil.
     * */
    public void setSoil(final Soil newsoil) {
        this.soil = newsoil;
    }
    /**
     * @return color.
     * */
    public String getSteamColor() {
        return steamColor;
    }
    /**
     * @param color -color.
     * */
    public void setSteamColor(final String color) {
        this.steamColor = color;
    }
    /**
     * @return color.
     * */
    public String getLeafColor() {
        return leafColor;
    }
    /**
     * @param color -color.
     * */
    public void setLeafColor(final String color) {
        this.leafColor = color;
    }
    /**
     * @return size.
     * */
    public int getAvgSize() {
        return avgSize;
    }
    /**
     * @param size -size.
     * */
    public void setAvgSize(final int size) {
        this.avgSize = size;
    }
    /**
     * @return temperature.
     * */
    public String getTemperature() {
        return temperature;
    }
    /**
     * @param temp -temp.
     * */
    public void setTemperature(final String temp) {
        this.temperature = temp;
    }
    /**
     * @return watering.
     * */
    public int getWatering() {
        return watering;
    }
    /**
     * @param wat -watering.
     * */
    public void setWatering(final int wat) {
        this.watering = wat;
    }
    /**
     * @return date.
     * */
    public LocalDate getDiscoveryYear() {
        return discoveryYear;
    }
    /**
     * @param year -year.
     * */
    public void setDiscoveryYear(final LocalDate year) {
        this.discoveryYear = year;
    }
    /**
     * @param o -obj.
     * @return bool.
     * */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractFlower that = (AbstractFlower) o;
        return isMedical == that.isMedical && avgSize == that.avgSize
                && temperature == that.temperature && watering == that.watering
                && identificator.equals(that.identificator)
                && flowerName.equals(that.flowerName)
                && multiplying.equals(that.multiplying)
                && soil.equals(that.soil) && steamColor.equals(that.steamColor)
                && leafColor.equals(that.leafColor)
                && discoveryYear.equals(that.discoveryYear);
    }
    /**
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(identificator, flowerName, isMedical, multiplying,
                soil, steamColor, leafColor, avgSize, temperature, watering,
                discoveryYear);
    }
    /**
     * @return string.
     * */
    @Override
    public String toString() {
        return "AbstractFlower{"
                + "identificator='" + identificator + '\'' + ", flowerName='"
                + flowerName + '\'' + ", isMedical=" + isMedical
                + ", multiplying=" + multiplying + ", soil=" + soil
                + ", steamColor='" + steamColor + '\'' + ", leafColor='"
                + leafColor + '\'' + ", avgSize=" + avgSize + ", temperature="
                + temperature + ", watering=" + watering + ", discoveryYear="
                + discoveryYear + '}';
    }
}
