package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class UserGroup {
  private final String name;
  private final ArrayList<User> users;
  private final ArrayList<Area> areas = new ArrayList<>();
  private Schedule schedule;
  //We use set because contains method "contains()" and the elements can't be duplicated
  private final Set<String> allowedActions = new HashSet<>();

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

  public void setSchedule(Schedule schedule){
    this.schedule = schedule;
  }

  public boolean canSendRequests(LocalDateTime now){
    return schedule != null && schedule.isWithinSchedule(now);
  }

  public void addAllowedAction(String action){
    if (action != null) {
      allowedActions.add(action);
    }
  }

  public boolean canDoAction(String action){
    return action != null && allowedActions.contains(action);
  }

  @Override
  public String toString() {
    return "UserGroup{name='" + name + "'}";
  }
}
