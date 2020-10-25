package nl.hva.ict.se.sands.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hva.ict.se.sands.main.Archer;
import nl.hva.ict.se.sands.main.ChampionSelector;
import nl.hva.ict.se.sands.main.TotalScoreComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChampionSelectorTest {
    protected TotalScoreComparator comparator;

    @BeforeEach
    public void createComparator() {
        comparator = new TotalScoreComparator();
    }

    @Test
    public void selInsSortAndCollectionSortResultInSameOrder() {
        List<Archer> unsortedArchersForSelIns = Archer.generateArchers(22);
        List<Archer> unsortedArchersForCollection = new ArrayList<>(unsortedArchersForSelIns);

        List<Archer> sortedArchersSelIns = ChampionSelector.selectionSort(unsortedArchersForSelIns, comparator);
        List<Archer> sortedArchersCollection = ChampionSelector.collectionSort(unsortedArchersForCollection, comparator);
        
        
        assertEquals(sortedArchersCollection, sortedArchersSelIns);
    }

}
