package main.mocks;

import java.util.List;

import main.Competitor;
import main.match.Match;
import main.observer.Bookmaker;

public class MockBookmaker extends Bookmaker {
	
	public int called;
	
	public MockBookmaker(String name, List<Competitor> comps) {
		super(name, comps);
		called=0;
	}
	
	
	@Override
	public void reactToCompetition(Match match) {
		super.reactToCompetition(match);
		called++;
	}
}
