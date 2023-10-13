package main;

/** A class that represents a competitor */
public class Competitor {

	/** the competitor's name */
	private final String name;

	/** the competitor's number of points */
	private int points;

	/** initial number of points */
	private static final int INITIAL_PTS = 0;


	/** A competitor is defined by its name
	 * @param name the competitor's name
	 */
	public Competitor(String name) {
		this.name = name;
		this.points = INITIAL_PTS;
	}


	/** A competitor is defined by its name and a given number of points
	 * @param name the competitor's name
	 * @param pts the competitor's number of points
	 */
	public Competitor(String name, int pts) {
		this.name = name;
		this.points = pts;
	}

	/**
	 * @return the competitor's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the competitor's number of points
	 */
	public int getPoints() {
		return this.points;
	}

	/** Sets the competitor's number of points
	 * @param nb the number to set
	 */
	public void setPoints(int nb) {
		this.points = nb;
	}
	

	/**
	 * @return a string representation of a competitor
	 */
	public String toString() {
		return this.getName();
	}

  	/**
  	 * Adds one point to the competitor
  	 */
	public void addPoints() {
		this.points += 1;
	}


	/** Determines if two competitors are the same or not
	 * @param o the other object to compare with
	 * @return <code>true</code> iff two competitors are the same, <code>false</code> if not
	 */
	public boolean equals(Object o) {
		if(o instanceof Competitor) {
			Competitor other = (Competitor)o;
			return this.name == other.name &&
				   this.points == other.points;
		}
		return false;
	}

}
