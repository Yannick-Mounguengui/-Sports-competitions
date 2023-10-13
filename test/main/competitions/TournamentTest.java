package main.competitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import main.Competitor;
import main.exceptions.*;
import main.mocks.MockBookmaker;
import main.mocks.MockJournalist;
import main.observer.CompetitionObserver;


class TournamentTest extends CompetitionTest{
	
	private Tournament t;
	private MockJournalist mj;
	private MockBookmaker mb;
	
	@Override
	public Competition createOneCompetition() {
		return this.t = new Tournament(this.comps, obs);
	}
	

	@Test
	void NotApowerOfTwoException() throws NotAPowerOfTwoException {
		c5=new Competitor("Bayern Munich");
		t.addCompetitor(c5);
		assertTrue(t.getAllCompetitors().contains(c5));
		assertThrows(NotAPowerOfTwoException.class,() ->{
			new Tournament(comps, obs);
		});
	}
	
	@Test
	public void tournamentTest() {
		t.play(this.comps);
		t.displayRanking();
		b.displayAllRatings();
		int allpts=0;
		for (Competitor c : comps){
			allpts += c.getPoints();
		}
		assertEquals(comps.size()-1,allpts);
	}
	
	@Test
	void testCompetitionObserved() {
		this.comps = new ArrayList<Competitor>();
		for(int i = 0; i < 8; i++) {
			comps.add(new Competitor("Team "+Integer.toString(i+1)));
		}
		mj=new MockJournalist("Patrick Mboma");
		this.obs=new ArrayList<CompetitionObserver>();
		mb=new MockBookmaker("Zebet",comps);
		t=new Tournament(comps, obs);
		t.register(mj);
		t.register(mb);
		assertEquals(0,mj.called);
		assertEquals(0,mb.called);
		t.play();
		int nbmatch=comps.size()-1;
		assertEquals(nbmatch,mj.called);
		assertEquals(nbmatch,mb.called);
	}
}
