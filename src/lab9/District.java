package lab9;/*
 * CS1011 - TODO
 * Fall 2020
 * Lab 9 - ParkingLots
 * Name: TODO
 * Created: 11/6/2019
 */

/**
 * Manages parking lots within a district.
 *
 * @author [TODO: put your name here]
 */
public class District {
    private ParkingLot[] lots;
    public static final int MAX_LOTS = 20;
    private int numLots;
    private int closingTimestamp;
    private int totMinsClosed;

    private boolean flag = false;

    /**
     * Set up a district with three parking lots.
     */
    public District() {
        lots = new ParkingLot[MAX_LOTS];
    }

    public int addLot(String name, int capacity) {
        int newIndex = numLots;
        if (newIndex < MAX_LOTS) {
            lots[newIndex] = new ParkingLot(name, capacity);
            numLots++;
        }
        return newIndex < MAX_LOTS ? newIndex : -1;
    }

    public ParkingLot getLot(int index) {
        if (index >= 0 && index < MAX_LOTS) {
            return lots[index];
        }
        return null;
    }

    /**
     * Returns the number of remaining parking spots in the district
     *
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {
        // TODO: Complete this stub
        int numSpotsRemaining = 0;
        for (int i = 0; i < numLots; i++)
            numSpotsRemaining += lots[i].getNumberOfSpotsRemaining();
        return numSpotsRemaining;
    }

    /**
     * Returns the amount of time all three lots have been
     * simultaneously closed.
     *
     * @return number of minutes all three lots have been closed
     */
    public int getMinutesClosed() {
        // TODO: Complete this stub
        return totMinsClosed;
    }

    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     *
     * @return whether all three lots in the district are closed
     */
    public boolean isClosed() {
        // TODO: Complete this stub
        for (int i = 0; i < numLots; i++) {
            if (!lots[i].isClosed())
                return false;
        }
        return true;
    }

    /**
     * Record a vehicle entering a lot at a specified timestamp.
     * <p></p>
     * This calls lab8.ParkingLot.markVehicleEntry for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleEntry on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     *
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {
        // TODO: Complete this stub
        lots[lotNumber].markVehicleEntry(timestamp);
        if (isClosed() && !flag) {
            closingTimestamp = timestamp;
            flag = true;
        }
    }

    /**
     * Record a vehicle exiting a lot at a specified timestamp.
     * <p></p>
     * This calls lab8.ParkingLot.markVehicleExit for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleExit on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     *
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Exit timestamp in minutes since all lots were opened.
     */
    public void markVehicleExit(int lotNumber, int timestamp) {
        // TODO: Complete this stub
        lots[lotNumber].markVehicleExit(timestamp);
        if (!isClosed() && flag) {
            totMinsClosed += timestamp - closingTimestamp;
            flag = false;
        }
    }

    public String toString() {
        String str = "";
        str += "District status:\n";
        for (int i = 0; i < numLots; i++) {
            str += "  ";
            str += lots[i].toString();
            str += "\n";
        }
        return str;
    }
}