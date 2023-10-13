package main.decorator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Competitor;
import main.competitions.Tournament;
import main.observer.Bookmaker;
import main.observer.CompetitionObserver;

class BookmakerDecoratorTest {
	
	public List<Competitor> comps;
	public Competitor c1,c2,c3,c4,c5;
	public CompetitionObserver b;
	public Tournament t;
	public List<CompetitionObserver> obs;
	
	@Test
	void testBookmakerDecorator() {
		c1 = new Competitor("Blastoff");
		c2 = new Competitor("Drift");
		this.comps = new ArrayList<Competitor>();
		comps.add(c1);
		comps.add(c2);
		b=new BookmakerDecorator(new Bookmaker("Betclic", comps));
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		t.register(b);
		assertTrue(obs.contains(b));
		assertEquals(1,obs.size());
		t.play();
		t.unRegister(b);
		assertFalse(obs.contains(b));
		assertEquals(0,obs.size());

	}

	@Test
	void testDisplayAllRatingsdecorated() {
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
		b=new BookmakerDecorator(new Bookmaker("parions sport", comps));
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		t.register(b);
		assertTrue(((BookmakerDecorator) b).displayAllRatingsdecorated().containsKey(c1));
		assertFalse(((BookmakerDecorator) b).displayAllRatingsdecorated().containsKey(c5));
		assertEquals(4,((BookmakerDecorator) b).displayAllRatingsdecorated().size());
		t.play();
	}

}
