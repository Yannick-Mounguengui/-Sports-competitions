package main.strategy;

import java.util.List;

import main.Competitor;
import main.competitions.League;

/** An interface that will able to select a list of finalists for the final phase */
public interface Selection {


	/** Selects a list of competitors that will play the final phase (tournament)
	 * @param groupStage the competitors list that have played the group stage
	 * @return the list of finalists
	 */
	public List<Competitor> selectFinalists(List<League> groupStage);


}
