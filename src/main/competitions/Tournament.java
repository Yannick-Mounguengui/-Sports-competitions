package main.competitions;

import main.*;
import java.util.*;
import main.exceptions.*;
import main.observer.CompetitionObserver;

/** A class that represents a Tournament (a kind of competition) */
public class Tournament extends Competition {

	/**
	 * @see main.competitions.Competition#Competition
	 * @param comp the competitor's list
	 * @param obs the list of competitor's observers 
	 */
	public Tournament(List<Competitor> comp, List<CompetitionObserver> obs) {
		super(comp, obs);
		if(!isPowerOfTwo(comp.size())) {
			throw new NotAPowerOfTwoException("Cannot play a tournament : number of competitors is not a power of two ! ");
		}
	}

	/** Determines if an integer is a power of two or not
	 * @param n the integer
	 * @return <code>true</code> iff it is a power of two, <code>false</code> if not
	 */
	private static boolean isPowerOfTwo(int n) {
		while(n%2 == 0) {
			n = n/2;
		}
		return n == 1;
	}


	/**
	 * @see main.competitions.Competition#play() play(comp)
	 */
	@Override
	protected void play(List<Competitor> comp) {
		Queue<Competitor> queue = new LinkedList<>(comp);
		Competitor winner;

		while(queue.size()!=1) {
			// we remove the first 2 elements from the linked list to obtain 2 competitors
			Competitor c1 = queue.poll();
			Competitor c2 = queue.poll();
			this.playMatch(c1, c2);
			if(c1.getPoints() > c2.getPoints()) {
				winner = c1;
			}
			else {
				winner = c2;
			}

			// we put the winner of the match at the tail of the linked list
			queue.offer(winner);
		}
		Competitor ultimateWinner = queue.poll();
		System.out.println("The winner of the tournament is : " + ultimateWinner);
	}

}
