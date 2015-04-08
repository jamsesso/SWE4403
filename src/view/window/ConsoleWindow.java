package view.window;

import application.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ConsoleWindow extends Window implements Observer {
  private final static Logger logger = Logger.getInstance();
  private JLabel label;

  public ConsoleWindow() {
    super("Editor Debug Log");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    logger.log(ConsoleWindow.class, "Opening debug log viewer.");

    // Set up the window.
    label = new JLabel();
    JScrollPane scrollPane = new JScrollPane(label, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    label.setVerticalAlignment(JLabel.TOP);

    // Build the contents of the label and register this object as an observer to the logger.
    drawConsole();
    logger.addObserver(this);

    // Add the console to the content pane.
    getContentPane().add(scrollPane, BorderLayout.CENTER);
  }

  private void drawConsole() {
    StringBuilder content = new StringBuilder();
    content.append("<html>");

    for(String message : logger.getLogs()) {
      content.append(message);
      content.append("<br />");
    }

    content.append("</html>");
    label.setText(content.toString());
  }

  @Override
  protected JMenuBar createMenuBar() {
    return null;
  }

  @Override
  protected Dimension createWindowSizeDimension() {
    return new Dimension(600, 400);
  }

  @Override
  protected LayoutManager createLayout() {
    return new BorderLayout();
  }

  @Override
  public void update(Observable observable, Object o) {
    drawConsole();
  }
}
