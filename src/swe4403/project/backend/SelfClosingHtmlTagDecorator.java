package swe4403.project.backend;

public class SelfClosingHtmlTagDecorator extends TextItemDecorator {
  public SelfClosingHtmlTagDecorator(TextItem component) {
    super(component);
  }

  @Override
  protected String decorate() {
    return "<" + component.toString() + "/>";
  }

  @Override
  public void accept(HtmlTreeVisitor visitor) {
    visitor.visitHtmlSelfClosingTag(this);
  }
}
