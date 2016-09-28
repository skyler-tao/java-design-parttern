package chapter5;

/**
 * Created by chaoqiang on 9/28/16.
 */
public abstract class AbstractTerminatableThread extends Thread implements Terminatable {
    public final TerminationToken terminationToken;

    protected AbstractTerminatableThread() {
        terminationToken = new TerminationToken();
    }
}
