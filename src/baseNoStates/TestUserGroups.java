
package baseNoStates;

import baseNoStates.requests.RequestReader;
import java.time.LocalDateTime;

public class TestUserGroups {

  public static void main(String[] args) {

    // Inicialitzar primer les àrees, després les portes i els usuaris
    DirectoryAreas.makeAreas();
    DirectoryDoors.makeDoors();
    DirectoryUsers.makeUsers();

    LocalDateTime now = LocalDateTime.now();

    System.out.println("=== Prova Employees ===");
    testAccess("74984", now, "D3");

    System.out.println("\n=== Prova Blank ===");
    testAccess("12345", now, "D1");

    System.out.println("\n=== Prova usuari inexistent ===");
    testAccess("00000", now, "D1");
  }

  private static void testAccess(
      String credential,
      LocalDateTime now,
      String doorId) {

    RequestReader request =
        new RequestReader(credential, "open", now, doorId);

    request.process();

    System.out.println(request);
  }
}
