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
            String document;

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

            FileWriter fileWriter = new FileWriter(saveToFile);
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
