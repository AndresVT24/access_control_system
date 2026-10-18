package baseNoStates;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;

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
    //Creation UserGroups
    UserGroup blank = new UserGroup("Blank");
    UserGroup employees = new UserGroup("Employees");
    UserGroup managers = new UserGroup("Managers");
    UserGroup administrator = new UserGroup("Administrator");

    userGroups.add(blank);

    //Adding Areas to UserGroups
    employees.addArea(DirectoryAreas.findAreaById("ground floor"));
    employees.addArea(DirectoryAreas.findAreaById("floor 1"));
    employees.addArea(DirectoryAreas.findAreaById("exterior"));
    employees.addArea(DirectoryAreas.findAreaById("stairs"));
    userGroups.add(employees);

    managers.addArea(DirectoryAreas.findAreaById("building"));
    userGroups.add(managers);

    administrator.addArea(DirectoryAreas.findAreaById("building"));
    userGroups.add(administrator);

    //Creation of a Schedule Employee
    Schedule employeeSchedule = new Schedule(
            LocalDate.of(2026, 9, 1),
            LocalDate.of(2027, 3, 1),
            LocalTime.of(9, 0),
            LocalTime.of(17, 0),
            EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
    );
    employees.setSchedule(employeeSchedule);

    //Creation of a Schedule Manager
    Schedule managerSchedule = new Schedule(
            LocalDate.of(2026, 9, 1),
            LocalDate.of(2027, 3, 1),
            LocalTime.of(8, 0),
            LocalTime.of(20, 0),
            EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.SATURDAY)
    );
    managers.setSchedule(managerSchedule);

    //Creation of a Schedule Admin
    Schedule administratorSchedule = new Schedule(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2100, 12, 31),
            LocalTime.MIN,
            LocalTime.MAX,
            EnumSet.allOf(DayOfWeek.class)
    );
    administrator.setSchedule(administratorSchedule);

    //Adding Actions to UserGroup
    employees.addAllowedAction(Actions.OPEN);
    employees.addAllowedAction(Actions.CLOSE);
    employees.addAllowedAction(Actions.UNLOCK_SHORTLY);

    String[] allActions = {
            Actions.OPEN,
            Actions.CLOSE,
            Actions.LOCK,
            Actions.UNLOCK,
            Actions.UNLOCK_SHORTLY
    };

    for (String action : allActions) {
      managers.addAllowedAction(action);
      administrator.addAllowedAction(action);
    }
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
