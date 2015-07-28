package model;

import model.ParkingLotOwner;

public class TestParkingLotOwner implements ParkingLotObserver {
    public NotificationCode CODE;
    @Override
    public void takeActtion(NotificationCode CODE) {
        this.CODE=CODE;
    }
}
