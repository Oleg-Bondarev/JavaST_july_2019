package by.training.train.observer;

public interface Observer<T> {
    /**
     * @param event - object.
     * */
    void handleEvent(T event);
}
