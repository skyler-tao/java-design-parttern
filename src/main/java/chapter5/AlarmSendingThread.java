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
        alarmQueue = new ArrayBlockingQueue<>(100);
        submittedAlarmRegistry = new ConcurrentHashMap<>();
        alarmAgent.init();
    }

    protected void doRun() throws Exception {
        AlarmInfo alarm;
        alarm = alarmQueue.take();
        terminationToken.reservations.decrementAndGet();

        try {
            alarmAgent.sendAlarm(alarm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (AlarmType.RESUME == alarm.type) {
            String key = AlarmType.FAULT.toString() + ':' + alarm.id + '@' + alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);

            key = AlarmType.RESUME.toString() + ':' + alarm.id + '@' + alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);
        }
    }

    public void terminate() {

    }

    public int sendAlarm(final AlarmInfo alarm) {
        AlarmType type = alarm.type;
        String id = alarm.id;
        String extraInfo = alarm.getExtraInfo();

        if (terminationToken.isToShutdown()) {
            System.err.println("rejected alarm: " + id + "," + extraInfo);
            return -1;
        }

        int duplicateSubmissionCount = 0;
        try {
            AtomicInteger prevSubmittedCounter;
            prevSubmittedCounter = submittedAlarmRegistry.putIfAbsent(type.toString() + ':' + id + '@' + extraInfo, new AtomicInteger(0));

            if (null == prevSubmittedCounter) {
                terminationToken.reservations.incrementAndGet();
                alarmQueue.put(alarm);
            } else {
                duplicateSubmissionCount = prevSubmittedCounter.incrementAndGet();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    protected void doCleanUp(Exception exp) {
        if (null != exp && !(exp instanceof InterruptedException)) {
            exp.printStackTrace();
        }
        alarmAgent.disconnect();
    }
}
