import java.applet.*;
import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
  public void paintComponent(Graphics grafObj) {
    super.paintComponent(grafObj);
    grafObj.setColor(Color.green);
    grafObj.fillOval(20, 20, 20, 20);
    grafObj.setColor(Color.red);
    grafObj.fillRect(60, 20, 40, 20);

  }
}