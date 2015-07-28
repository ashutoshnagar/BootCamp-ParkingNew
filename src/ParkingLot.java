import exceptions.CarAlreadyParkedException;
import exceptions.CarNotParkedException;
import exceptions.ParkingFullException;
import model.*;

import java.util.*;

public class ParkingLot {
    private int token;
    private Map<Integer, Car> parkingSpace = new HashMap<Integer, Car>();
    private int CAPACITY = 2;
    private Map<ParkingLotObserver,SubscriptionStrategy> observers;

    public ParkingLot() {
    }

    public ParkingLot(TestParkingLotOwner owner,SubscriptionStrategy strategy) {
        this.observers = new HashMap<ParkingLotObserver,SubscriptionStrategy>();
        observers.put(owner,strategy);
    }

    public ParkingLot(TestParkingLotOwner owner,SubscriptionStrategy strategy, int capacity) {
        this.CAPACITY = capacity;
        this.observers = new HashMap<ParkingLotObserver,SubscriptionStrategy>();
        observers.put(owner,strategy);
    }

    public int park(Car car) {

        if (parkingSpace.containsValue(car))
            throw new CarAlreadyParkedException();
        if (isParkingFull())
            throw new ParkingFullException();

        parkingSpace.put(++token, car);
        notifyObservers(new NotificationEvent(EventType.CAR_PARKED, CAPACITY, parkingSpace.size()));

        return token;
    }

    public Car unPark(int token) {

        if (!parkingSpace.containsKey(token))
            throw new CarNotParkedException();


            notifyObservers(new NotificationEvent(EventType.CAR_UNPARKED,CAPACITY,parkingSpace.size()-1));


        return parkingSpace.remove(token);

    }

    public boolean isParkingFull() {
        return parkingSpace.size() == CAPACITY;
    }

    private void notifyObservers(NotificationEvent event) {

        for (ParkingLotObserver observer : observers.keySet()) {
            SubscriptionStrategy strategy = observers.get(observer);
            if (strategy.apply(event)) {
             if(event.getTYPE()==EventType.CAR_PARKED)
                observer.notify(NotificationCode.FULL);
             else
                 observer.notify(NotificationCode.VACANT);}
            }
        }



    public void register(ParkingLotObserver observer,SubscriptionStrategy strategy) {
        observers.put(observer,strategy);
    }

    public void unRegister(ParkingLotObserver observer) {
        observers.remove(observer);
    }
}
