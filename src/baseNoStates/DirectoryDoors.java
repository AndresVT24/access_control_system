package baseNoStates;

import java.util.ArrayList;

public final class DirectoryDoors {

  private static ArrayList<Door> allDoors = new ArrayList<>();

  private DirectoryDoors() {
  }

  public static void makeDoors() {
    allDoors = new ArrayList<>();

    Space exterior = DirectoryAreas.findSpaceById("exterior");
    Space parking = DirectoryAreas.findSpaceById("parking");
    Space stairs = DirectoryAreas.findSpaceById("stairs");
    Space hall = DirectoryAreas.findSpaceById("hall");
    Space room1 = DirectoryAreas.findSpaceById("room 1");
    Space room2 = DirectoryAreas.findSpaceById("room 2");
    Space corridor = DirectoryAreas.findSpaceById("corridor");
    Space room3 = DirectoryAreas.findSpaceById("room 3");
    Space it = DirectoryAreas.findSpaceById("IT");

    allDoors.add(new Door("D1", exterior, parking));
    allDoors.add(new Door("D2", stairs, parking));

    allDoors.add(new Door("D3", exterior, hall));
    allDoors.add(new Door("D4", stairs, hall));
    allDoors.add(new Door("D5", hall, room1));
    allDoors.add(new Door("D6", hall, room2));

    allDoors.add(new Door("D7", stairs, corridor));
    allDoors.add(new Door("D8", corridor, room3));
    allDoors.add(new Door("D9", corridor, it));
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    return null;
  }

  public static ArrayList<Door> getAllDoors() {
    return new ArrayList<>(allDoors);
  }
}