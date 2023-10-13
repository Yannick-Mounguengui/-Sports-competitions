package main.decorator;

import main.Competitor;
import main.match.Match;
import main.observer.Journalist;

/** A class to represent a journalist that will displays the results of each match with a different message from another journalist */
public class JournalistDecorator extends ObservatorDecorator {
	
	/** A JournalistDecorator is defined by a journalist object
	 * @param j the journalist
	 */
	public JournalistDecorator(Journalist j) {
		journalist=j;
	}
	
	/** Reacts to the competition : displays results after each match
	 * @param match the match 
	 */
	@Override
	public void reactToCompetition(Match match) {
		Competitor winner = match.getWinner();
		Competitor looser = match.getLooser();
		System.out.println(journalist.getName()+": Today "+winner+" was stronger than "+looser+"\n");
		System.out.println(journalist.getName()+": The score was "+journalist.generateScoreWinner()+" - "+journalist.generateScoreLooser(journalist.generateScoreWinner())+"\n");
	}
	
}
