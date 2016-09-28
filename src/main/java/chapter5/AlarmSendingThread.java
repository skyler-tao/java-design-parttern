package chapter5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chaoqiang on 9/28/16.
 */
public class AlarmSendingThread extends AbstractTerminatableThread {
    private final AlarmAgent alarmAgent = new AlarmAgent();
    private final BlockingQueue<AlarmInfo> alarmQueue;
    private final ConcurrentMap<String, AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread() {
        alarmQueue = new ArrayBlockingQueue<AlarmInfo>(100);
        submittedAlarmRegistry = new ConcurrentHashMap<String, AtomicInteger>();
        alarmAgent.init();
    }

    protected void doRun() throws Exception {
        AlarmInfo alarm;
        alarm = alarmQueue.take();

    }
    public void terminate() {

    }

    public int sendAlarm(AlarmInfo alarm) {
        return 0;
    }
}
