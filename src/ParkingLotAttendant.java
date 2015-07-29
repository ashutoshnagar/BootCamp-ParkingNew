import exceptions.CarNotParkedException;
import exceptions.ParkingFullException;
import model.*;

import java.util.*;

public class ParkingLotAttendant implements ParkingLotObserver {
    Map<Integer, ParkingLot> parkingLotMap = new HashMap<Integer, ParkingLot>();
    Map<Integer,Double> parkingLotStatusMap = new HashMap<>();

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.put(parkingLot.getParkingLotId(), parkingLot);
        parkingLotStatusMap.put(parkingLot.getParkingLotId(), 1.0);
    }

    public CarToken park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        System.out.println(parkingLotStatusMap);
        return  parkingLot.park(car);
    }

    public Car unPark(CarToken carToken) {

        ParkingLot parkingLot = parkingLotMap.get(carToken.getParkingLotId());
        if (parkingLot == null)
            throw new CarNotParkedException();
        return parkingLot.unPark(carToken.getCarToken());
    }


    public ParkingLot getAvailableParkingLot() {
        if (parkingLotStatusMap.isEmpty())
            throw new ParkingFullException();
            int maxCapacityParkingLot=0;
            double maxValue=0.0;
            for(Integer parkingLotId:parkingLotStatusMap.keySet())
            {
                if(maxValue<=parkingLotStatusMap.get(parkingLotId))
                    maxCapacityParkingLot=parkingLotId;
            }

        return parkingLotMap.get(maxCapacityParkingLot);

    }

    @Override
    public void notify(NotificationEvent notificationEvent) {
        double value=notificationEvent.getCurrentSize()/notificationEvent.getCAPACITY();
        if (EventType.CAR_PARKED == notificationEvent.getTYPE()) {
            if(value==1.0)
                parkingLotStatusMap.remove(new Integer(notificationEvent.getParkingLotId()));
            else
                parkingLotStatusMap.put(new Integer(notificationEvent.getParkingLotId()),value);
        } else {
            if(value==0.0)
            parkingLotStatusMap.put(new Integer(notificationEvent.getParkingLotId()), 1.0);

                parkingLotStatusMap.put(new Integer(notificationEvent.getParkingLotId()),value);


        }}
}
