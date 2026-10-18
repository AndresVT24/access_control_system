
package baseNoStates;

import java.util.ArrayList;

/**
 * Manages the creation and lookup of user groups.
 * This class centralizes group management so that other classes
  do not need to create or store groups independently.
 * Design pattern: Singleton-like static registry.
 * provide access to the shared collection of user groups.
 */
public final class DirectoryUserGroups {

  private static final ArrayList<UserGroup> userGroups =
      new ArrayList<>();

  private DirectoryUserGroups() {
  }

  /**
   * Creates the predefined user groups and assigns their
   * authorized areas.
   * Must be called after DirectoryAreas.makeAreas().
   */
  public static void makeUserGroups() {
    userGroups.clear();

    UserGroup blank = new UserGroup("Blank");
    UserGroup employees = new UserGroup("Employees");
    UserGroup managers = new UserGroup("Managers");
    UserGroup administrator = new UserGroup("Administrator");

    userGroups.add(blank);

    employees.addArea(DirectoryAreas.findAreaById("ground floor"));
    employees.addArea(DirectoryAreas.findAreaById("floor 1"));
    employees.addArea(DirectoryAreas.findAreaById("exterior"));
    employees.addArea(DirectoryAreas.findAreaById("stairs"));
    userGroups.add(employees);

    managers.addArea(DirectoryAreas.findAreaById("building"));
    userGroups.add(managers);

    administrator.addArea(DirectoryAreas.findAreaById("building"));
    userGroups.add(administrator);
  }

  /**
   * Finds a user group by name.
   * Returns null if no group matches the given name.
   */
  public static UserGroup findUserGroupByName(String name) {
    for (UserGroup group : userGroups) {
      if (group.getName().equals(name)) {
        return group;
      }
    }

    return null;
  }

  /**
   * Returns a copy of the registered user groups,
   * preventing callers from directly modifying the internal list.
   */
  public static ArrayList<UserGroup> getUserGroups() {
    return new ArrayList<>(userGroups);
  }
}
