package main.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Competitor;
import main.competitions.League;
import main.competitions.Master;
import main.observer.Bookmaker;
import main.observer.CompetitionObserver;
import main.observer.Journalist;

class SelectionTest {
	
	public Master m1,m2,m3;
	Selection s1;
	Selection s2;
	Selection s3;
	public Competitor c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
	List<Competitor> comps;
	List<CompetitionObserver> obs;
	
	@Test
	void testcontentselection() {
		Journalist j=new Journalist("Habib Beye");
		s1=new SelectFirst();
		c1 = new Competitor("allemagne");
		c2 = new Competitor("brasil");
		c3 = new Competitor("canada");
		c4 = new Competitor("danemark");
		c5 = new Competitor("espagne");
		c6 = new Competitor("france");
		c7 = new Competitor("gabon");
		c8 = new Competitor("hongrie");
		c9 = new Competitor("italie");
		c10 = new Competitor("japon");
		c11 = new Competitor("kazakhstan");
		c12 = new Competitor("liban");
		this.comps = new ArrayList<Competitor>();
		comps.add(c1);
		comps.add(c2);
		comps.add(c3);
		comps.add(c4);
		comps.add(c5);
		comps.add(c6);
		comps.add(c7);
		comps.add(c8);
		comps.add(c9);
		comps.add(c10);
		comps.add(c11);
		comps.add(c12);
		this.obs=new ArrayList<CompetitionObserver>();
		Bookmaker b=new Bookmaker("Fdj", comps);
		m1=new Master(comps,s1,4,obs);
		m1.register(j);
		m1.register(b);
		m1.buildGroupStage();
		List<League> playgroupstage=m1.playGroupStage();
		c7.setPoints(6);
		c3.setPoints(0);
		List<Competitor> finalists= s1.selectFinalists(playgroupstage);
		m1.displayGroupRanking();
		m1.playFinalStage(finalists);
		assertTrue(finalists.contains(c7));
		assertFalse(finalists.contains(c3));
	}
	
	@Test
	void testSelectFinalists1() {
		s1=new SelectFirst();
		this.comps = new ArrayList<Competitor>();
		for(int i = 0; i < 4; i++) {
			comps.add(new Competitor("TeamS1 "+Integer.toString(i+1)));
		}
		this.obs=new ArrayList<CompetitionObserver>();
		Journalist j=new Journalist("Habib Beye");
		Bookmaker b=new Bookmaker("Fdj", comps);
		m1=new Master(comps,s1,2, obs);
		m1.register(b);
		m1.register(j);
		m1.buildGroupStage();
		assertEquals(s1,m1.getSelection());
		List<Competitor> finalists= s1.selectFinalists(m1.playGroupStage());
		m1.displayGroupRanking();
		System.out.println("finalists are: "+finalists+ "and the number of finalist is "+finalists.size());
		assertEquals(m1.getNbOfGroupStage(),finalists.size());
		
	}
	@Test
	void testSelectFinalists2() {
		this.obs=new ArrayList<CompetitionObserver>();
		s2=new SelectTwoFirst();
		this.comps = new ArrayList<Competitor>();
		for(int i = 0; i < 8; i++) {
			comps.add(new Competitor("TeamS2 "+Integer.toString(i+1)));
		}
		Journalist j=new Journalist("Habib Beye");
		Bookmaker b=new Bookmaker("Fdj", comps);
		m2=new Master(comps,s2,2, obs);
		m2.register(b);
		m2.register(j);
		m2.buildGroupStage();
		assertEquals(s2,m2.getSelection());
		List<Competitor> finalists= s2.selectFinalists(m2.playGroupStage());
		m2.displayGroupRanking();
		System.out.println("finalists are: "+finalists+ "and the number of finalist is "+finalists.size());
		assertEquals(m2.getNbOfGroupStage()*2,finalists.size());
	}
	
	@Test
	void testSelectFinalists3() {
		this.obs=new ArrayList<CompetitionObserver>();
		s3=new SelectTwoFirstThenTwoBestThird();
		this.comps = new ArrayList<Competitor>();
		for(int i = 0; i < 16; i++) {
			comps.add(new Competitor("TeamS3 "+Integer.toString(i+1)));
		}
		Journalist j=new Journalist("Habib Beye");
		Bookmaker b=new Bookmaker("Fdj", comps);
		m3=new Master(comps,s3,3, obs);
		m3.register(b);
		m3.register(j);
		m3.buildGroupStage();
		assertEquals(s3,m3.getSelection());
		List<Competitor> finalists= s3.selectFinalists(m3.playGroupStage());
		m3.displayGroupRanking();
		System.out.println("finalists are: "+finalists+ "and the number of finalist is "+finalists.size());
		assertEquals((m3.getNbOfGroupStage()*3)-1,finalists.size());
	}
}
