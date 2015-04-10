package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DocumentBuilder {
  private TextComponent currentRoot = new TextComposite();
  private Stack<TextComponent> tree = new Stack<TextComponent>();
  private List<Character> buffer = new ArrayList<Character>();
  private Boolean bufferCaptureTag = false;

  public DocumentBuilder() {
    tree.push(currentRoot);
  }

  public void buildPart(Character c) {
    if(c == '<' && !bufferCaptureTag) {
      if(buffer.size() > 0) {
        String text = listToString(buffer);
        TextComponent component = new TextItemProtectionProxy(text, ProxyPolicy.READ_ONLY);

        currentRoot.addChild(currentRoot.numChildren(), component);
        buffer.clear();
      }

      bufferCaptureTag = true;
    }
    else if(c == '>' && bufferCaptureTag) {
      bufferCaptureTag = false;
      String tag = listToString(buffer);
      buffer.clear();

      if(tag.startsWith("/")) {
        tag = tag.substring(1);
        TextComponent component = new ClosingHtmlTagDecorator(new TextItem(tag));

        tree.pop();
        currentRoot = tree.peek();
        currentRoot.addChild(currentRoot.numChildren(), component);
      }
      else if(tag.endsWith("/")) {
        tag = tag.substring(0, tag.length() - 1);
        TextComponent component = new SelfClosingHtmlTagDecorator(new TextItem(tag));

        currentRoot.addChild(currentRoot.numChildren(), component);
      }
      else {
        TextComponent component = new OpeningHtmlTagDecorator(new TextItem(tag));
        TextComponent nextRoot = new TextComposite();

        currentRoot.addChild(currentRoot.numChildren(), component);
        currentRoot.addChild(currentRoot.numChildren(), nextRoot);
        currentRoot = nextRoot;
        tree.push(currentRoot);
      }
    }
    else {
      buffer.add(c);
    }
  }

  public TextComponent getResult() throws HtmlTagMismatchException {
    TextComponent root = tree.pop();
    DocumentValidator validator = DocumentValidator.getInstance();

    if(!tree.empty() || root == null || !validator.isValid(root)) {
      throw new HtmlTagMismatchException("Improperly formatted HTML");
    }

    return root;
  }

  private <T> String listToString(Iterable<T> list) {
    StringBuilder builder = new StringBuilder();

    for(T item : list) {
      builder.append(item);
    }

    return builder.toString();
  }
}
