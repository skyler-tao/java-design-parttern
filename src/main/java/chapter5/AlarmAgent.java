package chapter5;

/**
 * Created by chaoqiang on 9/28/16.
 */
public class AlarmAgent {

    public void init() {
        System.out.println("Init AlarmAgent...");
    }

    public void sendAlarm(AlarmInfo alarm) {
        System.out.println("Sended AlarmInfo: " + alarm.toString());
    }
}
