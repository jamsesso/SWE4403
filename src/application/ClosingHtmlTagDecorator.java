package application;

public class ClosingHtmlTagDecorator extends TextItemDecorator {
  public ClosingHtmlTagDecorator(TextItem component) {
    super(component);
  }

  @Override
  protected String decorate() {
    return "</" + component.toString() + ">";
  }
}
