package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;
import java.util.Observer;
import java.util.Observable;
import java.time.LocalDateTime;

/**
 * Represents a door connecting two spaces.
 * The connected spaces are used to check access authorization.
 */

public class Door implements Observer {
  private DoorState state;
  private final String id;
  private boolean closed; // physically
  private final Space from;
  private final Space to;
  private static final Clock clock = new Clock(1);

  public Door(String id, Space from, Space to) {
    this.id = id;
    closed = true;
    this.state=new Unlocked(this);
    this.to=to;
    this.from=from;
    // Register the door in the space it gives access to
    to.addDoor(this);
  }
  // We use "synchronized" to prevent concurrent changes to the door by the clock and server threads.
  public synchronized DoorState getState() {
    return state;
  }

  public synchronized void setState(DoorState newState) {
    // Stop observing when leaving the temporary state
    if (state instanceof UnlockedShortly) {
      clock.deleteObserver(this);
    }

    // Change the current door state
    this.state = newState;

    // Observe the clock while temporarily unlocked
    if (newState instanceof UnlockedShortly) {
      clock.addObserver(this);
    }
  }

  public Space getFrom() {
    return from;
  }
  /**
   * Checks whether this door connects the requested spaces.
   * Both directions are accepted because a door connects two spaces.
   */
  public boolean connects(Space origin, Space destination) {
    return (from == origin && to == destination) || (from == destination && to == origin);
  }

  public Space getTo() {
    return to;
  }

  public synchronized  void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }
  public synchronized void setClosed(boolean closed){
    this.closed=closed;
  }

  public synchronized boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public synchronized String getStateName() {
    return state.getName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  public synchronized void update(Observable observable, Object arg) {
    // Only temporary states need clock notifications
    if (state instanceof UnlockedShortly && arg instanceof LocalDateTime) {
      LocalDateTime now = (LocalDateTime) arg;

      // Delegate the timeout check to the current state
      ((UnlockedShortly) state).checkTimeout(now);
    }
  }

  public static void startClock() {
    // Start the shared clock
    clock.start();
  }

  public static void stopClock() {
    // Stop the shared clock
    clock.stop();
  }
}
