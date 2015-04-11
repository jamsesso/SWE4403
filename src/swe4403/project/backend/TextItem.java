package swe4403.project.backend;

public class TextItem extends TextComponent {
  private String word;

  public TextItem(String word) {
    this.word = word;
  }

  @Override
  public String toString() {
    return word;
  }

  @Override
  public void setWord(String word) {
    this.word = word;
  }

  @Override
  public void accept(HtmlTreeVisitor visitor) {
    visitor.visitTextItem(this);
  }
}
