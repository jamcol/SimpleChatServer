import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
 
/*
 * On a Mac system, I place the TextEdit text editor in the 
 * upper-left corner of the screen, and put a bunch of blank lines 
 * in the editor. Then I run this Java Robot example from 
 * Eclipse or the Unix command line.
 * 
 * It types the three strings shown in the code below into
 * the text editor.
 *
 * Many thanks to the people on the Mac Java-dev mailing list
 * for your help. 
 *
 */
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