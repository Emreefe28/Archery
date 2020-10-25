package nl.hva.ict.se.sands.main;

import java.util.ArrayList;

public class App {
	
	private static ArrayList<Long> selectionSortTimes = new ArrayList<>();
	private static ArrayList<Long> collectionSortTimes = new ArrayList<>();
	private static ArrayList<Long> quickSortTimes     = new ArrayList<>();
	
	public static void main(String[] args) {
		

		// Generating and printing Archers
		System.out.println("Generated Archers: \n");
		ArrayList<Archer> archers = (ArrayList) Archer.generateArchers(10);
		
		for (Archer archer : archers) {
			System.out.println(archer);
		}
		System.out.println();
		
		// Sorting Archers and Printing them (in ascending order)
		System.out.println("Artists Sorted: \n");
		ChampionSelector.quickSort(archers, new TotalScoreComparator());
		
		for (Archer archer : archers) {
			System.out.println(archer);
		}
		
		// Finding the Champion and printing them
		System.out.println("\nThe Champion is : " + ChampionSelector.findChampion(archers));
		
		
		
		
		
		// BENCHMARKING CODE //

		for (int i = 0 ; i < 10 ; i++) {
			benchmarkSortingAlgorithms();
		}
		
		System.out.println("SelectionSort Times : " + selectionSortTimes);
		System.out.println("CollectionSort Times : " + collectionSortTimes);
		System.out.println("QuickSort Times : " + quickSortTimes);
		
		System.out.println("\nBenchmark Finished\n");

		
	}
	
	
	public static void benchmarkSortingAlgorithms() {
		
		System.out.printf("%5s|%10s|%10s|%10s|" , "i" , "Selection" 
				, "Collection" , "Quicksort");
		
		// Sorting Procedure
		// It benchmarks up to Lists with 25600 elements due to taking
		// too much time
		for (int i = 100 ; i <= 5000000 ; i = i * 2 ) {
			
			// Creating first List
			ArrayList<Archer> archers = (ArrayList) Archer.generateArchers(i);
			
			// Copying first list 2 times (1 for each Sorting Algorithm)
			
			ArrayList<Archer> archers2 = new ArrayList<>();
			for (Archer archer : archers) {
				archers2.add(archer);
			}
			
			ArrayList<Archer> archers3 = new ArrayList<>();
			for (Archer archer : archers) {
				archers3.add(archer);
			}
			
			
			TotalScoreComparator comparator = new TotalScoreComparator();
			
			// Selection Sort
			long startTimeSelection = System.currentTimeMillis();
			ChampionSelector.selectionSort(archers, comparator );
			long endTimeSelection   = System.currentTimeMillis();
			
			// Collection Sort
			long startTimeCollection = System.currentTimeMillis();
			ChampionSelector.collectionSort(archers2, comparator );
			long endTimeCollection   = System.currentTimeMillis();
			
			// Quicksort
			long startTimeQuicksort = System.currentTimeMillis();
			ChampionSelector.quickSort(archers3, comparator );
			long endTimeQuicksort   = System.currentTimeMillis();
			
			System.out.printf("\n%5d|%10d|%10d|%10d|" , i ,
					endTimeSelection  - startTimeSelection  ,
					endTimeCollection - startTimeCollection ,
					endTimeQuicksort  - startTimeQuicksort);
			
			
			// Checks how much time each algorithm took
			// if the time exceeds 20 seconds, then the loop ends
			if (endTimeSelection - startTimeSelection > 20000 ||
				endTimeCollection - startTimeCollection > 20000 ||
				endTimeQuicksort  - startTimeQuicksort > 20000) 
			{
				selectionSortTimes.add(endTimeSelection  - startTimeSelection) ;
				collectionSortTimes.add(endTimeCollection - startTimeCollection) ;
				quickSortTimes.add(endTimeQuicksort  - startTimeQuicksort) ;
				
				break ;
			}
			
			
			
		}
	}
}
