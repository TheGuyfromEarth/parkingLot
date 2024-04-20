package lab8;

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

    public void displayStatus() {
        System.out.print(name + " parking lot ");
        if (isClosed())
            System.out.println("status: CLOSED");
        else
            System.out.println("status: " + Math.round(getPercentFull() * 10.0) / 10.0 + "%");
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

}
