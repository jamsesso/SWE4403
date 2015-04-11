package swe4403.project.backend;

import java.util.ArrayList;
import java.util.List;

public class ReadOnlyTextItemVisitor extends HtmlTreeVisitor {
  private List<TextComponent> componentList = new ArrayList<TextComponent>();

  @Override
  public void visitProxyProtectedTextItem(TextItemProtectionProxy item) {
    if(item.getPolicy().equals(ProxyPolicy.READ_ONLY)) {
      componentList.add(item);
    }
  }

  public List<TextComponent> getVisitedElements() {
    return componentList;
  }
}
