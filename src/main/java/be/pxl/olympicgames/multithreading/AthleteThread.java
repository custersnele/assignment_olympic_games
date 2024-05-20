package be.pxl.olympicgames.multithreading;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class AthleteThread {
    private static final Random RANDOM = new Random();
    private final long id;
    private final String firstName;
    private final String lastName;
    private final RaceDetails race;
    private int distanceRaced;
    private LocalDateTime start;
    private LocalDateTime end;

    public AthleteThread(long id, String firstName, String lastName, RaceDetails race) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.race = race;
    }

    public long getAthleteId() {
        return id;
    }


    public String getStatus() {
        return end != null? "QUALIFIED" : "DID_NOT_FINISH";
    }

    public Duration getTime() {
        return end != null? Duration.between(start, end) : null;
    }
}
