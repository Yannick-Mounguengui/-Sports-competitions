package main.mocks;

import main.match.Match;
import main.observer.Journalist;

public class MockJournalist extends Journalist {
	
	public int called;
	
	public MockJournalist(String name) {
		super(name);
		called=0;
	}

	
	@Override
	public void reactToCompetition(Match match) {
		super.reactToCompetition(match);
		called++;
	}

}
