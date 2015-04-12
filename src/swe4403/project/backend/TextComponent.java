package swe4403.project.backend;

import java.util.ArrayList;
import java.util.List;

abstract public class TextComponent {
  private List<TextComponent> empty = new ArrayList<TextComponent>(0);

  @Override
  abstract public String toString();

  abstract public void accept(HtmlTreeVisitor visitor);

  public void setWord(String word) {
    // Nothing to do.
  }

  public void addChild(Integer index, TextComponent textComponent) {
    // Nothing to do.
  }

  public void removeChild(Integer index) {
    // Nothing to do.
  }

  public TextComponent getChild(Integer index) {
    return null;
  }

  public List<TextComponent> getChildren() {
    return empty;
  }

  public Integer numChildren() {
    return 0;
  }

  public Iterator<TextComponent> createIterator() {
    return new HtmlTreeIterator(this);
  }
}
