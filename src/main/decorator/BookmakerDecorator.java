package main.decorator;

import java.util.Map;

import main.Competitor;
import main.match.Match;
import main.observer.Bookmaker;

/** A class to represent the bookmaker decorator */
public class BookmakerDecorator extends ObservatorDecorator{
	
	/** A BookmakerDecorator is defined by a bookmaker object
	 * @param b the bookmaker
	 */
	public BookmakerDecorator(Bookmaker b) {
		bookmaker=b;
	}
	
	/** Reacts to the competition : the bookmaker sets and displays the new ratings of the winner and the looser after the match
	 * @param match the match
	 */
	@Override
	public void reactToCompetition(Match match) {
		Competitor winner = match.getWinner();
		Competitor looser = match.getLooser();
		
		int ratingW = (bookmaker.ratings.get(winner));
		int ratingL = (bookmaker.ratings.get(looser));

		bookmaker.decreaseRating(winner);
		bookmaker.increaseRating(looser);
		
		System.out.println(bookmaker.getName()+": Before the match odd for " + "("+winner+") => "+ratingW +" \n");
		System.out.println(bookmaker.getName()+": And odd for ("+looser+") => "+ratingL +" \n");
		System.out.println(bookmaker.getName() + ": End of match, the participants' odds have changed !\n");
		System.out.println(bookmaker.getName()+": odd for "+"("+winner+") => "+bookmaker.getCompetitorRating(winner) +" \n");
		System.out.println(bookmaker.getName()+": odd for ("+looser+") => "+bookmaker.getCompetitorRating(looser) +" \n");
		}
	
	
	/** Displays all ratings
	 * @return all ratings : a map with a couple competitor rating 
	 */
	public Map<Competitor,Integer> displayAllRatingsdecorated() {
		System.out.println("==================================================================================================================");
		return super.bookmaker.displayAllRatings();
		
	}
}
