package chapter1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by chaoqiang on 9/10/16.
 */
public class WorkerThread {
    public static void main(String[] args) {
        Helper helper = new Helper();
        helper.init();

        helper.submit("Something...");
        helper.submit("Another thing...");
    }

    private static class Helper {
        private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);

        private final Thread workerThread = new Thread() {
            @Override
            public void run() {
                String task;
                while(true) {
                    try {
                        task = workQueue.take();
                    } catch (InterruptedException e) {
                        break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };

        private String doProcess(String task) {
            return task + " -> processed.";
        }

        public void init() {
            workerThread.start();
        }

        public void submit(String task) {
            try {
                workQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
