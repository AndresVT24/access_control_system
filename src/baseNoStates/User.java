
package baseNoStates;

public class User {

  private final String name;
  private final String credential;
  private UserGroup userGroup;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getName() {
    return name;
  }

  public String getCredential() {
    return credential;
  }

  public UserGroup getUserGroup() {
    return userGroup;
  }

  public void setUserGroup(UserGroup userGroup) {
    // Si ja pertany a un grup, l'eliminació de l'anterior
    // es podrà gestionar des de UserGroup en una millora posterior.
    this.userGroup = userGroup;

    if (userGroup != null) {
      userGroup.addUser(this);
    }
  }
  public boolean canBeInSpace(Space space) {
    return userGroup != null && userGroup.canBeInSpace(space);
  }
  @Override
  public String toString() {
    return "User{name='" + name
        + "', credential='" + credential
        + "', userGroup='"
        + (userGroup != null ? userGroup.getName() : "none")
        + "'}";
  }
}
