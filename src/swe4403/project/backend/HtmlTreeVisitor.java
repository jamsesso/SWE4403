package swe4403.project.backend;

abstract public class HtmlTreeVisitor {
  public void visitTextComposite(TextComposite item) { }

  public void visitTextItem(TextItem item) { }

  public void visitProxyProtectedTextItem(TextItemProtectionProxy item) { }

  public void visitHtmlOpenTag(OpeningHtmlTagDecorator item) { }

  public void visitHtmlClosingTag(ClosingHtmlTagDecorator item) { }

  public void visitHtmlSelfClosingTag(SelfClosingHtmlTagDecorator item) { }
}
