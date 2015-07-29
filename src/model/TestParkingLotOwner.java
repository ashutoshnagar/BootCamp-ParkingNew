package model;

public class TestParkingLotOwner implements ParkingLotObserver {
    public NotificationCode CODE;
    @Override
    public void notify(ObserverNotificationEvent notificationEvent) {
        this.CODE=notificationEvent.getCODE();
    }
}
