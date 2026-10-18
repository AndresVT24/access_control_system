
package baseNoStates;

import java.time.LocalDateTime;

public class TestUserGroupSchedule {

    public static void main(String[] args) {

        DirectoryAreas.makeAreas();
        DirectoryUsers.makeUsers();

        User employee = DirectoryUsers.findUserByCredential("74984");
        User manager = DirectoryUsers.findUserByCredential("95783");
        User admin = DirectoryUsers.findUserByCredential("11343");
        User blank = DirectoryUsers.findUserByCredential("12345");

        LocalDateTime monday =
                LocalDateTime.of(2026, 10, 19, 10, 0);

        LocalDateTime saturday =
                LocalDateTime.of(2026, 10, 24, 10, 0);

        LocalDateTime sunday =
                LocalDateTime.of(2026, 10, 18, 10, 0);

        System.out.println(employee.canSendRequests(monday));
        System.out.println(employee.canSendRequests(saturday));
        System.out.println(manager.canSendRequests(saturday));
        System.out.println(admin.canSendRequests(sunday));
        System.out.println(blank.canSendRequests(monday));
    }
}
