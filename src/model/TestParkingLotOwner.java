package model;

import model.ParkingLotOwner;

public class TestParkingLotOwner implements ParkingLotObserver {
    public NotificationCode CODE;
    @Override
    public void notify(NotificationCode CODE) {
        System.out.println("jhandu balm 40");
        this.CODE=CODE;
    }
}
