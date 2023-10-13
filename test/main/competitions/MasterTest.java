package main.competitions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Competitor;
import main.mocks.MockBookmaker;
import main.mocks.MockJournalist;
import main.mocks.MockMaster;
import main.observer.CompetitionObserver;
import main.strategy.SelectFirst;


class MasterTest extends CompetitionTest {
	
	protected Master m;
	protected SelectFirst s1;
	protected MockMaster mock;
	private MockJournalist mj;
	private MockBookmaker mb;
	
	@Override
	public Competition createOneCompetition() {
		s1=new SelectFirst();
		return m=new Master(comps,s1,2, obs);
	}
	
	@Test
	void testfinishgroupstage() {
		mock=new MockMaster(comps,s1,2,obs);
		mock.buildGroupStage();
		mock.playGroupStage();
		assertTrue(mock.finished);
	}
	
	@Test
	void mastertest() {
		assertEquals(2,m.getNbOfGroupStage());
		assertEquals(s1,m.getSelection());
		List<League> groupsRanked = m.playGroupStage();
		for(League l : groupsRanked) {
			assertEquals(2,l.getAllCompetitors().size());
			int size= comps.size();
			int allpts=0;
			for (Competitor c : comps){
				allpts += c.getPoints();
			}
			size=size*(size-l.getAllCompetitors().size()-1);
			assertEquals(size,allpts);
		}
		List<Competitor> finalists = m.getFinalists(groupsRanked);
		m.displayGroupRanking();
		m.playFinalStage(finalists);
		b.displayAllRatings();
		int allpts2=0;
		int size2=finalists.size();
		size2=size2-1;
		for (Competitor c : finalists){
			allpts2 += c.getPoints();
		}
		assertEquals(size2,allpts2);
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
		m=new Master(comps,s1,2, obs);
		m.register(mj);
		m.register(mb);
		assertEquals(0,mj.called);
		assertEquals(0,mb.called);
		List<League> groupsRanked = m.playGroupStage();
		int nbCompsPerGroup = comps.size() / m.getNbOfGroupStage();
		int nbmatchpergroup=nbCompsPerGroup-1;
		int nbmatchgroupstage=comps.size()*nbmatchpergroup;
		assertEquals(nbmatchgroupstage,mj.called);
		assertEquals(nbmatchgroupstage,mb.called);
		List<Competitor> finalists = m.getFinalists(groupsRanked);
		m.playFinalStage(finalists);
		int nbmatchfinalstage=finalists.size()-1;
		int nbmatchmaster=nbmatchgroupstage+nbmatchfinalstage;
		assertEquals(nbmatchmaster,mj.called);
		assertEquals(nbmatchmaster,mb.called);
		
		
	}
}
