package main.observer;

import java.util.*;

import main.Competitor;
import main.match.Match;
import main.util.MapUtil;

/** A class to represent a bookmaker that will evolve each competitor's rating */
public class Bookmaker implements CompetitionObserver { 
	
	/** The bookmaker's name */
	private String name;

	/** A list of all ratings associated to a competitor */
	public Map<Competitor, Integer> ratings;
	
	/** A bookmaker is defined by its name and its competitors list
	 * @param name the bookmaker's name 
	 * @param comps the competitors list 
	 */
	public Bookmaker(String name,List<Competitor> comps) {
		this.setName(name);
		this.ratings = this.createBookmakerMap(comps);
	}
	
	/** Returns the bookmaker's name
	 * @return the bookmaker's name
	 */
	public String getName() {
		return this.name;
	}
	
	/** Sets the bookmaker's name
	 * @param name the name to set 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Returns the competitor's rating 
	 * @param c the competitor
	 * @return the competitor's rating
	 */
	public int getCompetitorRating(Competitor c) {
		return this.ratings.get(c);
	}
	
	/** Reacts to the competition : the bookmaker sets and displays the new ratings of the winner and the looser after the match
	 * @param match the match
	 */
	@Override
	public void reactToCompetition(Match match) {
		Competitor winner = match.getWinner();
		Competitor looser = match.getLooser();
		
		int ratingW = this.ratings.get(winner);
		int ratingL = this.ratings.get(looser);

		this.decreaseRating(winner);
		this.increaseRating(looser);

		System.out.println(this.name + ": Nice assisted match, the participants' odds have changed !\n");
		System.out.println(this.name + ": winner " + "("+winner+")" + " new rating " + ratingW + " => " + this.getCompetitorRating(winner)+"\n");
		System.out.println(this.name + ": looser " + "("+looser+")" + " new rating " + ratingL + " => " + this.getCompetitorRating(looser)+"\n");
	}
	

	/** Decreases the competitor's rating
	 * @param c the competitor
	 */
	public void decreaseRating(Competitor c) {
		int oldRating = ratings.get(c);
		if(oldRating > 1) {
		this.ratings.replace(c,(oldRating-1));
		}
		else {
			this.ratings.replace(c,oldRating);
		}
	}

	/** Increases the competitor's rating
	 * @param c the competitor
	 */
	public void increaseRating(Competitor c) {
		int oldRating = this.ratings.get(c);
		this.ratings.replace(c, (oldRating + 2));
	}


	/** Generates a random rating
	 * @return a random rating
	 */
	private int generateRandomRating() {
		Random rand = new Random();
		int random = rand.nextInt(9)+1;
		return random;
	}

	
	/** Returns a map with a couple competitor rating
	 * @param comps the competitors's list
	 * @return a map with a couple competitor rating
	 */
	private Map<Competitor,Integer> createBookmakerMap(List<Competitor> comps) {
		Iterator<Competitor> it = comps.iterator();
		Map<Competitor,Integer> CompetitorsWithRate = new HashMap<Competitor,Integer>();
		while(it.hasNext()) {
			Competitor c = it.next();
			int d=this.generateRandomRating();
			CompetitorsWithRate.put(c,d);
		}
		return CompetitorsWithRate;
	}

	
	/** Displays all ratings
	 * @return all ratings : a map with a couple competitor rating 
	 */
	public Map<Competitor,Integer> displayAllRatings() {
		Map<Competitor, Integer> rank = this.ratings;
		rank = MapUtil.sortByAscendingValue(rank);
		System.out.println("\n");
		System.out.println("======  COMPETITOR RATING AT START OF COMPETITION BY "+this.getName()+"  =====");
		for (Map.Entry<Competitor, Integer> entry : rank.entrySet()) {
			System.out.println("");
			System.out.println(entry.getKey().getName() + " : "+ entry.getValue());
			System.out.println("============");
		}
		System.out.println("==================================================================================================================");
		return rank;
	}

}
