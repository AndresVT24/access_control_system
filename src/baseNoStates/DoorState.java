package baseNoStates;

public abstract class DoorState {
    protected final Door door;
    private final String name;

    public DoorState(Door door, String name){
        this.door = door;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract void open();
    public abstract void close();
    public abstract void lock();
    public abstract void unlock();
}
