package model;

public class TestFBIAgent implements ParkingLotObserver {
    public NotificationCode CODE;

    @Override
    public void notify(ObserverNotificationEvent notificationEvent) {
        this.CODE=notificationEvent.getCODE();
    }
}
