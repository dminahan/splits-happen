package eitc.interview.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import eitc.interview.bowling.exceptions.InvalidBonusThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForGame;

/**
 * BowlingGame is the class that holds all the frames of the game.  It will add in the ball rolls 
 * or throws of the game.
 */
public class BowlingGame {

	protected List<Frame> frameList=new ArrayList<Frame>(10);  //List of frames of the game
	
	private int currentFrame=0;  //current frame index
	private int currentBall=0;   //current ball of frame

	/**
	 * Method to for each turn/roll of the ball
	 * 
	 * @param roll
	 * @throws InvalidThrowForGame
	 * @throws InvalidThrowForFrame 
	 * @throws InvalidBonusThrowForFrame 
	 */
	public void turn(Throw roll) throws InvalidThrowForGame, InvalidThrowForFrame, InvalidBonusThrowForFrame{
		
		//Check if we have finished all the frames, if not allow roll
		if(!isGameFinished()) {
			Frame frame=null;
			//If first time in first frame has not been created
			if((currentFrame==0) && (currentBall==0)){
				frame=new Frame();
				frameList.add(frame);
			} else if(currentFrame<frameList.size()){
				frame=frameList.get(currentFrame);
			}

			//Loop through frames to determine if pins need to be added to other frames
			//in case spares or strikes happened
			Iterator<Frame> frameItr=frameList.iterator();
			while(frameItr.hasNext()){
				Frame verifyFrame=frameItr.next();
				if(verifyFrame.needsBonusThrows()){
					verifyFrame.addBonusThrow(roll);
				}
			}

			//Frame can be null if you rolled a strike on the last 2 frames or a spare 
			//in the last frame and rolling the extra balls for the scoring
			if(null!=frame){
				//Add ball to current frame unless it is done 
				//(i.e. on last frame but we are on bonus throws)
				if(!frame.isFrameDone()){
					frame.addScore(roll);
				} 

				currentBall++;

				//If frame is done and not the last frame then increment counter
				if(frame.isFrameDone()){
					currentFrame++;
					currentBall=0;

					if(currentFrame!=10){
						frame=new Frame();
						frameList.add(frame);
					}
				}			

			}

		} else {
			throw new InvalidThrowForGame("The frames are all done and the game is over.");
		}
		
	}
	
	/**
	 * Method to verify if the game is finished 
	 * @param frame
	 * @return
	 */
	public boolean isGameFinished() {
		//If there are no frames, game just begun
		if(frameList.size()==0){
			return false;
		}
		
		boolean framesFinished=true;
		Iterator<Frame> frameItr=frameList.iterator();
		while(frameItr.hasNext()){
			Frame frame=frameItr.next();
			if(!frame.isFrameDone() || (frame.isFrameDone() && frame.needsBonusThrows())){
				framesFinished=false;
			}
		}
		return framesFinished;
	}

	/**
	 * Method to get the score of the game
	 * @return score - current score of the game
	 */
	public int getScore(){
		int score=0;
		Iterator<Frame>frameItr=frameList.iterator();
		while(frameItr.hasNext()){
			Frame frame=frameItr.next();
			score+=frame.getSubScore();
		}
		return score;
	}

	/**
	 * Method to get the current frame the game is on
	 * @return int - current frame
	 */
	public int getCurrentFrame() {
		//Add one so visually it shows 1-10
		return currentFrame+1;
	}

	/**
	 * Method to get which ball of the current frame the game is on
	 * @return int - current ball of frame
	 */
	public int getCurrentBall() {
		return currentBall;
	}
	
}
