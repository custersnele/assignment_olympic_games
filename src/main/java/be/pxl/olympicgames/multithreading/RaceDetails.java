package be.pxl.olympicgames.multithreading;

public class RaceDetails {
    private final long id;
    private final int distance;

    public long getId() {
        return id;
    }

    public RaceDetails(long id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
