import exceptions.CarAlreadyParkedException;
import exceptions.CarNotParkedException;
import exceptions.ParkingFullException;
import model.*;

import java.util.*;

public class ParkingLot {
    private int parkingLotId;
    private int token;
    private Map<Integer, Car> parkingSpace = new HashMap<Integer, Car>();
    private int CAPACITY = 2;
    private Map<ParkingLotObserver,SubscriptionStrategy> observers;
    private ParkingLotAttendant attendant;
    public int getParkingLotId() {
        return parkingLotId;
    }

    public ParkingLot() {
    }

    public ParkingLot(TestParkingLotOwner owner,SubscriptionStrategy strategy) {
        this.observers = new HashMap<ParkingLotObserver,SubscriptionStrategy>();
        observers.put(owner,strategy);
    }

    public ParkingLot(TestParkingLotOwner owner,SubscriptionStrategy strategy, int capacity,int parkingLotId,ParkingLotAttendant attendant) {
        this.CAPACITY = capacity;
        this.observers = new HashMap<ParkingLotObserver,SubscriptionStrategy>();
        observers.put(owner,strategy);
        this.parkingLotId = parkingLotId;
        this.attendant=attendant;
    }

    public  CarToken park(Car car) {

        if (parkingSpace.containsValue(car))
            throw new CarAlreadyParkedException();
        if (isParkingFull())
            throw new ParkingFullException();

        parkingSpace.put(++token, car);
        notifyObservers(new NotificationEvent(parkingLotId,EventType.CAR_PARKED, CAPACITY, parkingSpace.size()));

        return new CarToken(parkingLotId,token);
    }

    public Car unPark(int token) {

        if (!parkingSpace.containsKey(token))
            throw new CarNotParkedException();

            notifyObservers(new NotificationEvent(parkingLotId,EventType.CAR_UNPARKED,CAPACITY,parkingSpace.size()-1));

        return parkingSpace.remove(token);

    }

    public boolean isParkingFull() {
        return parkingSpace.size() == CAPACITY;
    }

    private void notifyObservers(NotificationEvent event) {
        attendant.notify(event);
        for (ParkingLotObserver observer : observers.keySet()) {
            SubscriptionStrategy strategy = observers.get(observer);
            if (strategy.apply(event)) {
                if (event.getTYPE() == EventType.CAR_PARKED)
                        observer.notify(new NotificationEvent(parkingLotId,EventType.FULL,0,0));
                 else  if (event.getTYPE() == EventType.CAR_UNPARKED)
                       observer.notify(new NotificationEvent(parkingLotId,EventType.VACANT,0,0));

            }}
        }

    public void register(ParkingLotObserver observer,SubscriptionStrategy strategy) {
        observers.put(observer,strategy);
    }

    public void unRegister(ParkingLotObserver observer) {
        observers.remove(observer);
    }
}
