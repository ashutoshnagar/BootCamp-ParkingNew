import exceptions.CarAlreadyParkedException;
import exceptions.CarNotParkedException;
import exceptions.ParkingFullException;
import model.*;
import org.junit.Before;

import static org.junit.Assert.*;

public class ParkingLotTest {


    private ParkingLot parkingLot;
    private TestParkingLotOwner testParkingLotOwner;


    @Before
    public void setUp() {
        testParkingLotOwner = new TestParkingLotOwner();
        parkingLot = new ParkingLot(testParkingLotOwner,new Subscription40(),5);

        parkingLot.park(new Car(1212));

    }

    @org.junit.Test
    public void testParkWithSpace() throws Exception {
        assertEquals(2, parkingLot.park(new Car(1213)));

    }


    @org.junit.Test(expected = ParkingFullException.class)
    public void testParkWithParkingFullException() throws Exception {

        parkingLot.park(new Car(1213));
        parkingLot.park(new Car(1215));
        parkingLot.park(new Car(1216));
        parkingLot.park(new Car(1217));
        assertEquals(6, parkingLot.park(new Car(1214)));
    }

    @org.junit.Test(expected = CarAlreadyParkedException.class)
    public void testParkWithCarAlreadyParkedException() throws Exception {

        parkingLot.park(new Car(1212));

    }

    @org.junit.Test
    public void testUnPark() throws Exception {
        assertEquals(new Car(1212), parkingLot.unPark(1));

    }

    @org.junit.Test(expected = CarNotParkedException.class)
    public void testUnParkWithCarNotParkedException() throws Exception {
        parkingLot.unPark(12134);
    }


/*

    @org.junit.Test
    public void testFBIAgentNotifiedOnParkingFull()


    {
        TestFBIAgent agent1 = new TestFBIAgent();
        TestFBIAgent agent2 = new TestFBIAgent();
        TestFBIAgent agent3 = new TestFBIAgent();

        parkingLot.register(agent1);
        parkingLot.register(agent2);
        parkingLot.register(agent3);
        parkingLot.park(new Car(1213));

        assertEquals(NotificationCode.FULL,testParkingLotOwner.CODE);
        assertEquals(NotificationCode.FULL, agent1.CODE);
        assertEquals(NotificationCode.FULL, agent2.CODE);
        assertEquals(NotificationCode.FULL, agent3.CODE);
    }


    @org.junit.Test
    public void testFBIAgentNotifiedOnNoMoreParkingFull()

    {
        TestFBIAgent agent1 = new TestFBIAgent();
        TestFBIAgent agent2 = new TestFBIAgent();
        TestFBIAgent agent3 = new TestFBIAgent();

        parkingLot.register(agent1);
        parkingLot.register(agent2);
        parkingLot.register(agent3);


        parkingLot.park(new Car(1213));
        parkingLot.unPark(2);

        assertEquals(NotificationCode.VACANT,testParkingLotOwner.CODE);
        assertEquals(NotificationCode.VACANT, agent1.CODE);
        assertEquals(NotificationCode.VACANT, agent2.CODE);
        assertEquals(NotificationCode.VACANT, agent3.CODE);
    }
*/

    @org.junit.Test
public void testNotifyParkwithNotificationEvent(){
        TestFBIAgent agent1 = new TestFBIAgent();
        TestFBIAgent agent2 = new TestFBIAgent();
        TestFBIAgent agent3 = new TestFBIAgent();

        parkingLot.register(agent1, new Subscription60());
        parkingLot.register(agent2,new Subscription40());
        parkingLot.register(agent3,new Subscription60());


        parkingLot.park(new Car(1213));

        assertEquals(NotificationCode.FULL, agent2.CODE);

        assertEquals(NotificationCode.FULL, testParkingLotOwner.CODE);

        parkingLot.park(new Car(1214));
        assertEquals(NotificationCode.FULL, agent1.CODE);
        assertEquals(NotificationCode.FULL, agent3.CODE);

    }

    @org.junit.Test
    public void testNotifyUnParkwithNotificationEvent(){
        TestFBIAgent agent1 = new TestFBIAgent();
        TestFBIAgent agent2 = new TestFBIAgent();
        TestFBIAgent agent3 = new TestFBIAgent();

        parkingLot.register(agent1, new Subscription60());
        parkingLot.register(agent2, new Subscription40());
        parkingLot.register(agent3, new Subscription60());

        parkingLot.park(new Car(1213));
        parkingLot.park(new Car(1214));

        parkingLot.park(new Car(1215));


        parkingLot.unPark(3);
        assertEquals(NotificationCode.VACANT, agent1.CODE);
        assertEquals(NotificationCode.VACANT, agent3.CODE);

        parkingLot.unPark(2);
        assertEquals(NotificationCode.VACANT, testParkingLotOwner.CODE);
        assertEquals(NotificationCode.VACANT, agent2.CODE);

    }

}