package baseNoStates;

public class Unlocked extends DoorState
{
  public Unlocked(Door door)
  {
    super(door, States.UNLOCKED);
  }
  public void open(){
      if(door.isClosed())
      {
        door.setClosed(false);
      }
  }

  public void close(){
    if(!door.isClosed())
    {
      door.setClosed(true);
    }
  }

  public void lock(){
      if(door.isClosed())
      {
        door.setState(new Locked(door));
      }
  }

  public void unlock(){
    System.out.print("It's already unlocked");
  }

  public void unlockShortly(){
    System.out.println("Door is already unlocked");
  }
}