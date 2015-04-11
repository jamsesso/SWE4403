package swe4403.project.view.component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class MenuBarBuilder {
  private JMenuBar menuBar;
  private JMenu currentMenu;

  public MenuBarBuilder() {
    menuBar = new JMenuBar();
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

  public MenuBarBuilder addItem(String name, Command clickEventCallback) {
    return addItem(name, clickEventCallback, null);
  }

  public MenuBarBuilder addItem(String name, final Command clickEventCallback, Integer mnemonic) {
    if(currentMenu != null) {
      JMenuItem item = new JMenuItem(name);
      item.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
          clickEventCallback.execute();
        }
      });

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
