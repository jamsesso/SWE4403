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

    String document = "<html>" +
      "hi <b><i>Kristin!</b></i>" +
      "<p>How are you?</p>" +
      "</html>";

    DocumentBuilder builder = new DocumentBuilder();

    for(Character c : document.toCharArray()) {
      builder.buildPart(c);
    }

    TextComponent result = null;

    try {
      result = builder.getResult();
      System.out.println(result.toString());
    }
    catch(HtmlTagMismatchException e) {
      System.out.println("Invalid HTML document.");
    }
  }
}
