import swe4403.project.backend.*;
import swe4403.project.view.window.EditorWindow;
import swe4403.project.view.window.Window;

import java.io.IOException;

public class DocumentEditor {
  public static void main(String[] args) throws IOException {
    Window window = new EditorWindow();
    window.setVisible(true);

    String document1 = "<html>" +
      "hi <b><i>Kristin!</i></b>" +
      "<p>How are you?</p>" +
      "</html>";

    String document2 = "<html>" +
      "hi <b>Kristin!</b>" +
      "<p>How are you?</p>" +
      "</html>";

    DocumentBuilder builder1 = new DocumentBuilder();
    DocumentBuilder builder2 = new DocumentBuilder();

    for(Character c : document1.toCharArray()) {
      builder1.buildPart(c);
    }

    for(Character c : document2.toCharArray()) {
      builder2.buildPart(c);
    }

    TextComponent result1 = null;
    TextComponent result2 = null;

    try {
      result1 = builder1.getResult();
      result2 = builder2.getResult();
    }
    catch(HtmlTagMismatchException e) {
      System.out.println("Invalid HTML document.");
    }

    if(result1 != null && result2 != null) {
      DocumentValidator validator = DocumentValidator.getInstance();

      if(validator.hasChangedText(result1, result2)) {
        System.out.println("ERROR: Document has changed text");
      }
    }
  }
}
