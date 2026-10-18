package baseNoStates;

public class Locked extends DoorState{
    public Locked(Door door){
        super(door, "locked");
    }

    @Override
    public void open(){
        System.out.println("Door " + door.getId() + " can't be open because is locked");
    }

    @Override
    public void close(){
        System.out.println("Door " + door.getId() + " is already closed");
    }

    @Override
    public void lock(){
        System.out.println("Door " + door.getId() + " is already locked");
    }

    @Override
    public void unlock(){
        door.setState(new Unlocked(door));
    }
}
