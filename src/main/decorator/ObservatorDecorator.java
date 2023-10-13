package main.decorator;

import main.match.Match;
import main.observer.Bookmaker;
import main.observer.CompetitionObserver;
import main.observer.Journalist;

/** A class to represent the observator decorator */
public abstract class ObservatorDecorator implements CompetitionObserver {
	
	/** the journalist observing the competition */
	protected Journalist journalist;
	
	/** the bookmaker observing the competition */
	protected Bookmaker bookmaker;
	
	/**
	 * @see main.observer.CompetitionObserver#reactToCompetition
	 */
	@Override
	public abstract void reactToCompetition(Match match);

}
