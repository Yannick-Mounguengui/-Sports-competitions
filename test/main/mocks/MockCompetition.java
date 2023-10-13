package main.mocks;

import java.util.*;

import main.*;
import main.competitions.Competition;
import main.match.Match;
import main.observer.CompetitionObserver;

/** A mock class that extends Competition class */
public class MockCompetition extends Competition {
	
	/** an attribute that will test a method's call */
	public int observed;
	
	/** an attribute that will test a method's call */
	public int test;
	
	/** Defines the MockCompetition that extends Competition
	 * @param comp the competitor's list
	 * @param competitionObservers 
	 */
	public MockCompetition(List<Competitor> comp, List<CompetitionObserver> competitionObservers) {
		super(comp, competitionObservers);
		this.test=0;
		this.observed=0;
	}
	
	
	/** Tests the method's call
	 * @param comp competitor's list
	 */
	@Override
	public void play(List<Competitor> comp) {
		this.test++;
	}
	
	/** Tests the method's call
	 * @param match the match observed
	 */
	@Override
	public void competitionDetected(Match m) {
		observed++;
	}
}
