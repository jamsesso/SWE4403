package swe4403.project.backend;

import java.util.LinkedList;
import java.util.List;

public class HistoryManager {
  private static final Logger logger = Logger.getInstance();

  public List<DocumentMemento> states = new LinkedList<DocumentMemento>();
  private Integer index = 0;

  public void save(DocumentMemento memento) {
    for(Integer i = index; i < states.size(); i++) {
      states.remove(i.intValue());
    }

    states.add(index, memento);
    index++;
  }

  public DocumentMemento undo() {
    Integer i = index - 2;

    if(i >= 0 && i < states.size()) {
      index = i + 1;
      return states.get(i);
    }

    return null;
  }

  public DocumentMemento redo() {
    if(index < states.size()) {
      logger.log(HistoryManager.class, "Redo: " + states.get(index).getState());

      DocumentMemento nextState = states.get(index);
      index++;
      return nextState;
    }

    return null;
  }
}
