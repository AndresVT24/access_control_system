
package baseNoStates;

import java.util.ArrayList;

/**
 * Manages user creation, storage and lookup.
 * It centralizes user management so other classes can find users
 without maintaining their own user collections.

 * Design pattern: Singleton-like static registry.
 * A private constructor prevents instances, while static methods
 * operate on the shared collection of users.
 */
public final class DirectoryUsers {

  private static final ArrayList<User> users = new ArrayList<>();

  private DirectoryUsers() {
  }

  /**
   * Creates all users and assigns each one to a user group.
   * User groups must be initialized before users are assigned.
   */
  public static void makeUsers() {
    users.clear();

    DirectoryUserGroups.makeUserGroups();

    UserGroup blank =
        DirectoryUserGroups.findUserGroupByName("Blank");

    UserGroup employees =
        DirectoryUserGroups.findUserGroupByName("Employees");

    UserGroup managers =
        DirectoryUserGroups.findUserGroupByName("Managers");

    UserGroup administrator =
        DirectoryUserGroups.findUserGroupByName("Administrator");

    addUserToGroup("Bernat", "12345", blank);
    addUserToGroup("Blai", "77532", blank);

    addUserToGroup("Eulalia", "43295", employees);
    addUserToGroup("Ernest", "74984", employees);

    addUserToGroup("Manel", "95783", managers);
    addUserToGroup("Marta", "05827", managers);

    addUserToGroup("Ana", "11343", administrator);
  }

  /**
   * Creates a user, assigns the user to a group,
   * and stores the user in the directory.
   */
  private static void addUserToGroup(
      String name, String credential, UserGroup group) {

    User user = new User(name, credential);
    user.setUserGroup(group);
    users.add(user);
  }

  /**
   * Finds a user by credential.
   * Returns null if no matching user exists.
   */
  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }

    return null;
  }

  /**
   * Returns a copy of the users list to protect the internal collection.
   */
  public static ArrayList<User> getUsers() {
    return new ArrayList<>(users);
  }
}
