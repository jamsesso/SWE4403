package application;

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
}
