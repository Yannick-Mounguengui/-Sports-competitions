package main.mocks;

import java.util.*;

import main.Competitor;
import main.competitions.*;
import main.observer.CompetitionObserver;
import main.strategy.Selection;

public class MockMaster extends Master {
	public boolean finished;
	
	public MockMaster(List<Competitor> comp, Selection strategy, int nbGroupStage, List<CompetitionObserver> obs) {
		super(comp, strategy, nbGroupStage, obs);
		finished=false;
	}
	
	@Override
	public List<League> playGroupStage(){
		finished=true;
		return super.playGroupStage();
	}

}
