package swe4403.project.backend;

import java.util.Observable;
import java.util.Stack;

public class DocumentModelFacade extends Observable {
  private static final Logger logger = Logger.getInstance();

  private TextComponent lastDocumentRevision = null;
  private String state;
  private DocumentValidator documentValidator = DocumentValidator.getInstance();
  private Stack<DocumentMemento> undoStack = new Stack<DocumentMemento>();
  private Stack<DocumentMemento> redoStack = new Stack<DocumentMemento>();

  public DocumentModelFacade() {
    update("");
  }

  public DocumentModelFacade(String document) {
    update(document);
  }

  public void update(String pendingDocument) {
    logger.log(DocumentModelFacade.class, "Updating document: " + pendingDocument);
    state = pendingDocument;
    undoStack.push(new DocumentMemento(state));
  }

  public void update(String pendingDocument, Boolean notify) {
    update(pendingDocument);

    if(notify) {
      setChanged();
      notifyObservers();
    }
  }

  public String getDocument() {
    TextComponent document = parseDocument(state);

    if(lastDocumentRevision != null && documentValidator.hasChangedText(document, lastDocumentRevision)) {
      logger.log(DocumentModelFacade.class, "Cannot update the document model because the text was changed.");
      throw new DocumentEditException("Document text was changed!");
    }

    lastDocumentRevision = document;
    state = document.toString();

    return state;
  }

  public String getDocumentState() {
    return state;
  }

  public void undo() {
    logger.log(DocumentModelFacade.class, "Undoing last document edit operation...");
  }

  public void redo() {
    logger.log(DocumentModelFacade.class, "Redoing last document edit operation.");
  }

  public void clearEditHistory() {
    undoStack.clear();
    redoStack.clear();
  }

  private TextComponent parseDocument(String document) {
    DocumentBuilder documentBuilder = new DocumentBuilder();

    for(Character c : document.toCharArray()) {
      documentBuilder.buildPart(c);
    }

    return documentBuilder.getResult();
  }
}
