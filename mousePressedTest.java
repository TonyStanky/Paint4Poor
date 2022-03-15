import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mousePressedTest extends Application{
  
  volatile private boolean mouseDown = false;
  
  public void start(Stage primaryStage) {
  
  }

  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      mouseDown = true;
      initThread();
    }
  }

  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      mouseDown = false;
    }
  }

  volatile private boolean isRunning = false;
  private synchronized boolean checkAndMark() {
    if (isRunning) return false;
    isRunning = true;
    return true;
  }
  private void initThread() {
    if (checkAndMark()) {
      new Thread() {
        public void run() {
          do {
            System.out.print('P');
          } while (mouseDown);
          isRunning = false;
        }
      }.start();
    }
  }
  
}
