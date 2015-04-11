package swe4403.project.backend;

public class ClosingHtmlTagDecorator extends TextItemDecorator {
  public ClosingHtmlTagDecorator(TextItem component) {
    super(component);
  }

  @Override
  protected String decorate() {
    return "</" + component.toString() + ">";
  }
}