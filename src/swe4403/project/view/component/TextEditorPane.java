package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Observable;
import java.util.Observer;

public class TextEditorPane extends JTextPane implements Observer {
  private static final Logger logger = Logger.getInstance();

  private DocumentModelFacade facade;

  public TextEditorPane(DocumentModelFacade facade) {
    this.facade = facade;
    this.facade.addObserver(this);

    getDocument().addDocumentListener(new DocumentChangedListener());
  }

  @Override
  public void update(Observable observable, Object o) {
    setText(facade.getDocumentState());
  }

  private class DocumentChangedListener implements DocumentListener {
    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
      logger.log(DocumentChangedListener.class, "Inserted characters");
      saveChanges();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
      logger.log(DocumentChangedListener.class, "Removed characters");
      saveChanges();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
      logger.log(DocumentChangedListener.class, "Changed characters");
      saveChanges();
    }

    private void saveChanges() {
      facade.update(TextEditorPane.this.getText());
    }
  }
}
