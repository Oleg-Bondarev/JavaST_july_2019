package by.training.train.entity.carriage;

public class RestaurantCarriage extends SpecialCarriage {
    /**Count of siting places.*/
    private int sitingPlaces;
    /***/
    private static final int DEFAULT_PLACES = 50;
    /**def constructor.*/
    public RestaurantCarriage() {
        super();
        this.sitingPlaces = DEFAULT_PLACES;
    }
    /**@param brigade -
     * @param newSitingPlaces -*/
    public RestaurantCarriage(final int brigade, final int newSitingPlaces) {
        super(brigade);
        this.sitingPlaces = newSitingPlaces;
    }
    /**@return places in the carriage.*/
    public int getSitingPlaces() {
        return sitingPlaces;
    }
    /**@param newPlaces -*/
    public void setSitingPlaces(final int newPlaces) {
        this.sitingPlaces = newPlaces;
    }
}
