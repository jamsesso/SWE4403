package swe4403.project.backend;

public class DocumentModelFacade {
  private TextComponent document;
  private DocumentValidator documentValidator = DocumentValidator.getInstance();
  private DocumentBuilder documentBuilder;

  public DocumentModelFacade(String document) {
    this.document = parseDocument(document);
  }

  public void update(String pendingDocument) {
    TextComponent pendingTextComponent = parseDocument(pendingDocument);

    if(documentValidator.hasChangedText(document, pendingTextComponent)) {
      throw new DocumentEditException("Document text was changed!");
    }

    document = pendingTextComponent;
  }

  public String getDocument() {
    return document.toString();
  }

  private TextComponent parseDocument(String document) {
    documentBuilder = new DocumentBuilder();

    for(Character c : document.toCharArray()) {
      documentBuilder.buildPart(c);
    }

    return documentBuilder.getResult();
  }
}
