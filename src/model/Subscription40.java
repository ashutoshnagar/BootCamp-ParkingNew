package model;

public class Subscription40 implements SubscriptionStrategy {
   private boolean flagPark =true;
    private boolean flagUnPark=true;
    @Override
    public boolean apply(NotificationEvent event) {

    int currentSize=event.getCurrentSize();
        int capacity=event.getCAPACITY();


     double value=(double)currentSize/(double)capacity;

        if(event.getTYPE()==EventType.CAR_PARKED)
            return forPark(value);
        else
            return forUnPark(value);

    }
    private boolean forPark(double value)
    {
    flagUnPark=true;
        if(value>=.4)
        {
            if(flagPark) {
                flagPark =false;
                return true;

            }}
        return false;
    }
    private boolean forUnPark(double value){
        flagPark=true;
        if(value<=.4)
        {
            if(flagUnPark) {
                flagUnPark=false;
                return true;

            }}
        return false;


    }
}
