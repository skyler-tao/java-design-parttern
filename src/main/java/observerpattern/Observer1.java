package observerpattern;

/**
 * Created by chaoqiang on 3/2/17.
 */
public class Observer1 implements Observer {
    @Override
    public void update() {
        System.out.println("Observer1 updated!");
    }
}
