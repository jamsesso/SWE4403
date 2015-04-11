package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;
import swe4403.project.backend.Logger;

public class SaveFileCommand implements Command {
  private static final Logger logger = Logger.getInstance();

  private DocumentModelFacade facade;

  public SaveFileCommand(DocumentModelFacade facade) {
    this.facade = facade;
  }

  @Override
  public void execute() {
    String finalDoc = facade.getDocument();
    logger.log(SaveFileCommand.class, "Saving: " + finalDoc);
  }
}
