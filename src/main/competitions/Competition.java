package main.competitions;

import java.util.*;

import main.match.*;
import main.util.MapUtil;
import main.Competitor;
import main.observer.CompetitionObserver;
import main.exceptions.UnknownPlayerException;


/** A class that represents a competition */
public abstract class Competition {

  /** A list of competitors */
  protected final List<Competitor> competitors;

  /** A kind of match */
  protected Match match;
  
  /** The list of competition's observers */
  protected List<CompetitionObserver> observers = new ArrayList<CompetitionObserver>();
  

  /** A competition is defined by a given list of competitors and a list of observers (journalists and bookmakers)
   * @param comp the list of competitors 
   * @param obs the list of competitor's observers 
   */
  public Competition(List<Competitor> comp, List<CompetitionObserver> obs) {
    this.competitors = comp;
    this.observers = obs;
  }
  

  /** Adds a competitor of the list
   *  @param c the competitor
   */
  public void addCompetitor(Competitor c) {
	  this.competitors.add(c);
  }


  /** Removes a competitor of the list
   *  @param c the competitor
   *  @throws UnknownPlayerException when the competitor does not exist in the list
   */
  public void removeCompetitor(Competitor c) throws UnknownPlayerException {
	  if(!this.competitors.contains(c)) {
		  throw new UnknownPlayerException();
	  }
	  else {
		  this.competitors.remove(c);
	  }
  }


  /**
   * @return the list of all competitors
   */
  public List<Competitor> getAllCompetitors() {
	  return this.competitors;
  }


  /**
   * Resets the number of points of all competitor from the list 
   */
  public void resetPoints() {
    for(Competitor c : this.competitors) {
      c.setPoints(0);
    }
  }


  /**
   * Plays the competition
   */
  public void play() {
	 this.play(this.competitors);
  }


  /**
   * plays all match according to the list of competitors
   * @param comp the list of competitors
   */
  protected abstract void play(List<Competitor> comp);


  /** Plays a match between two competitors
   * @param c1 the first competitor
   * @param c2 the second competitor
   */
  protected void playMatch(Competitor c1, Competitor c2) {
	  this.match = new RandomWinner();
	  match.setWinner(c1,c2); 
	  match.setLooser(c1,c2);
	  
	  this.competitionDetected(match);
	  System.out.println("==================================================================================================================");
  }


  /**
   * @return the ranking, a competitor (key) associated to a value (the rank)
   */
  public Map<Competitor, Integer> ranking() {
		Map<Competitor, Integer> competitorsRanking = new HashMap<>();
		Iterator<Competitor> it = this.competitors.iterator();

		while(it.hasNext()) {
			Competitor c1 = it.next();
			competitorsRanking.put(c1, c1.getPoints());
		}
		competitorsRanking = MapUtil.sortByDescendingValue(competitorsRanking);
		return competitorsRanking;
	}


  /**
   * Displays the rank of all competitors
   */
  public void displayRanking() {
	  	System.out.println("\n");
		Map<Competitor, Integer> rank = this.ranking();
		System.out.println("*** RANKING ***");
		for(Map.Entry<Competitor, Integer> entry : rank.entrySet()) {
			System.out.println(entry.getKey().getName() + " - "+ entry.getValue());
		}
	}
  
  
  /** Registers the competition's observer
   * @param ob the observer 
   */
  public void register(CompetitionObserver ob) {
		this.observers.add(ob);
	}
  
  
  /** Unregisters the competition's observer 
   * @param ob the observer 
   */
  public void unRegister(CompetitionObserver ob) {
	  this.observers.remove(ob);
	}
  
  
  /** Detects the match played and make each observer reacts 
   * @param m the match 
   */
  public void competitionDetected(Match m) {
	for(CompetitionObserver compObs : this.observers) {
		compObs.reactToCompetition(m);
	}
  }

}