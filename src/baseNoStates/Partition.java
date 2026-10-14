package baseNoStates;

import java.util.ArrayList;
import java.util.ArrayList;
/**
 * Represents a group of areas in the building hierarchy.
 * The Composite design pattern lets a Partition contain both
 * other Partitions and individual Spaces through the Area type.
 */
public class Partition extends Area{
  private ArrayList<Area> children;
  public Partition(String id, String description)
  {
    super(id,description);
    children=new ArrayList<>();
  }
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();

    for (Area child : children) {
      for (Door door : child.getDoorsGivingAccess()) {
        if (!doors.contains(door)) {
          doors.add(door);
        }
      }
    }

    return doors;
  }
  public void addArea(Area area)
  {
    if(!children.contains(area))
    {
      children.add(area);
    }
  }
  public ArrayList<Area> getChildren() {
    return new ArrayList<>(children);
  }
  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }

    for (Area child : children) {
      Area found = child.findAreaById(id);

      if (found != null) {
        return found;
      }
    }
    return null;
  }
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();

    for (Area child : children) {
      spaces.addAll(child.getSpaces());
    }

    return spaces;
  }
  public String toString() {
    return "Partition{id='" + id + "', description='" + description + "'}";
  }
}
