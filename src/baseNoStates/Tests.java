package baseNoStates;

import java.time.LocalDateTime;
import baseNoStates.requests.RequestReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Observable;
import java.util.Observer;

public class Tests {
    public static void main(String[] args) {
        DirectoryAreas.makeAreas();
        DirectoryDoors.makeDoors();
        DirectoryUsers.makeUsers();

        Door door = DirectoryDoors.findDoorById("D1");

        // Start the shared clock for this test.
        Door.startClock();

        try {
            // TEST 1: Closed door returns to Locked.
            send(Actions.LOCK);
            send(Actions.UNLOCK_SHORTLY);

            checkState(door, States.UNLOCKED_SHORTLY);

            // Wait for the automatic timeout.
            Thread.sleep(12000);

            checkState(door, States.LOCKED);
            System.out.println("Test 1 passed!");

            // TEST 2: Open door becomes Propped.
            send(Actions.UNLOCK_SHORTLY);
            send(Actions.OPEN);

            Thread.sleep(12000);

            checkState(door, States.PROPPED);
            System.out.println("Test 2 passed!");

            // TEST 3: Closing Propped returns to Locked.
            send(Actions.CLOSE);

            checkState(door, States.LOCKED);

            if (!door.isClosed()) {
                throw new AssertionError("Door should be closed");
            }

            System.out.println("Test 3 passed!");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Stop the clock after testing.
            Door.stopClock();
        }
    }

    private static void send(String action) {

        RequestReader request = new RequestReader(
                "11343",
                action,
                LocalDateTime.now(),
                "D1"
        );

        request.process();

        if (!request.isAuthorized()) {
            throw new AssertionError("Request was not authorized");
        }
    }

    private static void checkState(Door door, String expected) {

        if (!door.getStateName().equals(expected)) {
            throw new AssertionError(
                    "Expected " + expected
                            + " but got " + door.getStateName()
            );
        }
    }
}
