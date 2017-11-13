package eitc.interview.bowling;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

/**
 * Junit test for the Throw Class which is the actual rolling of the ball
 * 
 */
public class ThrowTest  extends TestCase {

	protected Throw ballRoll;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ballRoll=new Throw();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ballRoll=null;
	}
	
	public void testSetPins(){
		ballRoll.setPins(5);
		
		assertEquals(5, ballRoll.getPins());
	}
	
	public void testGetPins(){
		assertEquals(0, ballRoll.getPins());
		ballRoll.setPins(10);
		
		assertEquals(10, ballRoll.getPins());
		
	}
	

}
