package swe4403.project.view.window;

import swe4403.project.backend.Logger;
import swe4403.project.view.component.EditorStatusBar;
import swe4403.project.view.component.MenuBarBuilder;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends Window {
  private static final Logger logger = Logger.getInstance();

  public EditorWindow() {
    super("HTML Editor");

    // Set up the status bar.
    getContentPane().add(new EditorStatusBar(), BorderLayout.SOUTH);
    logger.log(EditorWindow.class, "Editor is ready.");

    // Build the menu bar.
    MenuBarBuilder menuBarBuilder = new MenuBarBuilder();

    menuBarBuilder
      .addMenu("File")
      .addItem("New")
      .addItem("Open...")
      .addItem("Save")
      .addItem("Save As...")
      .addItem("Quit")
      .addMenu("Edit")
      .addItem("Copy")
      .addItem("Cut")
      .addItem("Paste")
      .addItem("Undo")
      .addItem("Redo")
      .addMenu("View")
      .addItem("Increase Font Size")
      .addItem("Decrease Font Size");

    setJMenuBar(menuBarBuilder.getResult());

    // Set up the editor.
    JTextPane textPane = new JTextPane();
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
