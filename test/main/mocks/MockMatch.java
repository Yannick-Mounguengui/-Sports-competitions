package main.mocks;

import main.Competitor;
import main.match.Match;

public class MockMatch implements Match {
	
	public int test;
	@SuppressWarnings("unused")
	private Competitor c1,c2,winner,looser;
	
	public MockMatch() {
	}
	
	

	/** 
	 * Tests the method's call
	 * @return 
	 */
	@Override
	public Competitor setWinner(Competitor c1, Competitor c2) {
		return c1;	
	}
	
	/**
	 * @see main.match.Match#toString()
	 */
	public String toString() {
		return "Competitor c1 is the winner";
	}
	

	@Override
	public Competitor getWinner() {
		return c1;
	}


	@Override
	public Competitor getLooser() {
		return c2;
	}



	@Override
	public Competitor setLooser(Competitor c1, Competitor c2) {
		return c2;
	}

}
