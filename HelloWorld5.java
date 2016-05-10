import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;

public class HelloWorld5 extends Applet {
  public int i, j, t;
  public int x, y, g;
  public void paint(Graphics grafObj) {
    grafObj.setColor(Color.black);
    grafObj.drawLine(6, 6, 308, 6);
    grafObj.drawLine(6, 6, 6, 108);
    grafObj.drawLine(6, 108, 308, 108);
    grafObj.drawLine(308, 6, 308, 108);
    grafObj.setColor(Color.red);
    while(true)
    {
      t ++;
      if(t == 30)
      {
        t = 0;
        g ++;
        if(g > 4)
          g = 0;
        switch(g)
        {
          case 0: grafObj.setColor(Color.red); break;
          case 1: grafObj.setColor(Color.green); break;
          case 2: grafObj.setColor(Color.blue); break;
          case 3: grafObj.setColor(Color.gray); break;
          case 4: grafObj.setColor(Color.yellow); break;
          default: grafObj.setColor(Color.black); break;
        }
      }
      try 
      {
        Thread.sleep(90);
      } catch (InterruptedException e) 
      {
        return;
      }
      grafObj.clearRect(7, 7, 300, 100);
      grafObj.fillOval(x, y, 20, 20);
      x = x + i;
      y = y + j;
      if (x == 287 || x == 7)
        i = - i;
      if (y == 87 || y == 7)
        j = - j;
    }
  }
  public void init()
  {
    t = 0;
    i = 20;
    j = 10;
    x = 7;
    y = 7;
    g = 0;
  }
}