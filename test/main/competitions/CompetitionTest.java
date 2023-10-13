package main.competitions;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import main.*;
import main.exceptions.UnknownPlayerException;
import main.match.Match;
import main.match.RandomWinner;
import main.mocks.*;
import main.observer.*;

public abstract class CompetitionTest {
	
	public Competition c;
	public MockCompetition m;
	public List<Competitor> comps;
	public Match rand;
	public Competitor c1,c2,c3,c4,c5;
	public int test,testobs=0;
	public List<CompetitionObserver> obs;
	public Journalist j;
	public Bookmaker b;
	
	
	@BeforeEach
	protected
	void init() {
		rand=new RandomWinner();
		c1 = new Competitor("Blastoff");
		c2 = new Competitor("Drift");
		c3 = new Competitor("Catalyst");
		c4 = new Competitor("Raven");
		this.comps = new ArrayList<Competitor>();
		m=new MockCompetition(comps, obs);
		comps.add(c1);
		comps.add(c2);
		comps.add(c3);
		comps.add(c4);
		j=new Journalist("Habib Beye");
		b=new Bookmaker("Fdj", comps);
		this.obs=new ArrayList<CompetitionObserver>();
		c=this.createOneCompetition();
		c.register(b);
		c.register(j);
		
	}
	
	@Test
	void testRemoveCompetitorNotInList() throws UnknownPlayerException {
		c5=new Competitor("AB");
		assertFalse(c.getAllCompetitors().contains(c5));
		assertThrows(UnknownPlayerException.class,() ->{
			c.removeCompetitor(c5);
		});
	}
	
	@Test
	void testRemoveCompetitor() throws UnknownPlayerException {
		assertEquals(4,c.getAllCompetitors().size());
		c.removeCompetitor(c1);
		assertEquals(3,c.getAllCompetitors().size());
	}

	@Test
	void testCompetitors() {
		assertEquals(4,c.getAllCompetitors().size());
		c5=new Competitor("Lynx");
		c.addCompetitor(c5);
		assertEquals(5,c.getAllCompetitors().size());
	}
	
		
	@Test
	void testPlay() {
		assertEquals(0,m.test);
		m.play();
		assertEquals(1,m.test);
	}
	
	@Test
	void testcompetitionobserved() {
		assertEquals(0,m.observed);
		Competitor c6=new Competitor("Ac Milan");
		Competitor c7=new Competitor("Real Madrid");
		m.playMatch(c6, c7);
		assertEquals(1,m.observed);
	}
	
	@Test
	void testRanking() {
		c5=new Competitor("Lynx");
		assertTrue(c.ranking().containsKey(c1));
		assertFalse(c.ranking().containsKey(c5));
		assertEquals(4,c.ranking().size());
		c.displayRanking();
	}
			
	public abstract Competition createOneCompetition();

}
