package application;

abstract public class TextItemDecorator extends TextItem {
  protected TextItem component;

  public TextItemDecorator(TextItem item) {
    super(item.toString());
    this.component = item;
  }

  abstract protected String decorate();

  @Override
  public String toString() {
    return decorate();
  }

  public TextComponent getOriginalTextItem() {
    return component;
  }
}
