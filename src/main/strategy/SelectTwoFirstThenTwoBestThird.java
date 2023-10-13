package main.strategy;

import java.util.*;

import main.Competitor;
import main.competitions.League;
import main.observer.CompetitionObserver;

/** A class that select the first three competitors of each group stage for the final phase */
public class SelectTwoFirstThenTwoBestThird implements Selection {

	@Override
	/** 
	 * @see main.strategy.Selection#selectFinalists selectFinalists(groupStage) 
	 */
	public List<Competitor> selectFinalists(List<League> groupStage) {
		List<CompetitionObserver> competitionObservers = new ArrayList<CompetitionObserver>();
		List<Competitor> finalists = new ArrayList<Competitor>();
		List<Competitor> bestThird = new ArrayList<Competitor>();
		Iterator<League> it = groupStage.iterator();
		int competitorToChoosePerLeague = 3;
		while(it.hasNext()) {
			League currentLeague = it.next();
			Map<Competitor, Integer> rankedCurrentLeague = currentLeague.ranking();
			int i =0;
			for (Map.Entry<Competitor, Integer> entry : rankedCurrentLeague.entrySet()) {
				if(i<competitorToChoosePerLeague) {
					if(i==2) {
						Competitor competitorChoosen = entry.getKey();
						bestThird.add(competitorChoosen);
						i++;
					}
					else {
						Competitor competitorChoosen = entry.getKey();
						finalists.add(competitorChoosen);
						i++;
					}
					
				}
			}	
		}
				
		League bestThirdLeague = new League(bestThird, competitionObservers);
		Map<Competitor, Integer> rankedBestThird = bestThirdLeague.ranking();
		int y = 0;
		for (Map.Entry<Competitor, Integer> entry : rankedBestThird.entrySet()) {
			if(y<2) {
				Competitor competitorThirdChoosen = entry.getKey();
				finalists.add(competitorThirdChoosen);
				y++;
			}
		}
		return finalists;
	}

	
}

