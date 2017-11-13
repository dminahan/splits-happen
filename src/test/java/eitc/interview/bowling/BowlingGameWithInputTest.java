package eitc.interview.bowling;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eitc.interview.bowling.util.ParseInputScores;
import eitc.interview.bowling.exceptions.InvalidBonusThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForGame;
import eitc.interview.bowling.exceptions.NoLineFoundException;

/**
 * This unit test will be used to take sample input files and parse them and use them with the BowlingGame.
 * 
 */
public class BowlingGameWithInputTest {
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
	 * Method to parse the strikes file and use it in the bowling game
	 */
	@Test
	public void testProcessAllStrikesFile() {
		try {
			List<Throw> throwList=ParseInputScores.processFile("/allStrikesInput.txt");
			assertEquals(12,throwList.size());
			
			Iterator<Throw> throwItr=throwList.iterator();
			while(throwItr.hasNext()){
				Throw roll=throwItr.next();
				game.turn(roll);
			}
			assertEquals(300,game.getScore());
		} catch (FileNotFoundException e) {
			fail("Got FileNotFoundException and was not expecting that.");
		} catch (NoLineFoundException e) {
			fail("Got NoLineFoundException and was not expecting that.");
		} catch (InvalidThrowForGame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		}
	}
	
	/**
	 * Method to parse the spares file and use it in the bowling game
	 */
	@Test
	public void testProcessSparesFile() {
		try {
			List<Throw> throwList=ParseInputScores.processFile("/sparesInput.txt");
			assertEquals(21,throwList.size());

			Iterator<Throw> throwItr=throwList.iterator();
			while(throwItr.hasNext()){
				Throw roll=throwItr.next();
				game.turn(roll);
			}
			assertEquals(150,game.getScore());
		} catch (FileNotFoundException e) {
			fail("Got FileNotFoundException and was not expecting that.");
		} catch (NoLineFoundException e) {
			fail("Got NoLineFoundException and was not expecting that.");
		} catch (InvalidThrowForGame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		}
	}
	
	
	/**
	 * Method to parse the integer based file and use it in the bowling game
	 */
	@Test
	public void testProcessIntegerFile() {
		try {
			List<Throw> throwList=ParseInputScores.processFile("/integerInput.txt");
			assertEquals(20,throwList.size());

			Iterator<Throw> throwItr=throwList.iterator();
			while(throwItr.hasNext()){
				Throw roll=throwItr.next();
				game.turn(roll);
			}
			assertEquals(90,game.getScore());
		} catch (FileNotFoundException e) {
			fail("Got FileNotFoundException and was not expecting that.");
		} catch (NoLineFoundException e) {
			fail("Got NoLineFoundException and was not expecting that.");
		} catch (InvalidThrowForGame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		} catch (InvalidThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		} catch (InvalidBonusThrowForFrame e) {
			fail("Got InvalidBonusThrowForFrame and was not expecting that.");
		}
	}
	

}
