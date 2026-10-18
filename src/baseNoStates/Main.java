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
    /*DirectoryAreas.makeAreas(); //NOU
    DirectoryDoors.makeDoors();
    DirectoryUsers.makeUsers();
    new WebServer();*/
    Schedule employees = new Schedule(
            LocalDate.of(2026, 9, 1),
            LocalDate.of(2027, 3, 1),
            LocalTime.of(9, 0),
            LocalTime.of(17, 0),
            Set.of(
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY
            )
    );

    LocalDateTime date1 =
            LocalDateTime.of(2026, 10, 19, 10, 0);

    LocalDateTime date2 =
            LocalDateTime.of(2026, 10, 18, 10, 0);

    LocalDateTime date3 =
            LocalDateTime.of(2026, 10, 19, 18, 0);

    System.out.println(employees.isWithinSchedule(date1));
    System.out.println(employees.isWithinSchedule(date2));
    System.out.println(employees.isWithinSchedule(date3));
  }
}
