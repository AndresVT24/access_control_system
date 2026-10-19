package baseNoStates;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;

public class Clock extends Observable{

    private LocalDateTime date; //Last date
    private final Timer timer;
    private final int period;

    public Clock(int period) {
        this.period = period;
        this.timer = new Timer(true);
    }

    public void start() {
        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                // Update the current time.
                date = LocalDateTime.now();

                // Mark the observable as changed.
                setChanged();

                // Notify observers with the current time.
                notifyObservers(date);
            }
        };
        // Run the clock periodically in a separate thread.
        timer.scheduleAtFixedRate(repeatedTask, 0, 1000L * period);
    }

    public void stop() {
        timer.cancel();
    }

    public int getPeriod() {
        return period;
    }

    public LocalDateTime getDate() {
        return date;
    }
}