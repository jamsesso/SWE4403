package application;

public class DocumentValidator {
  private static DocumentValidator instance;

  private DocumentValidator() { }

  public static DocumentValidator getInstance() {
    if(instance == null) {
      instance = new DocumentValidator();
    }

    return instance;
  }

  public Boolean isValid(TextComponent document) {
    /**
     * In order for a document to be considered valid, we need to traverse each row of the
     * TextComponent tree and make sure that all open tags are closed on the same row. If
     * so, then the document is properly formatted. If not, then the document is not
     * properly formatted.
     */
    for(Integer i = 0; i < document.numChildren(); i++) {
      TextComponent component = document.getChild(i);

      if(component instanceof OpeningHtmlTagDecorator) {
        TextComponent closingComponent;

        try {
          closingComponent = document.getChild(i + 2);
        }
        catch(IndexOutOfBoundsException e) {
          return false;
        }

        if(!(closingComponent instanceof ClosingHtmlTagDecorator)) {
          // Something is wrong with the format of the parsed document tree.
          return false;
        }

        TextComponent original = ((TextItemDecorator) component).getOriginalTextItem();
        TextComponent opposite = ((TextItemDecorator) closingComponent).getOriginalTextItem();

        // Make sure the tags match.
        if(!original.toString().equalsIgnoreCase(opposite.toString())) {
          return false;
        }

        try {
          // Now recurse down in-between the tags.
          if(!isValid(document.getChild(i + 1))) {
            return false;
          }
        }
        catch(IndexOutOfBoundsException e) {
          return false;
        }
      }
    }

    return true;
  }
}
