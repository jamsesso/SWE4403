package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;

import javax.swing.*;
import java.awt.*;

public class HideHtmlTagsCommand implements Command {
  private DocumentModelFacade facade;
  private JTextPane textPane;

  public HideHtmlTagsCommand(DocumentModelFacade facade, JTextPane textPane) {
    this.textPane = textPane;
    this.facade = facade;
  }

  @Override
  public void execute() {
    facade.hideHtmlTags();
    textPane.setDisabledTextColor(Color.DARK_GRAY);
    textPane.setEnabled(false);
  }
}
