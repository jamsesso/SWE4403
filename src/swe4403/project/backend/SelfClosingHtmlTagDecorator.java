package swe4403.project.backend;

public class SelfClosingHtmlTagDecorator extends TextItemDecorator {
  private Boolean hidden = false;

  public SelfClosingHtmlTagDecorator(TextItem component) {
    super(component);
  }

  @Override
  protected String decorate() {
    return (hidden) ? "" : "<" + component.toString() + "/>";
  }

  public void hide() {
    hidden = true;
  }

  public void show() {
    hidden = false;
  }

  @Override
  public void accept(HtmlTreeVisitor visitor) {
    visitor.visitHtmlSelfClosingTag(this);
  }
}
