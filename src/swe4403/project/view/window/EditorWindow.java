package swe4403.project.view.window;

import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.Logger;
import swe4403.project.view.component.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class EditorWindow extends Window {
  private static final Logger logger = Logger.getInstance();

  private DocumentModelFacade documentModel = new DocumentModelFacade();

  public EditorWindow() {
    super("HTML Editor");

    // Set up the status bar.
    getContentPane().add(new EditorStatusBar(), BorderLayout.SOUTH);

    // Set up the editor.
    JTextPane textPane = new TextEditorPane(documentModel);
    JScrollPane scrollTextEditor = new JScrollPane(textPane);
    getContentPane().add(scrollTextEditor, BorderLayout.CENTER);

    // Build the menu bar.
    MenuBarBuilder menuBarBuilder = new MenuBarBuilder();

    menuBarBuilder
      .addMenu("File")
      .addItem("Open...", new OpenFileCommand(this, documentModel), KeyEvent.VK_O)
      .addItem("Save", new SaveFileCommand(this, documentModel), KeyEvent.VK_S)
      .addItem("Save As...", new SaveAsFileCommand(this, documentModel))
      .addItem("Quit", new QuitCommand(), KeyEvent.VK_Q)
      .addMenu("Edit")
      .addItem("Undo", new UndoCommand(documentModel), KeyEvent.VK_Z)
      .addItem("Redo", new RedoCommand(documentModel), KeyEvent.VK_Y)
      .addMenu("View")
      .addItem("Show HTML Tags", new ShowHtmlTagsCommand(documentModel, textPane))
      .addItem("Hide HTML Tags", new HideHtmlTagsCommand(documentModel, textPane));

    setJMenuBar(menuBarBuilder.getResult());
    logger.log(EditorWindow.class, "Editor is ready.");
  }

  @Override
  protected Dimension createWindowSizeDimension() {
    return new Dimension(650, 550);
  }

  @Override
  protected LayoutManager createLayout() {
    return new BorderLayout();
  }
}
