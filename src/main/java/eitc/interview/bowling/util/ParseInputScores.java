package eitc.interview.bowling.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	public static List<Throw>processFile(String fileName)throws FileNotFoundException, NoLineFoundException {
		List<Throw> throwList=null;
		
//		FileReader reader=new FileReader(fileName);
//		InputStream inputStream =ParseInputScores.class.getClassLoader().getResourceAsStream("sparesInput.txt");
		InputStream inputStream =ParseInputScores.class.getResourceAsStream(fileName);
		
		//.getClassLoader().getResourceAsStream(fileName);
//		Scanner scanner=new Scanner(reader);
		Scanner scanner=new Scanner(inputStream);
		
	    try {
	        //make sure scanner has a line
	        if ( scanner.hasNextLine() ){
	          throwList=processLine( scanner);
	        } else {
	        	throw new NoLineFoundException("There was no line found in the file: " + fileName);
	        }
	      }
	      finally {
	        //close the stream
	        scanner.close();
	      }
		
		return throwList;
		
	}

	private static List<Throw> processLine(Scanner scanner) {
		
		ArrayList<Throw> throwList=new ArrayList<Throw>();
		
		String line=scanner.nextLine();
		String[] lineSplit=line.split("-");
		String token=null;
		Throw roll=null;
		for(int i=0; i<lineSplit.length;i++){
			token=lineSplit[i];
			char[] charTokens=token.toCharArray();
			for(int c=0; c<charTokens.length;c++) {
				char cValue=charTokens[c];
				if(cValue=='X'){
					roll=new Throw();
					roll.setPins(10);
					throwList.add(roll);
				} else if( cValue=='/'){
					int spareHelper=Character.digit(charTokens[c-1],10);
					int spareValue=10-spareHelper;
					roll=new Throw();
					roll.setPins(spareValue);
					throwList.add(roll);
				} else {
					roll=new Throw();
					int digit=Character.digit(cValue, 10);
					roll.setPins(digit);
					throwList.add(roll);
				}
			}
			
		}
		return throwList;
	}

}
