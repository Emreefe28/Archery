package nl.hva.ict.se.sands.main;

import java.util.Comparator;
import java.util.List;

public class QuickSort {


    private Comparator<Archer> comparator = new TotalScoreComparator(); 
    private List list;

    
    private void quickSort(int low, int high) {
        int i = low;
        int j = high;
        Archer pivot = getPivot(low, high);

        while (i <= j) {
            while (this.comparator.compare((Archer) get(i), (Archer) pivot) < 0 && i < high) {
                i++;
            }
            while (this.comparator.compare((Archer) get(j), (Archer) pivot) > 0 && j > low) {
                j--;
            }

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        //recursion
        if (low < j)
            quickSort(low, j);
        if (i < high)
            quickSort(i, high);
    }

    
    public void sort(List<Archer> archers, Comparator<Archer> scoringScheme){
        comparator =  scoringScheme;
        list       = archers;
        if(list.size() == 0) return;
        this.quickSort(0, list.size()-1);
    }


    public static <Archer> void sortList(List<nl.hva.ict.se.sands.main.Archer> archers, java.util.Comparator<nl.hva.ict.se.sands.main.Archer> scoringScheme){
        QuickSort quickSort = new QuickSort();
        quickSort.sort(archers, scoringScheme);
    }
    
    /* This Method swaps two List items */
    protected void swap(int i, int j) {
    	Object tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    protected Archer get(int i) {
        return (Archer) list.get(i);
    }

    protected Archer getPivot(int low, int high) {
    	return (Archer) get(low + (high-low)/2);
    }

}
