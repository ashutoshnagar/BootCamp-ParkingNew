import exceptions.CarNotParkedException;
import exceptions.ParkingFullException;
import model.Car;
import model.NotificationCode;
import model.ObserverNotificationEvent;
import model.ParkingLotObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotAttendant implements ParkingLotObserver {
    Map<Integer, ParkingLot> parkingLotMap = new HashMap<Integer, ParkingLot>();
    List<Integer> parkingLotStausList = new ArrayList<Integer>();

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.put(parkingLot.getParkingLotId(), parkingLot);
        parkingLotStausList.add(parkingLot.getParkingLotId());
    }

    public String park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        int carToken = parkingLot.park(car);
        String token = "PL" + parkingLot.getParkingLotId() + "T" + carToken;
        System.out.println(token);
        return token;
    }

    public Car unPark(String token) {
        int parkingLotId;
        int carToken;
        try {
            parkingLotId = Integer.parseInt(token.substring(2, token.indexOf('T')));
            carToken = Integer.parseInt(token.substring(token.indexOf('T') + 1, token.length()));

        } catch (Exception exception) {
            throw new CarNotParkedException();
        }
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new CarNotParkedException();
        return parkingLot.unPark(carToken);
    }


    public ParkingLot getAvailableParkingLot() {
        if (parkingLotStausList.isEmpty())
            throw new ParkingFullException();
        System.out.println(parkingLotStausList);
        return parkingLotMap.get(parkingLotStausList.get(0));

    }

    @Override
    public void notify(ObserverNotificationEvent notificationEvent) {
        if (NotificationCode.FULL == notificationEvent.getCODE()) {
            parkingLotStausList.remove(new Integer(notificationEvent.getParkingLotId()));
        } else
            parkingLotStausList.add(new Integer(notificationEvent.getParkingLotId()));
    }
}
