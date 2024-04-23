package lab9;

import java.text.DecimalFormat;

public class ParkingLot {
    public static final double CLOSED_THRESHOLD = 80.0;
    private String name;
    private int capacity;
    private int numSlotsUsed;

    private int closingTimestamp;
    private int lastTimestamp;
    private int totMinsClosed;

    private boolean flag = false;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.name = "test";
    }

    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getMinutesClosed() {
        return totMinsClosed;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSpotsRemaining() {
        return capacity - numSlotsUsed;
    }

    public double getPercentFull() {
        return (numSlotsUsed * 100.0 / capacity);
    }

    public boolean isClosed() {
        return numSlotsUsed >= (CLOSED_THRESHOLD / 100) * capacity;
    }

    public void markVehicleEntry(int timestamp) {
        if (timestamp >= lastTimestamp) {
            numSlotsUsed++;
            lastTimestamp = timestamp;
            if (isClosed() && !flag) {
                closingTimestamp = timestamp;
                flag = true;
            }
        }
    }

    public void markVehicleExit(int timestamp) {
        if (timestamp >= lastTimestamp) {
            numSlotsUsed--;
            lastTimestamp = timestamp;
            if (!isClosed() && flag) {
                totMinsClosed += timestamp - closingTimestamp;
                flag = false;
            }
        }
    }

    public String toString() {
        String percentFull = "";
        if (isClosed())
            percentFull = "CLOSED";
        else {
            DecimalFormat formatter = new DecimalFormat("#.#");
            percentFull = formatter.format(getPercentFull())+"%";
        }
        return "Status for " + getName() + " parking lot: " + numSlotsUsed + " vehicles (" + percentFull + ")";
    }

}
