package swe4403.project.backend;

public class OpeningHtmlTagDecorator extends TextItemDecorator {
  public OpeningHtmlTagDecorator(TextItem component) {
    super(component);
  }

  @Override
  protected String decorate() {
    return "<" + component.toString() + ">";
  }
}