package model;

import model.FBIAgent;

public class TestFBIAgent implements ParkingLotObserver {
    public NotificationCode CODE;
    @Override
    public void notify(NotificationCode CODE) {


        this.CODE=CODE;
    }
}
