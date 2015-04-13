package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;

import javax.swing.*;

public class ShowHtmlTagsCommand implements Command {
  private DocumentModelFacade facade;
  private JTextPane textPane;

  public ShowHtmlTagsCommand(DocumentModelFacade facade, JTextPane textPane) {
    this.textPane = textPane;
    this.facade = facade;
  }

  @Override
  public void execute() {
    facade.showHtmlTags();
    textPane.setEnabled(true);
  }
}
