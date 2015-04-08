import application.DocumentBuilder;
import application.DocumentValidator;
import application.HtmlTagMismatchException;
import application.TextComponent;
import view.window.EditorWindow;
import view.window.Window;

import java.io.IOException;

public class DocumentEditor {
  public static void main(String[] args) throws IOException {
    Window window = new EditorWindow();
    window.setVisible(true);

    String document = "";

    DocumentBuilder builder = new DocumentBuilder();

    for(Character c : document.toCharArray()) {
      builder.buildPart(c);
    }

    TextComponent result = null;

    try {
      result = builder.getResult();
    }
    catch(HtmlTagMismatchException e) {
      e.printStackTrace();
    }
  }
}
