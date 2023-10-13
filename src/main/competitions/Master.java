package main.competitions;

import java.util.*;

import main.Competitor;
import main.observer.CompetitionObserver;
import main.strategy.Selection;

/** A class that handles a group stage and then a tournament between all finalists */
public class Master extends Competition {

	/** Selection of the finalists */
	private final Selection selection;

	/** Number of group stage */
	private final int nbOfGroupStage;

	/** Groups stage of the Master */
	private final List<League> groups;


	/** A Master is defined by its list of competitors, its selection of finalists and its number of group stage.
	 * At its creation, the group stage is initialized depending on the number of group stage chosen
	 * @param comp the list of competitors
	 * @param strategy the selection's type
	 * @param nbGroupStage the number of group stage chosen
	 * @param obs the list of competitor's observers 
	 */
	public Master(List<Competitor> comp, Selection strategy, int nbGroupStage, List<CompetitionObserver> obs) {
		super(comp, obs);
		this.selection = strategy;
		this.nbOfGroupStage = nbGroupStage;
		this.groups = this.buildGroupStage();
	}


	/**
	 * @return the selection chosen
	 */
	public Selection getSelection() {
		return this.selection;
	}


	/**
	 * @return the number of group stage
	 */
	public int getNbOfGroupStage() {
		return this.nbOfGroupStage;
	}


	/** Builds group stage that will play the league
	 * @return the group stage
	 */
	public List<League> buildGroupStage() {
		List<League> leagues = new ArrayList<>();
		int nbCompsPerGroup = this.competitors.size() / this.nbOfGroupStage;
		Collections.shuffle(competitors);
		for(int i = 0; i < this.nbOfGroupStage; i++) {
			List<Competitor> compsGroup = new ArrayList<>();
			for(int j = 0; j < nbCompsPerGroup; j++) {
				compsGroup.add(this.competitors.get(i * nbCompsPerGroup + j));
			}
			leagues.add(new League(compsGroup, this.observers));
		}
		return leagues;
	}



	/** Plays the league between all competitors of each group stage
	 * @return the list of groups ranked after the group stage
	 */
	public List<League> playGroupStage() {
		List<League> groupsRanked = new ArrayList<>();
		int i = 1;
		System.out.print("========== Group phase  ========== \n");
		System.out.print(" \n");
		for(League l : this.groups) {
			System.out.println("=============");
			System.out.println("  Group "+i+" ");
			System.out.println("=============");
			System.out.print("\n");
			l.play();
			i++;
			System.out.print("\n");
			groupsRanked.add(l);
		}
		return groupsRanked;
	}


	/**
	 * Displays the rank of all competitors in each group
	 */
	public void displayGroupRanking() {
		int i = 1;
		System.out.print("=== Group stage results === \n");
		System.out.print(" \n");
		for(League l : groups) {
			System.out.print("=== GROUP "+ i +" RESULTS === \n");
			l.displayRanking();
			System.out.print(" \n");
			i++;
		}
	}

	/**
	 * return the list of competitors for the final phase
	 * @param groupsRanked a list of league
	 * @return the list of competitors for the final phase
	 */
	public List<Competitor> getFinalists(List<League> groupsRanked){
		List<Competitor> finalists = this.selection.selectFinalists(groupsRanked);
		Collections.shuffle(finalists);
		return finalists;
	}

	/**
	 * Plays the tournament between finalists selected
	 * @param finalists the list of final competitors
	 */
	public void playFinalStage(List<Competitor> finalists) {
		Tournament t = new Tournament(finalists, this.observers);
		System.out.print("=== The Finalists === \n");
		System.out.print(finalists);
		System.out.print(" \n");
		System.out.print(" \n");
		System.out.print("========== Final phase  ========== \n");
		t.resetPoints();
		t.play();
		System.out.print(" \n");
		System.out.print("==== Final phase results  ==== \n");
		t.displayRanking();
	}

	/**
	 * @see main.competitions.Competition#play play(comp)
	 */
	@Override
	public void play(List<Competitor> comp) {
		List<League> groupsRanked = this.playGroupStage();
		List<Competitor> finalists = this.getFinalists(groupsRanked);
		this.displayGroupRanking();
		this.playFinalStage(finalists);
	}


}
