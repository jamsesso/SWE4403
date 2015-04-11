package swe4403.project.view.component;

import swe4403.project.backend.DocumentModelFacade;

public class UndoCommand implements Command {
  private DocumentModelFacade facade;

  public UndoCommand(DocumentModelFacade facade) {
    this.facade = facade;
  }

  @Override
  public void execute() {
    facade.undo();
  }
}
