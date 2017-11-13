package eitc.interview.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eitc.interview.bowling.exceptions.InvalidBonusThrowForFrame;
import eitc.interview.bowling.exceptions.InvalidThrowForFrame;

/**
 * Frame of a bowling game that holds the throws
 */
public class Frame {
	private static final int MAX_ROLLS = 2;
	private static int STRIKE=10;


	private List<Throw> throwList=new ArrayList<Throw>(MAX_ROLLS);
	
	private int subScore=0;  //Holds the bonus throw(s) pins
	private int currentThrow=0;
	private boolean hasStrike=false;
	private boolean hasSpare=false;
	private int bonusThrowCount=0;
	
	private boolean frameDone=false;
	
	
	/**
	 * Method to get the current throw counter
	 * @return
	 */
	public int getCurrentThrow() {
		return currentThrow;
	}
	
	/**
	 * Method to set the current throw counter
	 * @param currentThrow
	 */
	public void setCurrentThrow(int currentThrow) {
		this.currentThrow = currentThrow;
	}
	
	/**
	 * Method to get the subScore, or really the frame total score including throws and bonus points. 
	 * The subScore variable just holds realtime the bonus throw pins and this method sums that with 
	 * The throws of the frame realtime.
	 * @return
	 */
	public int getSubScore() {
		int totalScore=subScore;
		Iterator<Throw> throwsItr=throwList.iterator();
		while(throwsItr.hasNext()){
			Throw roll=throwsItr.next();
			totalScore+=roll.getPins();
		}
		return totalScore;
	}

	/**
	 * Method to get if this frame is a strike.
	 * @return
	 */
	public boolean hasStrike() {
		return hasStrike;
	}
	
	/**
	 * Method to set if this frame is a strike.
	 * @param hasStrike
	 */
	public void setHasStrike(boolean hasStrike) {
		this.hasStrike = hasStrike;
	}
	
	/**
	 * Method to get if this frame is a spare.
	 * @return
	 */
	public boolean hasSpare() {
		return hasSpare;
	}
	
	/**
	 * Method to set the indicator on if this frame is a spare.
	 * @param hasSpare
	 */
	public void setHasSpare(boolean hasSpare) {
		this.hasSpare = hasSpare;
	}
	
	/**
	 * Method to get the bonus throw count
	 * @return
	 */
	public int getBonusThrowCount() {
		return bonusThrowCount;
	}
	
	/**
	 * Method to add a Throw to the frame.  If maximum allowed throws for the frame have been exceeded,
	 * then an exception will be thrown.  This can happen by adding too many throws above the 2 throw limit, 
	 * or because a strike happened so the frame is over and bonus throws are all that get added in.
	 * 
	 * @param roll - Throw to add to the frame
	 * @throws InvalidThrowForFrame
	 */
	public void addScore(Throw roll) throws InvalidThrowForFrame{
		if(this.currentThrow>=MAX_ROLLS){
			throw new InvalidThrowForFrame("This roll cannot be added to the frame.");
		} else {
			int pins=roll.getPins();
			//If you got a strike then the frame is over.
			//TODO: DFG, This does not handle if second ball, with points already there.  Would mean too many pins passed on second ball.
			if(pins==STRIKE){
				this.setHasStrike(true);
				this.setFrameDone(true);
			} else if(pins+getSubScore()==STRIKE){
				this.setHasSpare(true); //will set frame done in following code
			}
			//Add in pins, increment current throw indicator
			throwList.add(roll);
			currentThrow++;
			//If both balls for a frame have been thrown then frame is over 
			//(but bonus pins may get added by game if strike or spare happened)
			if(currentThrow==MAX_ROLLS){
				this.setFrameDone(true);
			}
		}
	}
	
	/**
	 * Method to add bonus throw pins to a frame if a strike or spare happened.  The number of bonus 
	 * throws allowed depends on if a strike or spare happened.
	 * @param roll
	 * @throws InvalidBonusThrowForFrame
	 */
	public void addBonusThrow(Throw roll) throws InvalidBonusThrowForFrame {
		if(hasStrike() && this.getBonusThrowCount()<2){
			subScore+=roll.getPins();
			bonusThrowCount++;
		} else if(hasSpare()&& this.getBonusThrowCount()<1){
			subScore+=roll.getPins();
			bonusThrowCount++;
		} else {
			throw new InvalidBonusThrowForFrame("This roll cannot be added to the frame.");
		}
	}
	
	/**
	 * Indicator that the frame is done.
	 * @return
	 */
	public boolean isFrameDone() {
		return frameDone;
		/*
		if((this.hasStrike() && this.bonusThrowCount==2) || (this.hasSpare() && this.bonusThrowCount==1) || frameDone){
			return true;
		} else {
			return false;
		}
		*/
	}
	public void setFrameDone(boolean frameDone) {
		this.frameDone = frameDone;
	}
	
	/**
	 * Method to indicate if the frame still needs bonus throws.  This will happen when all 
	 * balls are done in the frame but the player got a strike or a spare.  
	 * 
	 * @return - true if bonus throws still need to be added to the frame, false if they do not.
	 */
	public boolean needsBonusThrows(){
		if((this.hasStrike() && this.bonusThrowCount<2) || (this.hasSpare() && this.bonusThrowCount<1)){
			return true;
		}
		return false;
	}
	
	

}
