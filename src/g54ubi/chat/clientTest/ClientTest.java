import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
 

public class ClientTest
{
  Robot robot = new Robot();
 
  public static void main(String[] args) throws AWTException
  { 
    new ClientTest();
  }
   
  public ClientTest() throws AWTException
  {
    robot.setAutoDelay(40);
    robot.setAutoWaitForIdle(true);
    robot.delay(4000);
   
 
  
 
    robot.delay(500);
    robot.keyPress(KeyEvent.VK_SHIFT);    
    type("IDEN ");
    robot.keyRelease(KeyEvent.VK_SHIFT);
    type("james");
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    // this should log the user in as James
    
    robot.delay(1000);    
    
    
    robot.keyPress(KeyEvent.VK_SHIFT);    
    type("HAIL ");
    robot.keyRelease(KeyEvent.VK_SHIFT);
    type("Hello World");
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    
    robot.delay(1000);
    
    robot.keyPress(KeyEvent.VK_SHIFT);    
    type("STAT");
    robot.keyRelease(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    
   robot.delay(1000);
   
   robot.keyPress(KeyEvent.VK_SHIFT);    
   type("LIST");
   robot.keyRelease(KeyEvent.VK_SHIFT);
   robot.keyPress(KeyEvent.VK_ENTER);
   robot.keyRelease(KeyEvent.VK_ENTER);
       
    
    
     
    
     
    
 
    robot.delay(1000);
    System.exit(0);
  }
   
  private void leftClick()
  {
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.delay(200);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    robot.delay(200);
  }
  
  private void rightClick(){
	  robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.delay(200);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);
	    robot.delay(200);
  }
   

  private void type(String s)
  {
    byte[] bytes = s.getBytes();
    for (byte b : bytes)
    {
      int code = b;
      if (code > 96 && code < 123) code = code - 32;
      robot.delay(40);
      robot.keyPress(code);
      robot.keyRelease(code);
    }
  }
}