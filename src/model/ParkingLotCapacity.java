package model;

public class ParkingLotCapacity implements Comparable {
    private double currentSize;
    private int parkingLotId;
    
    public ParkingLotCapacity(int parkingLotId,double currentSize){
        this.parkingLotId=parkingLotId;
        this.currentSize=currentSize;
        
    }

    @Override
    public int compareTo(Object o) {
        ParkingLotCapacity lotCapacity=(ParkingLotCapacity)o;
        if (parkingLotId==lotCapacity.parkingLotId)
            return 0;
        
        if(        lotCapacity.currentSize>=currentSize)
            return 1;
        else
            return -1;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }
}
