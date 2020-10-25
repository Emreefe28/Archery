package nl.hva.ict.se.sands.main;

import java.util.*;

/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 *
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer {
    public final static int MAX_ARROWS = 3;
    public final static int MAX_ROUNDS = 10;
    private static Random randomizer = new Random();
    
    
    private static int idCounter = 135788 ;
    private final int id;
    public String firstName ;
    public String lastName  ;
    ArrayList<int[]> archerPoints ;
    
    public int totalScore = 0 ;
    

    

    /**
     * Constructs a new instance of Archer and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword. Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName the archers surname.
     */
    protected Archer(String firstName, String lastName) {
    	this.firstName = firstName ;
    	this.lastName  = lastName  ;
    	
    	this.id = idCounter ;
    	idCounter++ ;
    	
    	archerPoints = new ArrayList<>() ;
    	for (int i = 0 ; i < 10 ; i++) {
    		archerPoints.add(null);
    	}
    
    	
    }
    
   

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round the round for which to register the points, zero based.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(int round, int[] points) {
    	archerPoints.set(round, points);
    }
    

    public int getTotalScore() {
        int totalScore = 0 ;
    	for (int[] i : archerPoints) {
        	totalScore += (i[0] + i[1] + i[2]);
        }
    	return totalScore ;
    }

    /**
     * Returns the number of 10's scored by this archer.
     * @return the number of 10's for this archer.
     */
    public int getTens() {
    	int tensCount = 0 ;
    	for (int[] i : archerPoints) {
    		for (int n : i) {
    			if (n == 10) {
    				tensCount++;
    			}
    		}
        }
    	return tensCount ;
    }

    /**
     * Returns the number of 9's scored by this archer.
     * @return the number of 9's for this archer.
     */
    public int getNines() {
    	int ninesCount = 0 ;
    	for (int[] i : archerPoints) {
    		for (int n : i) {
    			if (n == 9) {
    				ninesCount++;
    			}
    		}
        }
    	return ninesCount ;
    }

    public int getId() {
        return id;
    }

    /**
     * This methods creates a List of archers. This method takes care of assigning each arhcher
     * a first name, surname and lets them should 30 arrows.
     *
     * @param nrOfArchers the number of archers in the list.
     * @return
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname());
            letArcherShoot(archer);
            archers.add(archer);
        }
        return archers;

    }

    private static void letArcherShoot(Archer archer) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootOneRound());
        }
    }

    private static int[] shootOneRound() {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shootArrow();
        }
        return points;
    }

    private static int shootArrow() {
        return 1 + randomizer.nextInt(10);
    }
    
    public String toString() {
    	return id + " " + "(" + getTotalScore() + ") " 
    	+ firstName + " " + lastName;
    }
    
}
