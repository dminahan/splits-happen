package eitc.interview.bowling.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import eitc.interview.bowling.Throw;
import eitc.interview.bowling.exceptions.NoLineFoundException;


/**
 * This class is used to read in scores
 */
public class ParseInputScores {
	private String inputScoresFileName=null;
	private InputStream throwsStream=null;
	
	/**
	 * Constructor to load a named input file name and then get the throws.
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
    public ParseInputScores(String fileName) throws FileNotFoundException, IOException {
    	    this.inputScoresFileName=fileName;
	    throwsStream =ParseInputScores.class.getResourceAsStream(fileName);
	}
	
	
    /**
     * Process the Throw line
     * @return
     * @throws FileNotFoundException
     * @throws NoLineFoundException
     */
	public List<Throw>processThrows()throws FileNotFoundException, NoLineFoundException {
		List<Throw> throwList=null;
		
		Scanner scanner=new Scanner(throwsStream);
		
	    try {
	        //make sure scanner has a line
	        if ( scanner.hasNextLine() ){
	          throwList=processLine( scanner);
	        } else {
	        	throw new NoLineFoundException("There was no line found in the file: " + this.inputScoresFileName);
	        }
	      }
	      finally {
	        //close the stream
	        scanner.close();
	      }
		
		return throwList;
		
	}

	/**
	 * Method to process a line from the file to get a list of Throws
	 * @param scanner
	 * @return
	 */
	private List<Throw> processLine(Scanner scanner) {
		
		ArrayList<Throw> throwList=new ArrayList<Throw>();
		Throw roll=null;
		
		String line=scanner.nextLine();
		char[] charTokens=line.toCharArray();
		for(int c=0; c<line.length(); c++) {
			char cValue=charTokens[c];
			switch(charTokens[c]) {
			case 'X':
				roll=new Throw();
				roll.setPins(10);
				throwList.add(roll);
				break;
			case '/':
				int spareHelper=Character.digit(charTokens[c-1], 10);
				int spareValue=10-spareHelper;
				roll=new Throw();
				roll.setPins(spareValue);
				throwList.add(roll);
				break;
			case '-':
				roll=new Throw();
				roll.setPins(0);
				throwList.add(roll);
				break;
			default:
				roll=new Throw();
				int digit=Character.digit(cValue, 10);
				roll.setPins(digit);
				throwList.add(roll);
				break;
			}
		}

		return throwList;
	}

}
