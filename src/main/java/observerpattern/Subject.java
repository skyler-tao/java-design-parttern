package observerpattern;

/**
 * Created by chaoqiang on 3/2/17.
 */
public interface Subject {
    void registerObserver();
    void removeObserver();
    void notifyObservers();
}
