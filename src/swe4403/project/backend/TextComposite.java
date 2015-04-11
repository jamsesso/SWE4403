package swe4403.project.backend;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends TextComponent {
  private List<TextComponent> children = new ArrayList<TextComponent>();

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for(TextComponent child : children) {
      builder.append(child.toString());
    }

    return builder.toString();
  }

  @Override
  public void addChild(Integer index, TextComponent textComponent) {
    children.add(index, textComponent);
  }

  @Override
  public void removeChild(Integer index) {
    int primitiveIndex = index; // Need to unbox the Integer object because List.remove will look for an Integer object.
    children.remove(primitiveIndex);
  }

  @Override
  public TextComponent getChild(Integer index) {
    return children.get(index);
  }

  @Override
  public List<TextComponent> getChildren() {
    return children;
  }

  @Override
  public Integer numChildren() {
    return children.size();
  }

  @Override
  public void accept(HtmlTreeVisitor visitor) {
    visitor.visitTextComposite(this);
  }
}
