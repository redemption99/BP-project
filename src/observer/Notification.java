package observer;

import observer.enums.NotificationCode;

public class Notification {
    private NotificationCode code;
    private Object data;

    public Notification(NotificationCode code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Notification(NotificationCode code) {
        this.code = code;
    }

    public Notification(Object data) {
        this.data = data;
    }

    public NotificationCode getCode() {
        return code;
    }

    public void setCode(NotificationCode code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
