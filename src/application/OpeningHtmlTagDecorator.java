package application;

public class OpeningHtmlTagDecorator extends TextItemDecorator {
  public OpeningHtmlTagDecorator(TextItem component) {
    super(component);
  }

  @Override
  protected String decorate() {
    return "<" + component.toString() + ">";
  }
}
