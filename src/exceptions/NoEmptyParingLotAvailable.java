package exceptions;

public class NoEmptyParingLotAvailable extends RuntimeException {
    public NoEmptyParingLotAvailable(){
        super("All Parking Lots are full");
    }
}
