package swe4403.project.backend;

public class HideHtmlTagVisitor extends HtmlTreeVisitor {
  @Override
  public void visitHtmlOpenTag(OpeningHtmlTagDecorator item) {
    item.hide();
  }

  @Override
  public void visitHtmlClosingTag(ClosingHtmlTagDecorator item) {
    item.hide();
  }

  @Override
  public void visitHtmlSelfClosingTag(SelfClosingHtmlTagDecorator item) {
    item.hide();
  }
}
