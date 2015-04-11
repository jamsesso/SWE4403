package swe4403.project.view.component;

import swe4403.project.backend.Logger;
import swe4403.project.view.window.ConsoleWindow;
import swe4403.project.view.window.Window;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class EditorStatusBar extends JPanel implements Observer {
  private final static Logger logger = Logger.getInstance();
  private JLabel statusLabel;

  public EditorStatusBar() {
    logger.addObserver(this);

    setBorder(new BevelBorder(BevelBorder.LOWERED));
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    statusLabel = new JLabel();
    statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

    add(statusLabel);

    // Handle click events.
    addMouseListener(new MouseInputAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        Window consoleWindow = new ConsoleWindow();
        consoleWindow.setVisible(true);
      }
    });
  }

  @Override
  public void update(Observable observable, Object message) {
    this.statusLabel.setText(logger.getLatestLog());
  }
}
