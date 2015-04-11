package swe4403.project.backend;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Logger extends Observable {
  private static Logger instance;
  private List<String> messages;

  private Logger() {
    messages = new LinkedList<String>();
  }

  public void log(Class cls, String message) {
    String log = cls.getName() + ": " + message;
    messages.add(0, log);
    out.println(log);

    setChanged();
    notifyObservers();
  }

  public String getLatestLog() {
    return messages.get(0);
  }

  public List<String> getLogs() {
    return messages;
  }

  public static Logger getInstance() {
    if(instance == null) {
      instance = new Logger();
    }

    return instance;
  }
}
