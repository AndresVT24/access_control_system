package baseNoStates;

import java.time.LocalDateTime;
import java.time.Duration;

public class UnlockedShortly extends DoorState {

    private final LocalDateTime startTime;

    public UnlockedShortly(Door door) {
        super(door, States.UNLOCKED_SHORTLY);

        // Record when the temporary unlock starts
        startTime = LocalDateTime.now();
    }

    public void open() {
        // The door can be opened while temporarily unlocked
        if (door.isClosed()) {
            door.setClosed(false);
        }
    }

    public void close() {
        // Closing the door does not end the temporary state
        if (!door.isClosed()) {
            door.setClosed(true);
        }
    }

    public void lock() {
        // The door waits until the temporary period expires
        System.out.println("Door is temporarily unlocked");
    }

    public void unlock() {
        System.out.println("Door is already temporarily unlocked");
    }

    public void unlockShortly() {
        // Do not restart the countdown
        System.out.println("Door is already temporarily unlocked");
    }

    public void checkTimeout(LocalDateTime now) {
        // Calculate the time spent in this state
        Duration elapsed = Duration.between(startTime, now);

        if (elapsed.compareTo(Duration.ofSeconds(10)) >= 0) {
            // Select the next state based on the physical door
            if (door.isClosed()) {
                door.setState(new Locked(door));
            } else {
                door.setState(new Propped(door));
            }
        }
    }
}
