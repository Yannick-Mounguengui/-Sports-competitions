package main;

import java.util.*;
import main.observer.*;
import main.strategy.*;
import main.competitions.*;
import main.decorator.BookmakerDecorator;
import main.decorator.JournalistDecorator;
import main.exceptions.NotAPowerOfTwoException;

/** Main class that will play the competition depending on what kind of competition the user will choose */
public class CompetitionMain {

	/** the scanner */
	private static Scanner s;

	/** The main method
	 * @param args the argument
	 */
	public static void main(String[] args) {
		List<Competitor> competitors = new ArrayList<Competitor>();
		List<CompetitionObserver> competitionObservers = new ArrayList<CompetitionObserver>();
		Journalist j1 = new Journalist("Omar da Fonseca");
		CompetitionObserver j2 = new JournalistDecorator(new Journalist("Habib Beye"));
		Selection s1 = new SelectFirst();
		Selection s2 = new SelectTwoFirst();
		Selection s3 = new SelectTwoFirstThenTwoBestThird();

		s = new Scanner(System.in);
		boolean done = false;
		System.out.print("========== Sports Competition ========== \n");
		System.out.print("Please choose a competition \n" );
		System.out.print("1) Tournament, 2) League, 3) Master \n");
		do {

		int choice = s.nextInt();

		if(choice == 1) {
			System.out.print("========== Tournament Competition ========== \n");
			System.out.print("Choose a number of competitors \n");

			int number=s.nextInt();
			for(int i = 0; i < number; i++) {
				competitors.add(new Competitor("Team "+Integer.toString(i+1)));
			}

			try {
				Bookmaker b1=new Bookmaker("Unibet", competitors);
				CompetitionObserver b2=new BookmakerDecorator(new Bookmaker("Casinozer", competitors));
				Tournament t = new Tournament(competitors, competitionObservers);
				
				b1.displayAllRatings();
				((BookmakerDecorator) b2).displayAllRatingsdecorated();
				t.register(j1);
				t.register(j2);
				t.register(b1);
				t.register(b2);
				t.play();
				t.displayRanking();
				
			    } catch (NotAPowerOfTwoException e) {
			      System.out.println("Cannot play a tournament : number of competitors is not a power of two !\n");
			    }
			done=true;
			}
		else if(choice == 2) {
			System.out.print("========== League Competition  ========== \n");
			System.out.print("choose a number of competitors \n");

			int number=s.nextInt();
			for(int i = 0; i < number; i++) {
				competitors.add(new Competitor("Team "+Integer.toString(i+1)));
			}
			Bookmaker b1=new Bookmaker("Unibet", competitors);
			CompetitionObserver b2=new BookmakerDecorator(new Bookmaker("Casinozer", competitors));
			League l = new League(competitors, competitionObservers);
			
			b1.displayAllRatings();
			((BookmakerDecorator) b2).displayAllRatingsdecorated();
			l.register(j1);
			l.register(j2);
			l.register(b1);
			l.register(b2);
			l.play();
			l.displayRanking();
			done=true;
		}

		else if(choice == 3){
			String[] worldCup = {"Qatar", "Ecuador", "Senegal", "Netherlands", "England", "Iran", "USA", "Wales", "Argentina",
					"Saudi Arabia", "Mexico", "Poland", "France", "Australia", "Denmark", "Tunisia",
					"Spain", "Costa Rica", "Germany", "Japan", "Belgium", "Canada", "Morocco",
					"Croatia", "Brazil", "Serbia", "Switzerland", "Cameroon", "Portugal", "Ghana", "Uruguay", "South Korea",
					"Algeria","Gabon","Italia","Norway","Colombia","Peru","Sweden","Chile","Nigeria","Austria","Ivory coast","Turkey","Egypt",
					"Greece","Slovaquia","Democratic Republic of Congo","paraguay","Ukraine"
					};
			List<String> worldCupCopy = new ArrayList<>();
			Collections.addAll(worldCupCopy, worldCup);
			
			 boolean ok = false;
			 System.out.print("========== Master Competition  ========== \n");
			 System.out.println("Select the type of Master you want");
			 System.out.print("1) Master(16) : 16 competitors will be choose \n");
			 System.out.print("2) Master(24) : 24 competitors will be choose\n");
			 System.out.print("3) Master(32) : 32 competitors will be choose\n");
			 do {
			 int choice2 = s.nextInt();
			 if(choice2 == 1) {
				System.out.print("========== The World Cup 2022 ========== \n");
				System.out.print(" \n");
				
				int i = 0;
				while (i<16) {
					competitors.add(new Competitor(worldCupCopy.get(0)));
					worldCupCopy.remove(0);
					i++;
					Collections.shuffle(worldCupCopy);
				}
				CompetitionObserver b2=new BookmakerDecorator(new Bookmaker("Casinozer", competitors));
				Bookmaker b1=new Bookmaker("Unibet", competitors);
				Master m1=new Master(competitors,s1,4, competitionObservers);
				
				b1.displayAllRatings();
				((BookmakerDecorator) b2).displayAllRatingsdecorated();
				m1.register(j1);
				m1.register(j2);
				m1.register(b1);
				m1.register(b2);
				m1.play();
				ok=true;
			 }
			 else if(choice2 ==2) {
					System.out.print("========== The World Cup 2022 ========== \n");
					System.out.print(" \n");
					
					int i = 0;
					while (i<24) {
						competitors.add(new Competitor(worldCupCopy.get(0)));
						worldCupCopy.remove(0);
						i++;
						Collections.shuffle(worldCupCopy);
					}
					CompetitionObserver b2=new BookmakerDecorator(new Bookmaker("Casinozer", competitors));
					Bookmaker b1=new Bookmaker("Unibet", competitors);
					Master m3=new Master(competitors,s3,3, competitionObservers);
					
					b1.displayAllRatings();
					((BookmakerDecorator) b2).displayAllRatingsdecorated();
					m3.register(j1);
					m3.register(j2);
					m3.register(b1);
					m3.register(b2);
					m3.play();
					ok=true;
			 }
			 else if(choice2 == 3) {
				System.out.print("========== The World Cup 2022 ========== \n");
				System.out.print(" \n");
				
				int i = 0;
				while (i<32) {
					competitors.add(new Competitor(worldCupCopy.get(0)));
					worldCupCopy.remove(0);
					i++;
					Collections.shuffle(worldCupCopy);
				}
				CompetitionObserver b2=new BookmakerDecorator(new Bookmaker("Casinozer", competitors));
				Bookmaker b1=new Bookmaker("Unibet", competitors);
				Master m2=new Master(competitors,s2,8, competitionObservers);
				b1.displayAllRatings();
				((BookmakerDecorator) b2).displayAllRatingsdecorated();
				m2.register(j1);
				m2.register(j2);
				m2.register(b1);
				m2.register(b2);
				m2.play();
				ok=true;
			 }
			 else {
				System.out.print("Master doesn't exist \n");
				System.out.println("Please choose an existing Master");
			 }
			 }while(!ok);

			 done=true;
		 	}
		else {
			System.out.print("Competition doesn't exist \n");
			System.out.println("Please choose an existing competition ");
		}
		}while(!done);
	}
}
