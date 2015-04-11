package swe4403.project.backend;

import java.util.ArrayList;
import java.util.List;

public class HtmlTreeIterator implements Iterator<TextComponent> {
  private List<TextComponent> flattenedTree = new ArrayList<TextComponent>();
  private Integer index = 0;

  public HtmlTreeIterator(TextComponent root) {
    flatten(root);
  }

  @Override
  public void first() {
    index = 0;
  }

  @Override
  public void next() {
    index++;
  }

  @Override
  public TextComponent currentItem() {
    return flattenedTree.get(index);
  }

  @Override
  public Boolean isDone() {
    return index >= flattenedTree.size();
  }

  private void flatten(TextComponent tree) {
    flattenedTree.add(tree);

    for(Integer i = 0; i < tree.numChildren(); i++) {
      flatten(tree.getChild(i));
    }
  }
}
