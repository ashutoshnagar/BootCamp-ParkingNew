package model;

public class NotificationEvent {
    private int parkingLotId;
    private EventType TYPE;
    private int CAPACITY;
    private  int currentSize;

    public NotificationEvent(int parkingLotId,EventType TYPE, int CAPACITY, int currentSize) {
        this.TYPE = TYPE;
        this.parkingLotId=parkingLotId;
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

    public int getParkingLotId() {
        return parkingLotId;
    }
}
