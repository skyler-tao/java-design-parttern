package chapter5;

/**
 * Created by chaoqiang on 9/28/16.
 */
public class AlarmInfo {
    private String id;
    private AlarmType type;
    private String extraInfo;

    public AlarmInfo(String id, AlarmType type) {
        this.id = id;
        this.type = type;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "AlarmInfo{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }
}
