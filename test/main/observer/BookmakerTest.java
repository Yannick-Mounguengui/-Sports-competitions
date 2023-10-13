package main.observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Competitor;
import main.competitions.Tournament;

class BookmakerTest {
	public List<Competitor> comps;
	public Competitor c1,c2,c3,c4,c5;
	public Bookmaker b;
	public Tournament t;
	public List<CompetitionObserver> obs;

	
	@Test
	void testDisplayAllRatings() {
		c1 = new Competitor("Blastoff");
		c2 = new Competitor("Drift");
		c3 = new Competitor("Catalyst");
		c4 = new Competitor("Raven");
		c5 = new Competitor("Lynx");
		this.comps = new ArrayList<Competitor>();
		comps.add(c1);
		comps.add(c2);
		comps.add(c3);
		comps.add(c4);
		b=new Bookmaker("parions sport", comps);
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		t.register(b);
		t.play();
		assertTrue(b.displayAllRatings().containsKey(c1));
		assertFalse(b.displayAllRatings().containsKey(c5));
		assertEquals(4,b.displayAllRatings().size());
	}
	
	@Test
	void testBookmaker() {
		c1 = new Competitor("Blastoff");
		c2 = new Competitor("Drift");
		this.comps = new ArrayList<Competitor>();
		comps.add(c1);
		comps.add(c2);
		b=new Bookmaker("parions sport", comps);
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		assertEquals(0,obs.size());
		t.register(b);
		assertEquals("parions sport",b.getName());
		b.setName("Betclic");
		assertEquals("Betclic",b.getName());
		assertTrue(obs.contains(b));
		assertEquals(1,obs.size());
		t.unRegister(b);
		assertFalse(obs.contains(b));
		assertEquals(0,obs.size());
	}
	
}
