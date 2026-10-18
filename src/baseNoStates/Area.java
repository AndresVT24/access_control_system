package baseNoStates;
import java.util.ArrayList;
/**
 * Abstract base class for all areas in the building.
 * It provides common information and access doors for partitions and spaces.
 * Polymorphism allows DirectoryAreas to handle both types as Area objects.
 */
public abstract class Area {
  protected String id;
  protected String description;
  protected ArrayList<Door> doorsGivingAccess;

  public Area(String id, String description)
  {
    this.id=id;
    this.description=description;
    this.doorsGivingAccess=new ArrayList<>();
  }
  public String getId() {
    return id;
  }
  /**
   * Adds a door that gives access to this area.
   */
  public void addDoor(Door door) {
    if (!doorsGivingAccess.contains(door)) {
      doorsGivingAccess.add(door);
    }
  }
  public ArrayList<Door> getDoorsGivingAccess() {
    return new ArrayList<>(doorsGivingAccess);
  }
  /**
   * Finds an area by ID. Each subclass implements its own search.
   */
  public abstract Area findAreaById(String id);
  /**
   * Returns all spaces contained in this area.
   */
  public abstract ArrayList<Space> getSpaces();



}
