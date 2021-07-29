package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    @Test
    public void testConstructor() {
        Rental r1 = new Rental("r1");
        r1.setPrice(1600);
        r1.setLocation("West End");
        r1.setSize(600);
        r1.setBeds(1);
        r1.setBaths(1);
        r1.setLaundry(true);
        r1.setParking(false);

        assertEquals(1600, r1.getPrice());
        assertEquals("West End", r1.getLocation());
        assertEquals(600, r1.getSize());
        assertEquals(1, r1.getBeds());
        assertEquals(1, r1.getBaths());
        assertTrue(r1.hasLaundry());
        assertFalse(r1.hasParking());
    }
}