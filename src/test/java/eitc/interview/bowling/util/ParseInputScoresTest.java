package eitc.interview.bowling.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eitc.interview.bowling.Throw;
import eitc.interview.bowling.exceptions.NoLineFoundException;

/**
 * Junit test for the parsing of the input score data.
 */
public class ParseInputScoresTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Method to test it can parse the file
	 */
	@Test
	public void testProcessAllStrikesFile() {
		try {
//			List<Throw> throwList=ParseInputScores.processFile("test/resources/allStrikesInput.txt");
			List<Throw> throwList=ParseInputScores.processFile("/allStrikesInput.txt");
			assertEquals(12,throwList.size());
			
			Iterator<Throw> throwItr=throwList.iterator();
			while(throwItr.hasNext()){
				Throw roll=throwItr.next();
				assertEquals(10,roll.getPins());
			}
		} catch (FileNotFoundException e) {
			fail("Got FileNotFoundException and was not expecting that.");
		} catch (NoLineFoundException e) {
			fail("Got NoLineFoundException and was not expecting that.");
		}
	}
	
	/**
	 * Method to test it can parse the spares file and got the right values
	 */
	@Test
	public void testProcessSparesFile() {
		try {
//			List<Throw> throwList=ParseInputScores.processFile("test/resources/sparesInput.txt");
			List<Throw> throwList=ParseInputScores.processFile("/sparesInput.txt");
			
			Iterator<Throw> throwItr=throwList.iterator();
			while(throwItr.hasNext()){
				Throw roll=throwItr.next();
				assertEquals(5,roll.getPins());
			}
		} catch (FileNotFoundException e) {
			fail("Got FileNotFoundException and was not expecting that.");
		} catch (NoLineFoundException e) {
			fail("Got NoLineFoundException and was not expecting that.");
		}
	}
	

}
