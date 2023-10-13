package main.observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Competitor;
import main.competitions.Tournament;

class JournalistTest {	
	public List<Competitor> comps;
	public Competitor c1,c2,c3,c4,c5;
	public Journalist j;
	public Tournament t;
	public List<CompetitionObserver> obs;
	
	@Test
	void testJournalist() {
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
		j=new Journalist("Pierre menes");
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		t.register(j);
		t.play();
		assertEquals("Pierre menes",j.getName());
		j.setName("Herve mathoux");
		assertEquals("Herve mathoux",j.getName());
		t.play();
	}
	
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
		j=new Journalist("Pierre menes");
		Journalist j1=new Journalist("Habib beye");
		this.obs=new ArrayList<CompetitionObserver>();
		t=new Tournament(comps, obs);
		assertEquals(0,obs.size());
		t.register(j);
		assertTrue(obs.contains(j));
		assertEquals(1,obs.size());
		assertFalse(obs.contains(j1));
		t.register(j1);
		assertEquals(2,obs.size());
		t.unRegister(j);
		assertEquals(1,obs.size());
		assertTrue(obs.contains(j1));
		assertFalse(obs.contains(j));
		
	}
	
}
