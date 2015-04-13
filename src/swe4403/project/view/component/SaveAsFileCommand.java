package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.Logger;
import swe4403.project.view.window.Window;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAsFileCommand implements Command {
  private static final Logger logger = Logger.getInstance();

  private DocumentModelFacade facade;
  private Window window;

  public SaveAsFileCommand(Window parent, DocumentModelFacade facade) {
    this.window = parent;
    this.facade = facade;
  }

  @Override
  public void execute() {
    logger.log(SaveAsFileCommand.class, "Saving new document.");
    JFileChooser fileChooser = new JFileChooser();
    Integer returnValue = fileChooser.showSaveDialog(window);

    if(returnValue == JFileChooser.APPROVE_OPTION) {
      try {
        File saveToFile = fileChooser.getSelectedFile();

        if(!saveToFile.exists()) {
          Boolean created = saveToFile.createNewFile();

          if(created) {
            FileWriter fileWriter = new FileWriter(saveLocation);
            fileWriter.write(document);
            fileWriter.close();
            logger.log(SaveFileCommand.class, "File saved successfully!");
          }
          else {
            JOptionPane.showMessageDialog(window, "Cannot save file: Unable to create new file.");
          }
        }
      }
      catch(IOException e) {
        JOptionPane.showMessageDialog(window, "Cannot save file: " + e.getMessage());
        logger.log(SaveAsFileCommand.class, "Unable to save file: " + e.getMessage());
      }
    }
    else {
      logger.log(SaveAsFileCommand.class, "Save as operation was cancelled.");
    }
  }
}
