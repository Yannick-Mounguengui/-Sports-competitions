package main.decorator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Competitor;
import main.competitions.Tournament;
import main.observer.CompetitionObserver;
import main.observer.Journalist;

class JournalistDecoratorTest {

	public List<Competitor> comps;
	public Competitor c1,c2,c3,c4,c5;
	public CompetitionObserver j;
	public Journalist j1;
	public Tournament t;
	public List<CompetitionObserver> obs;
	
	@Test
	void testJournalistPresent() {
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
		j=new JournalistDecorator(new Journalist("Thierry Henry"));
		Journalist j1 = new Journalist("Omar da Fonseca");
		CompetitionObserver j2 = new JournalistDecorator(new Journalist("Pierre Menes"));
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		assertEquals(0,obs.size());
		t.register(j);
		t.register(j1);
		t.play();
		assertTrue(obs.contains(j));
		assertEquals(2,obs.size());
		assertFalse(obs.contains(j2));
		t.register(j2);
		assertEquals(3,obs.size());
		t.unRegister(j);
		t.play();
		assertEquals(2,obs.size());
		assertTrue(obs.contains(j2));
		assertFalse(obs.contains(j));
		
	}
	

}
