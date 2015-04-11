package swe4403.project.view.component;

import swe4403.project.backend.DocumentEditException;
import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.HtmlTagMismatchException;
import swe4403.project.backend.Logger;
import swe4403.project.view.window.Window;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileCommand implements Command {
  private static final Logger logger = Logger.getInstance();

  private Window window;
  private DocumentModelFacade facade;

  public SaveFileCommand(Window parent, DocumentModelFacade facade) {
    this.window = parent;
    this.facade = facade;
  }

  @Override
  public void execute() {
    String document;
    File saveLocation = facade.getFileSaveLocation();

    try {
      document = facade.getDocument();
    }
    catch(HtmlTagMismatchException e) {
      logger.log(SaveFileCommand.class, "Unable to save file because the HTML was not well formed: " + e.getMessage());
      JOptionPane.showMessageDialog(window, "Cannot save file: The HTML is not well formed.");
      return;
    }
    catch(DocumentEditException e) {
      logger.log(SaveFileCommand.class, "Unable to save file because the text was changed: " + e.getMessage());
      JOptionPane.showMessageDialog(window, "Cannot save file: Only HTML tags can be edited, not regular text.");
      return;
    }

    if(saveLocation == null || !saveLocation.canWrite()) {
      logger.log(SaveFileCommand.class, "TODO: Open save as dialog.");
    }
    else {
      try {
        FileWriter fileWriter = new FileWriter(saveLocation);
        fileWriter.write(document);
        fileWriter.close();
        logger.log(SaveFileCommand.class, "File saved successfully!");
      }
      catch(IOException e) {
        logger.log(SaveFileCommand.class, "IOException occurred while saving file: " + e.getMessage());
      }
    }
  }
}
