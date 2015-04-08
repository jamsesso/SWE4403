package view.component;

import application.Logger;
import view.window.ConsoleWindow;
import view.window.Window;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class EditorStatusBar extends JPanel implements StatusBar, Observer {
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
  public void setStatusText(String text) {
    statusLabel.setText(text);
  }

  @Override
  public void update(Observable observable, Object message) {
    setStatusText(logger.getLatestLog());
  }
}
