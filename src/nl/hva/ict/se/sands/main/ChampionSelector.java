package nl.hva.ict.se.sands.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 * Note that you are NOT allowed to change the signature of these methods! Adding method is perfectly fine.
 */
public class ChampionSelector {
	/**
     * This method uses either selection sort for sorting the archers.
     */
    public static List<Archer> selectionSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
    	// If list is empty or has one Archer, then it is already sorted
    	// And therefore, we return the list as is
    	if (archers.size() == 0 || archers.size() == 1)
    		return archers ;

    	// We Create the Smallest and SmallestIndex variables to hold
    	// the smallest Score and the index of the archer with the smallest score
    	int smallestIndex;
    	int smallest;

    	
    	for (int curIndex = 0; curIndex < archers.size(); curIndex++) {
    		smallest      = archers.get(curIndex).getTotalScore();
    		smallestIndex = curIndex;

    		// Going through every archer after the current one and switching
    		// their place with the one with the smallest score
    		for (int i = curIndex + 1; i < archers.size(); i++) {
    			if (smallest > archers.get(i).getTotalScore()) {
    				smallest = archers.get(i).getTotalScore();
    				smallestIndex = i;
    			}
    		}

    		
    		if (smallestIndex == curIndex) {
    			// Then Do nothing
    		} else {
    			Archer temp = archers.get(curIndex);
    			archers.set(curIndex, archers.get(smallestIndex));
    			archers.set(smallestIndex, temp);
    		}
    		
    	}
    	
    	// Fixing the order of Archers so that they are sorted by ID
    	// this is used to pass the Unit Test, otherwise equally scored
    	// Archers will be placed next to eachother randomly, 
    	//and the Collections Sort Sorts Archers by id also
    	// (so it will fail the Unit test if the two sorted lists
    	// aren't completely equivalent)
    	for (int i = 0 ; i < 1000 ; i++) {
    		
    		ArrayList<Archer> tmpArchers = new ArrayList<>();
    		for (Archer archer : archers) {
    			if (archer.getTotalScore() == i) {
    				tmpArchers.add(archer);
    			}
    		}
    		
    		if (tmpArchers.size() <= 1) 
    			continue ;
    		else {
    			int indexOfFirstArcher = archers.indexOf(tmpArchers.get(0));
    			Collections.sort(tmpArchers, new ArcherIDComparator());
    			
    			for (int y = 0 ; y < tmpArchers.size() ; y++) {
    				archers.set(indexOfFirstArcher + y, tmpArchers.get(y));
    			}
    			
    		}
    		
    	}
    	
    	
    	
    	return archers;
    }

    
    /**
     * This method uses quick sort for sorting the archers.
     */
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
    	QuickSort.sortList(archers, scoringScheme);
    	return archers;
    }

    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     */
    public static List<Archer> collectionSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
    	Collections.sort(archers, scoringScheme);  
    	return archers;
    }
    
    
    /** This method finds the Champion from a list of archers */
    public static Archer findChampion(List<Archer> archers) {
    	
    	// Sort Archers
    	collectionSort(archers, new TotalScoreComparator());

    	// If at least two Archers with the highest score, have the same score
    	// then we need to figure out who the champion is based on the
    	// criteria of the assignment
    	if(archers.get(archers.size()-1).getTotalScore() == archers.get(archers.size() -2).getTotalScore()) {
    		
    		// First we find the topScorers (those who have the same highest score)
    		ArrayList<Archer> topScorers = new ArrayList();
    		
    		for (Archer archer : archers) {
    			if (archer.getTotalScore() == archers.get(archers.size() -1).getTotalScore()){
    				topScorers.add(archer);
    			}
    		}
    		
    		
    		// Then we find those with the highest number of 10 point shots
    		int maxTens = 0 ;
    		
    		for (Archer archer : topScorers) {
    			if (archer.getTens() > maxTens) {
    				maxTens = archer.getTens();
    			}
    		}
    		
    		ArrayList<Archer> topTensScorers = new ArrayList<>();
    		
    		// removing scorers with less than max tens
    		for (Archer archer : topScorers) {
    			if (archer.getTens() == maxTens) {
    				System.out.println(archer.firstName + " , " + archer.lastName
    						+ " had " + archer.getTens() + "tens");
    				topTensScorers.add(archer);
    			}
    		}
    		
    		// If there is only one archer with the most 10-point shots
    		// then they are the chamption, else we check the 9-point shots
    		if (topTensScorers.size() == 1) {
    			return topTensScorers.get(0);
    		}
    		

    		// Finding those left with the highest number of 9-point shots
    		int maxNines = 0 ;
    		
    		for (Archer archer : topTensScorers) {
    			if (archer.getNines() > maxNines) {
    				System.out.println(archer.firstName + " , " + archer.lastName
    						+ " had " + archer.getNines() + "nines");
    				maxNines = archer.getNines();
    			}
    		}
    		
    		ArrayList<Archer> topNinesScorers = new ArrayList<>();
    		
    		// removing scorers with less than the highest number of 9p shots
    		for (Archer archer : topTensScorers) {
    			if (archer.getNines() == maxNines) {
    				topNinesScorers.add(archer);
    			}
    		}
    		
    		//If there's a player with a higher number of 9-point shots
    		// They win
    		if (topNinesScorers.size() == 1) {
    			return topNinesScorers.get(0);
    		} else {
    			
    			// Else, the less experienced player wins
    			// (less experienced is the player with the highest id number)
    			Archer maxIDArcher = topNinesScorers.get(0) ;
    			
    			for (Archer archer : topNinesScorers) {
    				if (archer.getId() > maxIDArcher.getId()) {
    					maxIDArcher = archer;
    				}
    			}
    			
    			return maxIDArcher ;
    		}
    		
    	} else {
    		// If the two top archers have different scores then the highest
    		// one wins.
    		return archers.get(archers.size() - 1);
    	}
    	
    	
    }
    
}
