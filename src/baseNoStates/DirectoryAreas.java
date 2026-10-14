
package baseNoStates;
import java.util.ArrayList;

/**
 * Maintains the building area hierarchy and provides area lookup.
 * A single root lets the rest of the application search the whole building.
 */
public final class DirectoryAreas {

  private static Partition root;

  private DirectoryAreas() {
  }

  public static void makeAreas() {
    root = new Partition("building", "Building");

    Partition basement = new Partition("basement", "Basement");
    Partition groundFloor = new Partition("ground floor", "Ground floor");
    Partition floor1 = new Partition("floor 1", "First floor");

    Space parking = new Space("parking", "Parking");
    Space hall = new Space("hall", "Hall");
    Space room1 = new Space("room 1", "Room 1");
    Space room2 = new Space("room 2", "Room 2");
    Space room3 = new Space("room 3", "Room 3");
    Space corridor = new Space("corridor", "Corridor");
    Space it = new Space("IT", "IT room");
    Space stairs = new Space("stairs", "Stairs");
    Space exterior = new Space("exterior", "Exterior");

    root.addArea(basement);
    root.addArea(groundFloor);
    root.addArea(floor1);
    root.addArea(stairs);
    root.addArea(exterior);

    basement.addArea(parking);

    groundFloor.addArea(hall);
    groundFloor.addArea(room1);
    groundFloor.addArea(room2);

    floor1.addArea(room3);
    floor1.addArea(corridor);
    floor1.addArea(it);
  }


  public static Area findAreaById(String id) {
    if (root == null || id == null) {
      return null;
    }

    switch (id) {
      case "ground_floor":
        id = "ground floor";
        break;
      case "floor1":
        id = "floor 1";
        break;
      case "room1":
        id = "room 1";
        break;
      case "room2":
        id = "room 2";
        break;
      case "room3":
        id = "room 3";
        break;
    }

    return root.findAreaById(id);
  }



  public static Space findSpaceById(String id) {
    Area area = findAreaById(id);

    if (area instanceof Space) {
      return (Space) area;
    }

    return null;
  }
  public static Door findDoorById(String id) {
    return DirectoryDoors.findDoorById(id);
  }

  public static ArrayList<Door> getAllDoors() {
    return DirectoryDoors.getAllDoors();
  }
}
