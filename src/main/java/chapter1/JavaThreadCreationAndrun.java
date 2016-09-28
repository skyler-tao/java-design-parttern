package chapter1;

/**
 * Created by chaoqiang on 9/27/16.
 */
public class JavaThreadCreationAndrun {
    public static void main(String[] args) {
        System.out.println("The main method was executed by thread: "
        + Thread.currentThread().getName());
        Helper helper = new Helper("Java Thread Anywhere");

        Thread thread = new Thread(helper);
        thread.setName("A-Worker-thread");
        thread.start();
    }

    static class Helper implements Runnable {
        private final String message;

        public Helper(String message) {
            this.message = message;
        }

        public void run() {
            doSomething(message);
        }

        private void doSomething(String message) {
            System.out.println("The doSomething method was executed by thread: "
            + Thread.currentThread().getName());
            System.out.println("Do something with " + message);
        }
    }
}
