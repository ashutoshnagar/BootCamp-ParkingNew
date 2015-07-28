package model;

import model.FBIAgent;

public class TestFBIAgent implements ParkingLotObserver {
    public NotificationCode CODE;
    @Override
    public void notify(NotificationCode CODE) {
        System.out.println("jhandu balm 60");

        this.CODE=CODE;
    }
}
