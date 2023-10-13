package main.match;

import java.util.*;
import main.*;

/** A class that describes how to get a random winner */
public class RandomWinner implements Match {
	
	private Competitor winner, looser;
	
	/** */
	public RandomWinner() {
	}
	
	
	/**
	 * @see main.match.Match#setWinner(Competitor, Competitor)
	 */
	public Competitor setWinner(Competitor c1, Competitor c2) {
		  Random rand = new Random();
		  List<Competitor> match = new ArrayList<>();
		  match.add(c1);
		  match.add(c2);
		  this.winner = match.get(rand.nextInt(match.size()));
		  this.winner.addPoints();
		  System.out.println(c1 + " vs " + c2 + " --> " + this.winner + " wins !\n");
		  return this.winner;
	}
	
	/**
	 * @see main.match.Match#setLooser(Competitor, Competitor)
	 */
	public Competitor setLooser(Competitor c1, Competitor c2) {
		if(this.winner.equals(c1)) {
			this.looser = c2;
		}
		else {
			this.looser = c1;
		}
		return this.looser;
	}
	
	
	/**
	 * @see main.match.Match#getWinner()
	 */
	public Competitor getWinner() {
		return this.winner;
	}
	
	
	/**
	 * @see main.match.Match#getLooser()
	 */
	public Competitor getLooser() {
		return this.looser;
	}
	

	/**
	 * @see main.match.Match#toString()
	 */
	public String toString() {
		return "Random winner : a random winner will be returned";
	}



}
