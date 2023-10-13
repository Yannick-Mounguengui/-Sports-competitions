package main.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Competitor;
import main.match.RandomWinner;
import main.mocks.MockMatch;

class RandomWinnerTest {
	
	public MockMatch ma;
	public RandomWinner r;
	public Competitor c1,c2;
	
	@BeforeEach
	void init() {
		c1 = new Competitor("Blastoff");
		c2 = new Competitor("Drift");
		ma=new MockMatch();
	}
	
	@Test
	void testmockGetWinner() {
		assertEquals(c1,ma.setWinner(c1, c2));
		assertEquals(c2,ma.setLooser(c1, c2));
		assertEquals("Competitor c1 is the winner",ma.toString());
		}
	


}
