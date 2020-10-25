package nl.hva.ict.se.sands.main;

import java.util.Comparator;

public class ArcherIDComparator implements Comparator<Archer>{

	@Override
	public int compare(Archer archer1, Archer archer2) {
		if (archer1.getId() == archer2.getId()) {
			return 0 ;
		} else if (archer1.getId() > archer2.getId()) {
			return 1 ;
		} else {
			return -1 ;
		}
	}

}
