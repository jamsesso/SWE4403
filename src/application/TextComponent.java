package application;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract public class TextComponent {
  private List<TextComponent> empty = new ArrayList<TextComponent>(0);

  @Override
  abstract public String toString();

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
}
