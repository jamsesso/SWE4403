package view.window;

import javax.swing.*;
import java.awt.*;

abstract public class Window extends JFrame {
  abstract protected JMenuBar createMenuBar();
  abstract protected Dimension createWindowSizeDimension();
  abstract protected LayoutManager createLayout();

  public Window(String title) {
    super();
    setTitle(title);
    setSize(createWindowSizeDimension());
    setJMenuBar(createMenuBar());
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setLayout(createLayout());
  }
}
