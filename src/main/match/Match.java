package main.match;

import main.Competitor;

/** An interface to define a Match */
public interface Match {
	
	
	/** Sets the winner of the match
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 * @return the random winner of the match
	 */
	public Competitor setWinner(Competitor c1, Competitor c2);
	
	
	/** Sets the looser of the match
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 * @return the random looser of the match
	 */
	public Competitor setLooser(Competitor c1, Competitor c2);
	
	
	/**
	 * @return the winner of the match
	 */
	public Competitor getWinner();
	
	
	/**
	 * @return the looser of the match 
	 */
	public Competitor getLooser();


	/** 
	 * @return a string representation for a match
	 */
	public String toString();
	

}
