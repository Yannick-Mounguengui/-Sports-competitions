package main.observer;

import main.match.Match;

/** An interface that models the competition's observers */
public interface CompetitionObserver {
	
	/** Reacts to the competition
	 * @param match the match
	 */
	public void reactToCompetition(Match match);

}
