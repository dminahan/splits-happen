package eitc.interview.bowling;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eitc.interview.bowling.exceptions.InvalidBonusThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForGame;

/**
 * Junit test for the Bowling Game Class which keeps track of all the frames and adds in the throws/rolls to each frame
 * 
 */
public class BowlingGameTest {
	
	private BowlingGame game;

	@Before
	public void setUp() throws Exception {
		game=new BowlingGame();
	}

	@After
	public void tearDown() throws Exception {
		game=null;
	}

	/**
	 * Method to test turn method creating frame, augmenting ball count, and ball addinging into score.
	 * 
	 */
	@Test
	public void testTurn() {
		try {
			assertEquals(0,game.getCurrentBall());
			rollBallForFrame(8);
			assertEquals(1,game.getCurrentBall());
			assertEquals(8,game.getScore());
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}

	/** Test of score retrieval method from frame 
	 *  
	 */
	@Test
	public void testScore() {
		try {
			rollBallForFrame(10);
			assertEquals(10,game.getScore());
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
	}
	
	/**
	 * Test of summing of scoring across 2 frames
	 */
	@Test
	public void testTwoFrameScoring() {
		try {
			assertEquals(1, game.getCurrentFrame());
			//First Frame
			rollBallForFrame(8);
			rollBallForFrame(1);
			assertEquals(2,game.getCurrentFrame());
			
			//Second Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(3,game.getCurrentFrame());
			
			assertEquals(16,game.getScore());
			
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}
	
	/**
	 * Test to check transition of frames including if a strike happens
	 */
	@Test
	public void testGetCurrentFrame() {
		//Currently on First Frame
		assertEquals(1,game.getCurrentFrame());
		try {
			rollBallForFrame(5);
			assertEquals(1,game.getCurrentFrame());
			rollBallForFrame(4);
			//First Frame done and now on Second Frame
			assertEquals(2,game.getCurrentFrame());
			rollBallForFrame(3);
			assertEquals(2,game.getCurrentFrame());
			rollBallForFrame(6);
			//Second Frame done and now on Third Frame
			assertEquals(3, game.getCurrentFrame());
			rollBallForFrame(10);
			//Third Frame done because a strike happened and now on Fourth Frame
			assertEquals(4, game.getCurrentFrame());
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
	}

	/**
	 * Test Ending of Game indicator
	 */
	@Test
	public void testEndOfGameIndicator() {
		try {
			assertEquals(1, game.getCurrentFrame());
			//First Frame
			rollBallForFrame(8);
			rollBallForFrame(1);
			assertEquals(2,game.getCurrentFrame());
			
			//Second Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(3,game.getCurrentFrame());
			
			//Third frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(4,game.getCurrentFrame());
			
			//Forth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(5,game.getCurrentFrame());
			
			//Fifth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(6,game.getCurrentFrame());
			
			//Six Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(7,game.getCurrentFrame());
			
			//Seventh Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(8,game.getCurrentFrame());
			
			//Eigth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(9,game.getCurrentFrame());
			
			//Nineth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(10,game.getCurrentFrame());
			
			//Tenth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			
			//Test Game Ended
			assertEquals(true,game.isGameFinished());
			
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}
	
	/**
	 * Test Spare Scoring
	 */
	@Test
	public void testSpareScoring() {
		try {
			//First Frame
			rollBallForFrame(8);
			rollBallForFrame(2);
			assertEquals(10,game.getScore());
			
			//Second Frame
			rollBallForFrame(3);
			assertEquals(2,game.getCurrentFrame());
			assertEquals(16,game.getScore());
			rollBallForFrame(4);
			assertEquals(20,game.getScore());
			assertEquals(3,game.getCurrentFrame());
			
			//Third frame
			rollBallForFrame(1);
			rollBallForFrame(1);
			assertEquals(4,game.getCurrentFrame());
			assertEquals(22,game.getScore());
			
			//Forth Frame
			rollBallForFrame(3);
			rollBallForFrame(2);
			assertEquals(5,game.getCurrentFrame());
			assertEquals(27,game.getScore());
			
			//Fifth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(6,game.getCurrentFrame());
			assertEquals(34,game.getScore());
			
			//Six Frame
			rollBallForFrame(7);
			rollBallForFrame(1);
			assertEquals(7,game.getCurrentFrame());
			assertEquals(42,game.getScore());
			
			//Seventh Frame
			rollBallForFrame(4);
			rollBallForFrame(2);
			assertEquals(8,game.getCurrentFrame());
			assertEquals(48,game.getScore());
			
			//Eigth Frame
			rollBallForFrame(3);
			rollBallForFrame(3);
			assertEquals(9,game.getCurrentFrame());
			assertEquals(54,game.getScore());
			
			//Nineth Frame
			rollBallForFrame(2);
			rollBallForFrame(7);
			assertEquals(10,game.getCurrentFrame());
			assertEquals(63,game.getScore());
			
			//Tenth Frame
			rollBallForFrame(5);
			rollBallForFrame(5);
			assertEquals(73,game.getScore());
			
			assertEquals(false,game.isGameFinished());
			
			rollBallForFrame(5);
			assertEquals(78,game.getScore());
			assertEquals(true,game.isGameFinished());
			
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}
	
	/**
	 * Test Strike Scoring
	 */
	@Test
	public void testStrikeScoring() {
		try {
			//First Frame
			rollBallForFrame(8);
			rollBallForFrame(2);
			assertEquals(10,game.getScore());
			
			//Second Frame
			rollBallForFrame(3);
			assertEquals(2,game.getCurrentFrame());
			assertEquals(16,game.getScore());
			rollBallForFrame(4);
			assertEquals(20,game.getScore());
			assertEquals(3,game.getCurrentFrame());
			
			//Third frame
			rollBallForFrame(1);
			rollBallForFrame(1);
			assertEquals(4,game.getCurrentFrame());
			assertEquals(22,game.getScore());
			
			//Forth Frame
			rollBallForFrame(3);
			rollBallForFrame(2);
			assertEquals(5,game.getCurrentFrame());
			assertEquals(27,game.getScore());
			
			//Fifth Frame
			rollBallForFrame(3);
			rollBallForFrame(4);
			assertEquals(6,game.getCurrentFrame());
			assertEquals(34,game.getScore());
			
			//Six Frame
			rollBallForFrame(7);
			rollBallForFrame(1);
			assertEquals(7,game.getCurrentFrame());
			assertEquals(42,game.getScore());
			
			//Seventh Frame
			rollBallForFrame(4);
			rollBallForFrame(2);
			assertEquals(8,game.getCurrentFrame());
			assertEquals(48,game.getScore());
			
			//Eigth Frame
			rollBallForFrame(3);
			rollBallForFrame(3);
			assertEquals(9,game.getCurrentFrame());
			assertEquals(54,game.getScore());
			
			//Nineth Frame
			rollBallForFrame(2);
			rollBallForFrame(7);
			assertEquals(10,game.getCurrentFrame());
			assertEquals(63,game.getScore());
			
			//Tenth Frame
			rollBallForFrame(10);
			assertEquals(73,game.getScore());
			
			assertEquals(false,game.isGameFinished());
			
			rollBallForFrame(5);
			rollBallForFrame(5);
			assertEquals(83,game.getScore());
			assertEquals(true,game.isGameFinished());
			
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}
	
	
	/**
	 * Test All Strikes logic and scoring
	 */
	@Test
	public void testAllStrikes() {
		try {
			//First Frame
			rollBallForFrame(10);
			assertEquals(10,game.getScore());
			
			//Second Frame
			assertEquals(2,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(30,game.getScore());
			
			//Third frame
			assertEquals(3,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(60,game.getScore());
			
			//Forth Frame
			assertEquals(4,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(90,game.getScore());
			
			//Fifth Frame
			assertEquals(5,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(120,game.getScore());
			
			//Six Frame
			assertEquals(6,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(150,game.getScore());
			
			//Seventh Frame
			assertEquals(7,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(180,game.getScore());
			
			//Eigth Frame
			assertEquals(8,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(210,game.getScore());
			
			//Nineth Frame
			assertEquals(9,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(240,game.getScore());
			
			//Tenth Frame
			assertEquals(10,game.getCurrentFrame());
			rollBallForFrame(10);
			assertEquals(270,game.getScore());
			
			assertEquals(false,game.isGameFinished());
			
			rollBallForFrame(10);
			rollBallForFrame(10);
			assertEquals(300,game.getScore());
			assertEquals(true,game.isGameFinished());
			
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}
	
	/**
	 * Test All Spares Logic and scoring
	 */
	@Test
	public void testAllSpares() {
		try {
			//First Frame
			rollBallForFrame(5);
			rollBallForFrame(5);
			assertEquals(10,game.getScore());
			
			//Second Frame
			assertEquals(2,game.getCurrentFrame());
			rollBallForFrame(5);
			rollBallForFrame(5);
			assertEquals(25,game.getScore());
			
			//Third frame
			assertEquals(3,game.getCurrentFrame());
			rollBallForFrame(4);
			rollBallForFrame(6);
			assertEquals(39,game.getScore());
			
			//Forth Frame
			assertEquals(4,game.getCurrentFrame());
			rollBallForFrame(1);
			rollBallForFrame(9);
			assertEquals(50,game.getScore());
			
			//Fifth Frame
			assertEquals(5,game.getCurrentFrame());
			rollBallForFrame(5);
			rollBallForFrame(5);
			assertEquals(65,game.getScore());
			
			//Six Frame
			assertEquals(6,game.getCurrentFrame());
			rollBallForFrame(3);
			rollBallForFrame(7);
			assertEquals(78,game.getScore());
			
			//Seventh Frame
			assertEquals(7,game.getCurrentFrame());
			rollBallForFrame(2);
			rollBallForFrame(8);
			assertEquals(90,game.getScore());
			
			//Eigth Frame
			assertEquals(8,game.getCurrentFrame());
			rollBallForFrame(5);
			rollBallForFrame(5);
			assertEquals(105,game.getScore());
			
			//Nineth Frame
			assertEquals(9,game.getCurrentFrame());
			rollBallForFrame(6);
			rollBallForFrame(4);
			assertEquals(121,game.getScore());
			
			//Tenth Frame
			assertEquals(10,game.getCurrentFrame());
			rollBallForFrame(4);
			rollBallForFrame(6);
			assertEquals(135,game.getScore());
			
			assertEquals(false,game.isGameFinished());
			
			rollBallForFrame(5);
			assertEquals(140,game.getScore());
			assertEquals(true,game.isGameFinished());
			
		} catch(InvalidThrowForGame e){
			fail("turn(), threw InvalidThrowForGame exception, too many throws happened.");
		} catch(InvalidThrowForFrame e){
			fail("turn(), threw InvalidThrowForFrame exception, too many throws happened.");
		} catch(InvalidBonusThrowForFrame e){
			fail("turn(), threw InvalidBonusThrowForFrame exception, too many throws happened.");
		}
		
	}
	
	
	/**
	 * Method to help with the logic around the construction of a Throw for a Frame in the game.
	 * @param pins
	 * @throws InvalidThrowForGame
	 * @throws InvalidBonusThrowForFrame 
	 * @throws InvalidThrowForFrame 
	 */
	public void rollBallForFrame(int pins) throws InvalidThrowForGame, InvalidThrowForFrame, InvalidBonusThrowForFrame {
		Throw roll=new Throw();
		roll.setPins(pins);
		game.turn(roll);
	}
	

}
