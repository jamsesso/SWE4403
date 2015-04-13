package swe4403.project.backend;

import java.io.File;
import java.util.Observable;

public class DocumentModelFacade extends Observable {
  private static final Logger logger = Logger.getInstance();

  private TextComponent lastDocumentRevision = null;
  private String state;
  private DocumentValidator documentValidator = DocumentValidator.getInstance();
  private File saveLocation;
  private HistoryManager historyManager = new HistoryManager();
  private Boolean isShowingHtmlTags = true;

  public DocumentModelFacade() {
    update("");
  }

  public DocumentModelFacade(String document) {
    update(document);
  }

  public void update(String pendingDocument) {
    logger.log(DocumentModelFacade.class, "Updating document.");
    historyManager.save(new DocumentMemento(pendingDocument));
    state = pendingDocument;
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
    if(isShowingHtmlTags) {
      return state;
    }
    else {
      // In order to hide HTML tags, we need to parse the document.
      TextComponent htmlTree = parseDocument(state);
      Iterator<TextComponent> iterator = htmlTree.createIterator();
      HtmlTreeVisitor visitor = new HideHtmlTagVisitor();

      for(iterator.first(); !iterator.isDone(); iterator.next()) {
        TextComponent currentItem = iterator.currentItem();
        currentItem.accept(visitor);
      }

      return htmlTree.toString();
    }
  }

  public File getFileSaveLocation() {
    return saveLocation;
  }

  public void setFileSaveLocation(File saveLocation) {
    this.saveLocation = saveLocation;
  }

  public void undo() {
    DocumentMemento lastState = historyManager.undo();

    if(lastState != null) {
      logger.log(DocumentModelFacade.class, "Last value: " + lastState.getState());
      state = lastState.getState();
      setChanged();
      notifyObservers();
    }
  }

  public void redo() {
    DocumentMemento nextState = historyManager.redo();

    if(nextState != null) {
      logger.log(DocumentModelFacade.class, "Redoing operation");
      state = nextState.getState();
      setChanged();
      notifyObservers();
    }
  }

  public void clearEditHistory() {
    lastDocumentRevision = null;
    historyManager = new HistoryManager();
  }

  public void hideHtmlTags() {
    isShowingHtmlTags = false;
    setChanged();
    notifyObservers();
  }

  public void showHtmlTags() {
    isShowingHtmlTags = true;
    setChanged();
    notifyObservers();
  }

  private TextComponent parseDocument(String document) {
    DocumentBuilder documentBuilder = new DocumentBuilder();

    for(Character c : document.toCharArray()) {
      documentBuilder.buildPart(c);
    }

    return documentBuilder.getResult();
  }
}
