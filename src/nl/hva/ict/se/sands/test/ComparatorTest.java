package nl.hva.ict.se.sands.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hva.ict.se.sands.main.Archer;
import nl.hva.ict.se.sands.main.TotalScoreComparator;

public class ComparatorTest {
    
	TotalScoreComparator comparator ;
	
	@BeforeEach
    public void createComparator() {
        comparator = new TotalScoreComparator();
    }
	
	@Test
    public void equalScoreArchersReturnsZero() {
        List<Archer> testArchers = Archer.generateArchers(2);
        
        // Overriding archerscores to have the same ones
        testArchers.get(0).registerScoreForRound(0, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(1, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(2, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(3, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(4, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(5, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(6, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(7, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(8, new int[] {0,0,0});
        testArchers.get(0).registerScoreForRound(9, new int[] {0,0,0});
		
        testArchers.get(1).registerScoreForRound(0, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(1, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(2, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(3, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(4, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(5, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(6, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(7, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(8, new int[] {0,0,0});
        testArchers.get(1).registerScoreForRound(9, new int[] {0,0,0});
		
        int compareResult = 
        		comparator.compare(testArchers.get(0), testArchers.get(1));
        assertTrue(compareResult == 0);
    }

	@Test
    public void firstArcherHigherScoreReturnsOne() {
        List<Archer> testArchers = Archer.generateArchers(2);
        
        // Overriding scores to have the first archer have the highest
        testArchers.get(0).registerScoreForRound
        (0, new int[] {100000000,0,0});
        
	
		
        int compareResult = 
        		comparator.compare(testArchers.get(0), testArchers.get(1));
        assertTrue(compareResult == 1);
    }
	
	@Test
    public void secondArcherHigherScoreReturnsMinusOne() {
        List<Archer> testArchers = Archer.generateArchers(2);
        
        // Overriding scores to have the second archer have the highest
        testArchers.get(1).registerScoreForRound
        (0, new int[] {100000000,0,0});
        
	
		
        int compareResult = 
        		comparator.compare(testArchers.get(0), testArchers.get(1));
        assertTrue(compareResult == -1);
    }
	
	
}
