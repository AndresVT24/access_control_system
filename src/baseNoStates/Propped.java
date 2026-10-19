package baseNoStates;

public class Propped extends DoorState {

    public Propped(Door door) {
        super(door, States.PROPPED);
    }

    public void open() {
        // A propped door is already open
        System.out.println("Door is already open");
    }

    public void close() {
        // Close the door and return to the locked state
        door.setClosed(true);
        door.setState(new Locked(door));
    }

    public void lock() {
        // An open door cannot be locked
        System.out.println("Cannot lock a propped door");
    }

    public void unlock() {
        System.out.println("Door is propped open");
    }

    public void unlockShortly() {
        System.out.println("Door is propped open");
    }
}