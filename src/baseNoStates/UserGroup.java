
package baseNoStates;

import java.util.ArrayList;

public class UserGroup {

  private final String name;
  private final ArrayList<User> users;
  private final ArrayList<Area> areas = new ArrayList<>();
  public UserGroup(String name) {
    this.name = name;
    this.users = new ArrayList<>();
  }
  public void addArea(Area area) {
    if (area != null && !areas.contains(area)) {
      areas.add(area);
    }
  }

  public boolean canBeInSpace(Space space) {
    if (space == null) {
      return false;
    }

    for (Area area : areas) {
      if (area.getSpaces().contains(space)) {
        return true;
      }
    }

    return false;
  }



  public String getName() {
    return name;
  }

  public void addUser(User user) {
    if (user != null && !users.contains(user)) {
      users.add(user);
    }
  }

  public ArrayList<User> getUsers() {
    return new ArrayList<>(users);
  }

  @Override
  public String toString() {
    return "UserGroup{name='" + name + "'}";
  }
}
