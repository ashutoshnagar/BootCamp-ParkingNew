package model;

public class ObserverNotificationEvent {
    private NotificationCode CODE;
    private int parkingLotId;
    public ObserverNotificationEvent(NotificationCode CODE, int parkingLotId)
    {this.CODE=CODE;
        this.parkingLotId=parkingLotId;}

    public int getParkingLotId() {
        return parkingLotId;
    }

    public NotificationCode getCODE() {
        return CODE;
    }
}
