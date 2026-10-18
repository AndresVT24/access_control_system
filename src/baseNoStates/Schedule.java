package baseNoStates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Set;

public class Schedule {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final LocalTime startTime;
    private final LocalTime endTime;

    // We use Set and not ArrayList because Set don't allow duplicate elements
    private final Set<DayOfWeek> allowedDays;

    public Schedule(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Set<DayOfWeek> allowedDays){
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        // Create a copy not modifiable
        this.allowedDays = Set.copyOf(allowedDays);
    }

    public boolean isWithinSchedule(LocalDateTime now){
        LocalDate date = now.toLocalDate();
        LocalTime time = now.toLocalTime();
        DayOfWeek day = now.getDayOfWeek();
        boolean validDate = false;
        boolean validTime = false;
        boolean validDay = false;

        if (!date.isBefore(startDate) && !date.isAfter(endDate)){
            validDate = true;
        }

        if (!time.isBefore(startTime) && !time.isAfter(endTime)){
            validTime = true;
        }

        if (allowedDays.contains(day)){
            validDay = true;
        }

        return validDate && validTime && validDay;
    }
}
