package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.Logger;
import swe4403.project.view.window.Window;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class OpenFileCommand implements Command {
  private static final Logger logger = Logger.getInstance();

  private Window window;
  private DocumentModelFacade facade;

  public OpenFileCommand(Window parent, DocumentModelFacade facade) {
    this.window = parent;
    this.facade = facade;
  }

  @Override
  public void execute() {
    JFileChooser fileChooser = new JFileChooser();
    Integer returnValue = fileChooser.showOpenDialog(window);

    if(returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      Scanner scanner = null;
      String result = "";

      logger.log(OpenFileCommand.class, "Chose file: " + file.getName());

      try {
        scanner = new Scanner(file).useDelimiter("\\Z");
        result = scanner.next();
      }
      catch(IOException e) {
        logger.log(OpenFileCommand.class, "Unable to read file: " + e.getMessage());
      }
      catch(NoSuchElementException e) {
        logger.log(OpenFileCommand.class, "Empty file! " + e.getMessage());
      }
      finally {
        if(scanner != null) {
          scanner.close();
        }
      }

      facade.clearEditHistory();
      facade.setFileSaveLocation(file);
      facade.update(result, true);
    }
    else {
      logger.log(OpenFileCommand.class, "Open file operation aborted.");
    }
  }
}
