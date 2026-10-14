
package baseNoStates;

import java.util.ArrayList;
/**
 * Represents a leaf area in the building hierarchy.
 * A Space cannot contain other areas, so its only space is itself.
 */
public class Space extends Area {

  public Space(String id, String description) {
    super(id, description);
  }


  public Area findAreaById(String id) {
    if (this.id.equals(id)) {
      return this;
    }
    return null;
  }


  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    spaces.add(this);
    return spaces;
  }


  public String toString() {
    return "Space{id='" + id + "', description='" + description + "'}";
  }
}
