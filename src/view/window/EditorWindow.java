package view.window;

import application.Logger;
import view.component.EditorMenuBar;
import view.component.EditorStatusBar;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends Window {
  private static final Logger logger = Logger.getInstance();

  public EditorWindow() {
    super("HTML Editor");

    // Set up the status bar.
    getContentPane().add(new EditorStatusBar(), BorderLayout.SOUTH);
    logger.log(EditorWindow.class, "Editor is ready.");

    // Set up the editor.
    JTextPane textPane = new JTextPane();
  }

  @Override
  protected JMenuBar createMenuBar() {
    return new EditorMenuBar();
  }

  @Override
  protected Dimension createWindowSizeDimension() {
    return new Dimension(300, 300);
  }

  @Override
  protected LayoutManager createLayout() {
    return new BorderLayout();
  }
}
