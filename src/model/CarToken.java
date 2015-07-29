package model;

public class CarToken {
    private int parkingLotId;
    private int carToken;

    public CarToken(int parkingLotId, int carToken) {
        this.parkingLotId = parkingLotId;
        this.carToken = carToken;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public int getCarToken() {
        return carToken;
    }
}
