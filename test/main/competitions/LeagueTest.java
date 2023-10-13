package main.competitions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import main.Competitor;
import main.mocks.MockBookmaker;
import main.mocks.MockJournalist;
import main.observer.CompetitionObserver;


class LeagueTest extends CompetitionTest{
	private League l ;
	private MockJournalist mj;
	private MockBookmaker mb;
	
	@Override
	public Competition createOneCompetition() {
		return this.l = new League(this.comps, obs);
	}
	
	
	@Test
    public void leagueTest(){
        int size= comps.size();
		l.play(comps);
		l.displayRanking();
		b.displayAllRatings();
		int allpts=0;
		for (Competitor c : comps){
			allpts += c.getPoints();
		}
		size = size * (size-1);
		assertEquals(size,allpts);         
    }
	
	@Test
	void testCompetitionObserved() {
		this.comps = new ArrayList<Competitor>();
		for(int i = 0; i < 4; i++) {
			comps.add(new Competitor("Team "+Integer.toString(i+1)));
		}
		mj=new MockJournalist("Patrick Mboma");
		this.obs=new ArrayList<CompetitionObserver>();
		mb=new MockBookmaker("Zebet",comps);
		l=new League(comps, obs);
		l.register(mj);
		l.register(mb);
		assertEquals(0,mj.called);
		assertEquals(0,mb.called);
		l.play();
		int nbmatch=comps.size()*(comps.size()-1);
		assertEquals(nbmatch,mj.called);
		assertEquals(nbmatch,mb.called);
	}
}
