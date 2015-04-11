package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class TextEditorPane extends JTextPane implements Observer {
  private static final Logger logger = Logger.getInstance();

  private DocumentModelFacade facade;
  private String lastRecordedContent = null;

  public TextEditorPane(DocumentModelFacade facade) {
    this.facade = facade;
    this.facade.addObserver(this);

    addKeyListener(new DocumentChangedListener());
  }

  @Override
  public void update(Observable observable, Object o) {
    logger.log(TextEditorPane.class, "Editor window is updating.");
    String previousState = facade.getDocumentState();

    lastRecordedContent = previousState;
    setText(previousState);
  }

  private class DocumentChangedListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) { }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
      if(!keyEvent.isActionKey()) {
        String content = TextEditorPane.this.getText();

        if(!content.equals(lastRecordedContent)) {
          lastRecordedContent = content;
          facade.update(content);
        }
      }
    }
  }
}
