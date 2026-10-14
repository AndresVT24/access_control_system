package baseNoStates;

public class Locked extends DoorState
{

  public Locked(Door door){
    super(door, States.LOCKED);

  }
  public void open(){
    System.out.print("Unable to open the door, it's locked");

  }
  public void close(){
    System.out.print("It's already closed");


  }
  public void lock(){

    System.out.print("It's already locked");
  }
  public void unlock()
  {
        door.setState(new Unlocked(door));

  }
}
