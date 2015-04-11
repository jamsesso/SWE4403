package swe4403.project.backend;

public class DocumentMemento implements Memento<String> {
  private String documentState;

  public DocumentMemento(String document) {
    this.documentState = document;
  }

  @Override
  public String getState() {
    return documentState;
  }

  @Override
  public void setState(String state) {
    documentState = state;
  }
}
