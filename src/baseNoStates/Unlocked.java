package baseNoStates;

public class Unlocked extends DoorState{

    public Unlocked(Door door){
        super(door, "unlocked");
    }

    @Override
    public void open() {
        if (door.isClosed()) {
            door.setClosed(false);
        } else {
            System.out.println("Door " + door.getId() + " is already open");
        }
    }

    @Override
    public void close() {
        if (!door.isClosed()) {
            door.setClosed(true);
        } else {
            System.out.println("Door " + door.getId() + " is already closed");
        }
    }

    @Override
    public void lock() {
        if (door.isClosed()) {
            door.setState(new Locked(this.door));
        } else {
            System.out.println("Door " + door.getId() + " can't be locked because is open");
        }
    }

    @Override
    public void unlock() {
        System.out.println("Door " + door.getId() +" is already unlocked");
    }
}
