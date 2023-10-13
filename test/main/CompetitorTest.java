package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompetitorTest {

	public Competitor c1,c2,c3,c4,c5;

	@BeforeEach
	void init() {
		c1 = new Competitor("Blastoff");
		c2 = new Competitor("Blastoff");
		c3 = new Competitor("Catalyst", 1);
		c4 = new Competitor("Raven", 8);
	}
	                                    
	@Test
	void testPoints() {
		assertEquals(0, c1.getPoints());
		c1.setPoints(4);
		assertEquals(4, c1.getPoints());
		assertEquals(8, c4.getPoints());
		c4.addPoints();
		assertEquals(9, c4.getPoints());
	}
	
	@Test
	void testEqualsWhenTwoCompetitorsAreEqual() {
		assertEquals(0, c1.getPoints());
		assertEquals(0, c2.getPoints());
		c1.setPoints(4);
		c2.setPoints(4);
		assertEquals(4, c1.getPoints());
		assertEquals(4, c2.getPoints());
		assertTrue(c1.equals(c2));
	}
	
	@Test
	void testWhenTwoCompetitorsAreNotEqual()  {
		assertFalse(c3.equals(c4));
		assertFalse(c1.equals(c3));
	}
	
	
	@Test
	void testToString() {
		assertTrue(c1.toString().contains(c1.getName()));
		assertEquals("Raven",c4.getName());
	}

}

