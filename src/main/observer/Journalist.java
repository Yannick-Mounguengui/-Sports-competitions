package main.observer;

import java.util.Random;

import main.Competitor;
import main.match.Match;

/** A class to represent a journalist that will displays the results of each match */
public class Journalist implements CompetitionObserver {
	
	/** The journalist's name */
	private String name;
	
	/** A journalist is defined by its name 
	 * @param name the journalist's name 
	 */
	public Journalist(String name) {
		this.setName(name);
	}

	/** Returns the journalist's name 
	 * @return the journalist's name 
	 */
	public String getName() {
		return name;
	}
	
	/** Sets the journalist's name 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Generates a random score for the winner 
	 * @return a random score for the winner 
	 */
	public int generateScoreWinner() {
		Random rand = new Random();
		int random = rand.nextInt(7)+1;
		return random;
	}
	
	/** Generates a random score for the looser
	 * @param scoreWinner the winner's score
	 * @return a random score for the looser 
	 */
	public int generateScoreLooser(int scoreWinner) {
		Random rand = new Random();
		int random = rand.nextInt(scoreWinner);
		return random;
	}
	
	/** Reacts to the competition : displays results after each match
	 * @param match the match 
	 */
	@Override
	public void reactToCompetition(Match match) {
		Competitor winner = match.getWinner();
		Competitor looser = match.getLooser();
		System.out.println(this.name+": Oooh, what a sensational victory for " + winner +"\n");
		System.out.println(this.name+": looser is " + looser + " ! What a fail ! \n");
	}

}
