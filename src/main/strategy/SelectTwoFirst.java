package main.strategy;

import java.util.*;

import main.Competitor;
import main.competitions.League;

/** A class that select the first two competitors of each group stage for the final phase */
public class SelectTwoFirst implements Selection {

	@Override
	/**
	 * @see main.strategy.Selection#selectFinalists selectFinalists(groupStage)
	 */
	public List<Competitor> selectFinalists(List<League> groupStage) {
		List<Competitor> finalists = new ArrayList<Competitor>();
		Iterator<League> it = groupStage.iterator();
		int Finalistcompetitor = 2;
		while(it.hasNext()) {
			League currentLeague = it.next();
			Map<Competitor, Integer> rankedCurrentLeague = currentLeague.ranking();
			int i = 0;
			for (Map.Entry<Competitor, Integer> entry : rankedCurrentLeague.entrySet()) {
				if(i < Finalistcompetitor) {
					Competitor competitorChoosen = entry.getKey();
					finalists.add(competitorChoosen);
					i++;
				}
			}
		}
		return finalists;
	}

}
