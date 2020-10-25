package nl.hva.ict.se.sands.main;

import java.util.Comparator;

public class TotalScoreComparator implements Comparator<Archer>{

	@Override
	public int compare(Archer archer1, Archer archer2) {
		if (archer1.getTotalScore() == archer2.getTotalScore()) {
			return 0 ;
		} else if (archer1.getTotalScore() > archer2.getTotalScore()) {
			return 1 ;
		} else {
			return -1 ;
		}
	}

}
