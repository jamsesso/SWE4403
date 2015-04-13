package swe4403.project.backend;

public class ShowHtmlTagVisitor extends HtmlTreeVisitor {
  @Override
  public void visitHtmlOpenTag(OpeningHtmlTagDecorator item) {
    item.show();
  }

  @Override
  public void visitHtmlClosingTag(ClosingHtmlTagDecorator item) {
    item.show();
  }

  @Override
  public void visitHtmlSelfClosingTag(SelfClosingHtmlTagDecorator item) {
    item.show();
  }
}
