package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    // Initialize areas, doors and users.
    DirectoryAreas.makeAreas();
    DirectoryDoors.makeDoors();
    DirectoryUsers.makeUsers();

    // Start the shared clock before the web server.
    Door.startClock();

    // Start accepting HTTP requests.
    new WebServer();
  }
}
