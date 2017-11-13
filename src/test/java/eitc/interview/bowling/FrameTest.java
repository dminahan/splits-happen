package eitc.interview.bowling;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eitc.interview.bowling.exceptions.InvalidBonusThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForGame;

/**
 * Junit test for the Frame Class which keeps track of scoring for each frame and logic of if bonus throw is allowed.
 * 
 */
public class FrameTest {

	Frame frame=null;
	@Before
	public void setUp() throws Exception {
		frame=new Frame();
	}

	@After
	public void tearDown() throws Exception {
		frame=null;
	}

	@Test
	public void testGetCurrentThrow() {
		assertEquals(0,frame.getCurrentThrow());
		frame.setCurrentThrow(1);
		assertEquals(1,frame.getCurrentThrow());
	}

	@Test
	public void testSetCurrentThrow() {
		frame.setCurrentThrow(2);
		assertEquals(2,frame.getCurrentThrow());
	}

	@Test
	public void testGetSubScore() {
		assertEquals(0,frame.getSubScore());
		Throw roll=new Throw();
		roll.setPins(10);
		try {
			frame.addScore(roll);
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception when should not have.");
		}
		assertEquals(10,frame.getSubScore());
	}

	@Test
	public void testIsCurrentThrowStrike() {
		assertFalse(frame.hasStrike());
	}

	@Test
	public void testSetCurrentThrowStrike() {
		frame.setHasStrike(true);
		assertTrue(frame.hasStrike());
	}

	@Test
	public void testIsCurrentThrowSpare() {
		assertFalse(frame.hasSpare());
	}

	@Test
	public void testSetCurrentThrowSpare() {
		assertFalse(frame.hasSpare());
		frame.setHasSpare(true);
		assertTrue(frame.hasSpare());
	}

	@Test
	public void testGetBonusThrowCount() {
		assertEquals(0,frame.getBonusThrowCount());
		frame.setHasStrike(true);
		Throw roll=new Throw();
		roll.setPins(5);
		try {
			frame.addBonusThrow(roll);
			assertEquals(1,frame.getBonusThrowCount());
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame exception when should not have.");
		}
	}

	@Test
	public void testAddScore() {
		assertEquals(0,frame.getSubScore());
		try {
			throwForFrame(10);
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception when should not have.");
		}
		assertEquals(10,frame.getSubScore());
	}

	/**
	 * Test the bonus throw count incrementor.
	 */
	@Test
	public void testAddBonusThrow() {
		frame.setHasStrike(true);
		Throw roll=new Throw();
		roll.setPins(5);
		try {
			frame.addBonusThrow(roll);
			assertEquals(1,frame.getBonusThrowCount());
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame exception when should not have.");
		}
	}

	/** 
	 * Test the frame is done flag when the first ball is not a strike, so will not toggle until
	 * the second ball.  This does not include the bonus throw points that will get added.
	 */
	@Test
	public void testIsFrameDoneNoStrike() {
		assertFalse(frame.isFrameDone());
		try {
			throwForFrame(5);
			assertFalse(frame.isFrameDone());

			throwForFrame(5);
			//This is true, the frame is done even though spare.  More points still get 
			//added to it but this is tracked another way and in another test.
			assertTrue(frame.isFrameDone());
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception and should not have.");
		}
	}

	/** 
	 * Test the frame is done flag when the first ball is a strike, so one roll and frame is done.
	 * This does not include the bonus throw points that will get added.
	 */
	@Test
	public void testIsFrameDoneStrike() {
		assertFalse(frame.isFrameDone());
		try {
			throwForFrame(10);
			//This is true, the frame is done even though spare.  More points still get 
			//added to it but this is tracked another way and in another test.
			assertTrue(frame.isFrameDone());
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception and should not have.");
		}
	}

	/**
	 * Just test that the method to set the frame is done works.
	 */
	@Test
	public void testSetFrameDone() {
		frame.setFrameDone(true);
		assertTrue(frame.isFrameDone());
	}

	/**
	 * Test the basic logic of a spare does sum the totals correctly.
	 */
	@Test
	public void testScoreSpare(){
		try{
			throwForFrame(4);
			assertEquals(4,frame.getSubScore());
			throwForFrame(6);
			assertEquals(true,frame.hasSpare());
			
			//Just rolled a Spare on the frame so one bonus throw should get added to the frame's score
			Throw roll=new Throw();
			roll.setPins(8);
			frame.addBonusThrow(roll);
			
			assertEquals(18,frame.getSubScore());
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception when should not have.");
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame exception when should not have.");
		}
	}
	
	/**
	 * Test the basic logic to be used for strikes does sum the totals correctly.
	 */
	@Test
	public void testScoreStrike(){
		try{
			throwForFrame(10);
			assertEquals(10,frame.getSubScore());
			assertEquals(true,frame.hasStrike());
			Throw roll=new Throw();
			roll.setPins(4);
			frame.addBonusThrow(roll);
			
			//Just rolled a Spare on the frame so one bonus throw should get added to the frame's score
			roll=new Throw();
			roll.setPins(5);
			frame.addBonusThrow(roll);
			
			assertEquals(19,frame.getSubScore());
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception when should not have.");
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame exception when should not have.");
		}
	}

