package view.component;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class MenuBarBuilder {
  private JMenuBar menuBar;
  private JMenu currentMenu;

  public MenuBarBuilder(JMenuBar menuBar) {
    this.menuBar = menuBar;
  }

  public MenuBarBuilder addMenu(String name) {
    JMenu menu = new JMenu(name);
    menuBar.add(menu);
    currentMenu = menu;

    return this;
  }

  public MenuBarBuilder addItem(String name) {
    return addItem(name, null, null);
  }

  public MenuBarBuilder addItem(String name, ActionListener clickEventCallback) {
    return addItem(name, clickEventCallback, null);
  }

  public MenuBarBuilder addItem(String name, ActionListener clickEventCallback, Integer mnemonic) {
    if(currentMenu != null) {
      JMenuItem item = new JMenuItem(name);
      item.addActionListener(clickEventCallback);

      if(mnemonic != null) {
        item.setAccelerator(KeyStroke.getKeyStroke(mnemonic, InputEvent.CTRL_MASK));
      }

      currentMenu.add(item);
    }

    return this;
  }

  public JMenuBar getResult() {
    return menuBar;
  }
}
