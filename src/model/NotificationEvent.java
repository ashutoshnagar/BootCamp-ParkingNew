package model;

public class NotificationEvent {
    private EventType TYPE;
    private int CAPACITY;
    private  int currentSize;

    public NotificationEvent(EventType TYPE, int CAPACITY, int currentSize) {
        this.TYPE = TYPE;
        this.CAPACITY = CAPACITY;
        this.currentSize = currentSize;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public EventType getTYPE() {
        return TYPE;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}
