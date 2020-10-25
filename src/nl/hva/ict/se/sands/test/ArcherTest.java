package nl.hva.ict.se.sands.test;


import nl.hva.ict.se.sands.main.Archer;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.*;

import static junit.framework.Assert.assertTrue;


public class ArcherTest {

    @Test
    public void archerIdsIncreaseCorrectly() {
        List<Archer> archers = Archer.generateArchers(3);
        assertTrue(archers.get(1).getId() == archers.get(0).getId()+ 1);
        assertTrue(archers.get(2).getId() == archers.get(1).getId()+ 1);
    }

    @Test
    public void visibilityOfConstructorsShouldBeUnchanged() {
        for (Constructor constructor : Archer.class.getDeclaredConstructors()) {
            assertTrue((constructor.getModifiers() & 0x00000004) != 0);
        }
    }

    @Test
    public void idFieldShouldBeUnchangeable() throws NoSuchFieldException {
        assertTrue((Archer.class.getDeclaredField("id").getModifiers() & 0x00000010) != 0);
    }

}
