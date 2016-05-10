import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;

public class HelloWorld3 extends Applet {
  public void paint(Graphics grafObj) {
    grafObj.setColor(Color.green);
    grafObj.fillOval(20, 20, 20, 20);
    grafObj.setColor(Color.red);
    grafObj.fillRect(60, 20, 40, 20);
    String myPara1 = getParameter("shape");
    String myPara2 = getParameter("color");
    switch(myPara2)
    {
      case "blue": grafObj.setColor(Color.blue); break;
      default: grafObj.setColor(Color.black); break;
    }
    switch(myPara1)
    {
      case "Rect": grafObj.fillRect(120, 20, 40, 20); break;
      case "Oval": grafObj.fillOval(120, 20, 20, 20); break;
      default: break;
    }
    }
}