package baseNoStates;

import baseNoStates.requests.RequestArea;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

public class Tests {
    public static void main(String[] args) {
        DirectoryAreas.makeAreas();
        DirectoryDoors.makeDoors();
        DirectoryUsers.makeUsers();

        // Administrator: can lock all doors.
        RequestArea adminRequest = new RequestArea(
                "11343",
                Actions.LOCK,
                LocalDateTime.of(2026, 10, 18, 10, 0),
                "ground floor"
        );

        adminRequest.process();

        JSONArray adminDoors =
                adminRequest.answerToJson().getJSONArray("requestsDoors");

        if (adminDoors.length() != 4) {
            throw new AssertionError("Expected 4 doors");
        }

        for (int i = 0; i < adminDoors.length(); i++) {
            JSONObject result = adminDoors.getJSONObject(i);

            if (!result.getBoolean("authorized")
                    || !result.getString("state").equals(States.LOCKED)) {
                throw new AssertionError("Administrator test failed");
            }
        }

        System.out.println("Administrator test passed!");

        // Employee: cannot lock doors.
        RequestArea employeeRequest = new RequestArea(
                "74984",
                Actions.LOCK,
                LocalDateTime.of(2026, 10, 19, 10, 0),
                "ground floor"
        );

        employeeRequest.process();

        JSONArray employeeDoors =
                employeeRequest.answerToJson().getJSONArray("requestsDoors");

        if (employeeDoors.length() != 4) {
            throw new AssertionError("Expected 4 doors");
        }

        for (int i = 0; i < employeeDoors.length(); i++) {
            JSONObject result = employeeDoors.getJSONObject(i);

            if (result.getBoolean("authorized")) {
                throw new AssertionError("Employee test failed");
            }
        }

        System.out.println("Employee test passed!");
    }
}
