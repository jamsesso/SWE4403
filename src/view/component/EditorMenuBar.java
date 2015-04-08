package view.component;

import application.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditorMenuBar extends JMenuBar {
  private final static Logger logger = Logger.getInstance();

  public EditorMenuBar() {
    MenuBarBuilder builder = new MenuBarBuilder(this);

    builder
      .addMenu("File")
        .addItem("New")
        .addItem("Open...", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
            logger.log(EditorMenuBar.class, "Opening new document...");
          }
        }, KeyEvent.VK_O)
        .addItem("Save")
        .addItem("Save As...")
        .addItem("Quit", new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
          }
        }, KeyEvent.VK_Q)
      .addMenu("Edit")
        .addItem("Copy")
        .addItem("Cut")
        .addItem("Paste")
        .addItem("Undo")
        .addItem("Redo")
      .addMenu("View")
        .addItem("Increase Font Size")
        .addItem("Decrease Font Size");
  }
}
