
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class ConnectionTest {

	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	
	
	@Before // setup 
	public void setUp() throws Exception { 
		socket = new Socket("localhost", 9000);
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWriter = new PrintWriter(socket.getOutputStream(), true);
	}

	@After //  close the test down when they
	//have finished and display quit so we know
	public void destroy() throws Exception {
		System.out.println("quit");
		printWriter.println("QUIT");
		socket.close();
	}
	
	@Test //testing if the client and server are connected
	public void connectionTest() throws Exception{
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.startsWith("OK"));
		//if "OK" is returned the connection is ongoing
		}
	
	
	@Test //testing the initial login before anything else happens - should be "OK"
	public void testLogin() throws Exception {
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//logging in as myself
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.startsWith("OK"));
		//"OK" is the first word shown when the Login is successful
	}
	
	@Test //testing an initial login then another immediately afterwards - should be "BAD"
	public void testLoginThenLogin() throws Exception{
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//logging in as myself
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//logging in as someone else
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.startsWith("BAD"));
		//BAD should be shown as you can't login as 2 separate people
	}
	
	@Test //test for logging in and broadcasting to all
	public void testLoginThenHail() throws Exception {
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//Logging in so that we can HAIL 
		bufferedReader.readLine();
		printWriter.println("HAIL yoid mahoid");
		//send a message to everyone
		String linetoCheck = bufferedReader.readLine();
		Assert.assertTrue(linetoCheck.startsWith("Broadcast"));
		//if it starts with "Broadcast" everyone can see it therefore it works
	}
	
	@Test //test for logging in and then LIST
	public void testLoginThenList() throws Exception {
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//Logging in before we call LIST
		bufferedReader.readLine();
		printWriter.println("LIST");
		//show the list of people online
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.startsWith("OK"));
		//if it starts with OK people are online and it returns the names
	}
	
	@Test //test for logging in then calling STAT returns OK if working
	public void testLoginThenStat() throws Exception{
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//Logging in before we call STAT
		bufferedReader.readLine();
		printWriter.println("STAT");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.indexOf("are logged") != -1);
	}

	@Test //test for logging in then quitting
	public void testLoginThenQuit() throws Exception{
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//logging in as myself before calling QUIT
		bufferedReader.readLine();
		printWriter.println("QUIT");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.startsWith("OK thank you for sending"));
	}
	
	
	
	 /*	@Test //test for logging in then messaging a specific person
	public void testLoginThenMessage() throws Exception{
		bufferedReader.readLine();
		printWriter.println("IDEN James");
		//logging in as myself before logging in as someone else
		bufferedReader2.readLine();
		printWriter2.println("IDEN Rob");
		//logging in as someone else to send a message to
		
	 } */
	 
	@Test //test for HAIL before logging in
	public void testHailBeforeLogin() throws Exception{
		bufferedReader.readLine();
		printWriter.println("HAIL yoid mahoid");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.equals("BAD You have not logged in yet"));
	}
	
	@Test //test for LIST before logging in
	public void testListBeforeLogin() throws Exception{
		bufferedReader.readLine();
		printWriter.println("LIST");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.startsWith("BAD"));
	}
	
	@Test //test for STAT before logging in
	public void testStatBeforeLogin() throws Exception {
		bufferedReader.readLine();
		printWriter.println("STAT");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.indexOf("not logged") != -1);
	}
	
	@Test //test for quitting before login
	public void testQuitBeforeLogin() throws Exception {
		bufferedReader.readLine();
		printWriter.println("QUIT");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.equals("OK goodbye"));
	}
	
	@Test //testing for a non-command string
	public void testingRandomStringBeforeLogin() throws Exception {
		bufferedReader.readLine();
		printWriter.println("YOIDBOY");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.equals("BAD command not recognised"));
	}
	
	@Test
	public void testingRandomStringAfterLogin() throws Exception {
		bufferedReader.readLine();
		printWriter.println("YOIDMAN");
		String lineToCheck = bufferedReader.readLine();
		Assert.assertTrue(lineToCheck.equals("BAD command not recognised"));
	}
}
