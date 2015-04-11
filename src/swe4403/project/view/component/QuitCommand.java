package swe4403.project.view.component;

public class QuitCommand implements Command {
  @Override
  public void execute() {
    System.exit(0);
  }
}