	@Test
	public void testPreventExtraRolls(){
		try{
			throwForFrame(4);
			assertEquals(4,frame.getSubScore());
			throwForFrame(5);
			assertEquals(9,frame.getSubScore());
			
			throwForFrame(4);
			//TODO: Need to figure out how to get this to very exception is thrown
	        fail("addScore() should've thrown an exception as this is the 3rd roll and not a spare or strike!");
	           			
		} catch (InvalidThrowForFrame e) {
			//Expected this exception so do nothing so junit will pass
		}
		
	}
	
	@Test
	public void testPreventBonusRoll(){
		try{
			throwForFrame(4);
			assertEquals(4,frame.getSubScore());
			throwForFrame(5);
			
			Throw roll=new Throw();
			roll.setPins(4);
			frame.addBonusThrow(roll);
			//TODO: Need to figure out how to get this to very exception is thrown
	        fail("addBonusThrow() should've thrown an exception as this was not a frame that should have allowed it.");
	           			
		} catch (InvalidThrowForFrame e) {
	        fail("addScore() threw an exception for some reason and should not have!");
		} catch (InvalidBonusThrowForFrame e) {
			//Expected this exception so do nothing so junit will pass
		}
		
	}
	
	@Test
	public void testPreventInvalidSpareScore(){
		try{
			throwForFrame(4);
			assertEquals(4,frame.getSubScore());
			throwForFrame(6);
			assertEquals(true,frame.hasSpare());
			
			//Just rolled a Spare on the frame so one bonus throw should get added to the frame's score
			Throw roll=new Throw();
			roll.setPins(8);
			frame.addBonusThrow(roll);
			
			assertEquals(18,frame.getSubScore());
			
			//Add in invalid Throw and this should throw an exception
			roll=new Throw();
			roll.setPins(1);
			frame.addBonusThrow(roll);
			
			fail("addBonusThrow() should have thrown an exception on a second ball added in for a spare.");
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception when should not have.");
		} catch (InvalidBonusThrowForFrame e) {
			//Expected this exception so do nothing so junit will pass
		}
	}
	
	/** 
	 * Test the needs bonus throws indicator when a strike has happened.
	 */
	@Test
	public void testNeedsBonusThrowStrike() {
		//just starting so frame is not done and no bonus throws have been indicated yet.
		assertFalse(frame.isFrameDone());
		assertFalse(frame.needsBonusThrows());
		try {
			throwForFrame(10);
			//This is true, the frame is done even though spare.  More points still get 
			//added to it but this is tracked another way and in another test.
			assertTrue(frame.isFrameDone());
			assertTrue(frame.needsBonusThrows());
			assertEquals(10,frame.getSubScore());
			
			//Add in first bonus throw
			Throw roll=new Throw();
			roll.setPins(5);
			frame.addBonusThrow(roll);
			assertTrue(frame.isFrameDone());
			assertTrue(frame.needsBonusThrows());
			assertEquals(15,frame.getSubScore());

			roll=new Throw();
			roll.setPins(4);
			frame.addBonusThrow(roll);
			assertTrue(frame.isFrameDone());
			assertFalse(frame.needsBonusThrows());
			assertEquals(19,frame.getSubScore());
			
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception and should not have.");
		}  catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame exception and should not have.");
		}
	}

	/** 
	 * Test the needs bonus throws indicator when a spare has happened.
	 */
	@Test
	public void testNeedsBonusThrowSpare() {
		//just starting so frame is not done and no bonus throws have been indicated yet.
		assertFalse(frame.isFrameDone());
		assertFalse(frame.needsBonusThrows());
		try {
			throwForFrame(5);
			assertFalse(frame.isFrameDone());
			assertFalse(frame.needsBonusThrows());
			assertEquals(5,frame.getSubScore());
			
			throwForFrame(5);
			//This is true, the frame is done even though spare.  More points still get 
			//added to it but this is tracked another way and in another test.
			assertTrue(frame.isFrameDone());
			assertTrue(frame.needsBonusThrows());
			assertEquals(10,frame.getSubScore());
			
			//Add in the one and only bonus throw
			Throw roll=new Throw();
			roll.setPins(5);
			frame.addBonusThrow(roll);
			assertTrue(frame.isFrameDone());
			assertFalse(frame.needsBonusThrows());
			assertEquals(15,frame.getSubScore());
			
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception and should not have.");
		}  catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame exception and should not have.");
		}
	}


	/** 
	 * Test the needs bonus throws indicator during a non strike or non spare frame.
	 */
	@Test
	public void testNeedsNoBonusThrowFrame() {
		//just starting so frame is not done and no bonus throws have been indicated yet.
		assertFalse(frame.isFrameDone());
		assertFalse(frame.needsBonusThrows());
		try {
			throwForFrame(5);
			assertFalse(frame.isFrameDone());
			assertFalse(frame.needsBonusThrows());
			assertEquals(5,frame.getSubScore());
			
			throwForFrame(4);
			assertTrue(frame.isFrameDone());
			assertFalse(frame.needsBonusThrows());
			assertEquals(9,frame.getSubScore());
			
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidThrowForFrame exception and should not have.");
		}
	}


	/**
	 * Method to help with the logic around the construction of a Throw for a Frame in the game.
	 * @param pins
	 * @throws InvalidThrowForGame
	 */
	public void throwForFrame(int pins) throws InvalidThrowForFrame {
		Throw roll=new Throw();
		roll.setPins(pins);
		frame.addScore(roll);
	}
	
}
