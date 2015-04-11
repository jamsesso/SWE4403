package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;

public class RedoCommand implements Command {
  private DocumentModelFacade facade;

  public RedoCommand(DocumentModelFacade facade) {
    this.facade = facade;
  }

  @Override
  public void execute() {
    facade.redo();
  }
}
