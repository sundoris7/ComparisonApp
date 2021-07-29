package model;

import exception.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentalsListTest {

    private RentalsList testList;
    private List<Rental> testFilteredList;
    private final Rental r1 = new Rental("r1");
    private final Rental r2 = new Rental("r2");
    private final Rental r3 = new Rental("r3");
    private final Rental r4 = new Rental("r4");

    @BeforeEach
    public void setup() {
        testList = new RentalsList("List 1");

        r1.setPrice(1300);
        r1.setLocation("West End");
        r1.setSize(400);
        r1.setBeds(0);
        r1.setBaths(1);
        r1.setLaundry(false);
        r1.setParking(false);

        r2.setPrice(3600);
        r2.setLocation("Kits");
        r2.setSize(2200);
        r2.setBeds(3);
        r2.setBaths(2);
        r2.setLaundry(false);
        r2.setParking(true);

        r3.setPrice(2100);
        r3.setLocation("Yaletown");
        r3.setSize(600);
        r3.setBeds(2);
        r3.setBaths(1);
        r3.setLaundry(true);
        r3.setParking(false);

        r4.setPrice(1800);
        r4.setLocation("Kits");
        r4.setSize(600);
        r4.setBeds(1);
        r4.setBaths(1);
        r4.setLaundry(true);
        r4.setParking(true);
    }

    @Test
    public void testGetRentalsList() {
        testList.addRental(r1);
        testList.addRental(r3);

        testList.getRentalsList();
        assertEquals(2, testList.size());
        assertTrue(testList.contains(r1));
        assertTrue(testList.contains(r3));
        assertEquals(r1, testList.getRental(1));
        assertEquals(r3, testList.getRental(2));

    }

    @Test
    public void testAddRentalToEmptyList() {
        testList.addRental(r1);

        assertEquals(1, testList.size());
        assertTrue(testList.contains(r1));
    }

    @Test
    public void testAddRental() {
        testList.addRental(r1);
        testList.addRental(r3);
        testList.addRental(r2);

        assertEquals(3, testList.size());
        assertTrue(testList.contains(r1));
        assertTrue(testList.contains(r3));
        assertTrue(testList.contains(r2));
    }

    @Test
    public void testRemoveRentalNonEmptyList() {
        testList.addRental(r1);
        testList.addRental(r2);

        try {
            testList.removeRental(1);
            assertEquals(1, testList.size());
            assertTrue(testList.contains(r2));
            assertFalse(testList.contains(r1));
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testRemoveRentalEmptyList() {
        try {
            testList.removeRental(1);
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            // pass, expected
        }
        assertEquals(0, testList.size());
        assertFalse(testList.contains(r2));
        assertFalse(testList.contains(r1));
    }

    @Test
    public void testGetRental() {
        testList.addRental(r1);
        testList.addRental(r2);

        testList.getRental(1);

        assertEquals(r1, testList.getRental(1));
    }

    @Test
    public void testFilterByPriceEmptyList() {
        try {
            testFilteredList = testList.filterByPrice(1000);
            fail("Empty list exception should have been thrown!");

        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception thrown as expected!");
        }
    }

    @Test
    public void testFilterByPriceNoResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByPrice(1000);
            assertEquals(0, testFilteredList.size());
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByPriceSomeResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByPrice(1800);
            assertEquals(2, testFilteredList.size());
            assertTrue(testFilteredList.contains(r1));
            assertFalse(testFilteredList.contains(r2));
            assertFalse(testFilteredList.contains(r3));
            assertTrue(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByPriceAllResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByPrice(4000);
            assertEquals(4, testFilteredList.size());
            assertTrue(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertTrue(testFilteredList.contains(r3));
            assertTrue(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByLocationEmptyList() {
        try {
            testFilteredList = testList.filterByLocation("Burnaby");
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception thrown as expected!");
        }
    }

    @Test
    public void testFilterByLocationNoResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByLocation("Burnaby");
            assertEquals(0, testFilteredList.size());
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByLocation() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByLocation("Kits");
            assertEquals(2, testFilteredList.size());
            assertFalse(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertFalse(testFilteredList.contains(r3));
            assertTrue(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterBySizeEmptyList() {
        try {
            testFilteredList = testList.filterBySize(2500);
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception thrown as expected!");
        }
    }

    @Test
    public void testFilterBySizeNoResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterBySize(2500);
            assertEquals(0, testFilteredList.size());
        } catch (EmptyListException e) {
            fail("Empty list exception thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterBySize() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterBySize(700);
            assertEquals(1, testFilteredList.size());
            assertFalse(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertFalse(testFilteredList.contains(r3));
            assertFalse(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByBedsEmptyList() {
        try {
            testFilteredList = testList.filterByBeds(4);
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testFilterByBedsNoResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByBeds(4);
            assertEquals(0, testFilteredList.size());
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByBeds() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByBeds(2);
            assertEquals(2, testFilteredList.size());
            assertFalse(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertTrue(testFilteredList.contains(r3));
            assertFalse(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByBathsEmptyList() {
        try {
            testFilteredList = testList.filterByBaths(4);
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testFilterByBathsNoResults() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByBaths(4);
            assertEquals(0, testFilteredList.size());
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByBaths() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByBaths(2);
            assertEquals(1, testFilteredList.size());
            assertFalse(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertFalse(testFilteredList.contains(r3));
            assertFalse(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByLaundryEmptyList() {
        try {
            testFilteredList = testList.filterByLaundry(true);
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testFilterByLaundry() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByLaundry(true);
            assertEquals(2, testFilteredList.size());
            assertFalse(testFilteredList.contains(r1));
            assertFalse(testFilteredList.contains(r2));
            assertTrue(testFilteredList.contains(r3));
            assertTrue(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByNoLaundry() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByLaundry(false);
            assertEquals(2, testFilteredList.size());
            assertTrue(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertFalse(testFilteredList.contains(r3));
            assertFalse(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByParkingEmptyList() {
        try {
            testFilteredList = testList.filterByParking(true);
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testFilterByParking() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByParking(true);
            assertEquals(2, testFilteredList.size());
            assertFalse(testFilteredList.contains(r1));
            assertTrue(testFilteredList.contains(r2));
            assertFalse(testFilteredList.contains(r3));
            assertTrue(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testFilterByNoParking() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testFilteredList = testList.filterByParking(false);
            assertEquals(2, testFilteredList.size());
            assertTrue(testFilteredList.contains(r1));
            assertFalse(testFilteredList.contains(r2));
            assertTrue(testFilteredList.contains(r3));
            assertFalse(testFilteredList.contains(r4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByPriceAscendingEmptyList() {
        try {
            testList.sortByPriceAscending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByPriceAscending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByPriceAscending();
            assertEquals(r1, testList.getRental(1));
            assertEquals(r4, testList.getRental(2));
            assertEquals(r3, testList.getRental(3));
            assertEquals(r2, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByPriceDescendingEmptyList() {
        try {
            testList.sortByPriceDescending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByPriceDescending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByPriceDescending();
            assertEquals(r2, testList.getRental(1));
            assertEquals(r3, testList.getRental(2));
            assertEquals(r4, testList.getRental(3));
            assertEquals(r1, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByLocationAZEmptyList() {
        try {
            testList.sortByLocationAZ();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByLocationAZ() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByLocationAZ();
            assertEquals(r2, testList.getRental(1));
            assertEquals(r4, testList.getRental(2));
            assertEquals(r1, testList.getRental(3));
            assertEquals(r3, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByLocationZAEmptyList() {
        try {
            testList.sortByLocationZA();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByLocationZA() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByLocationZA();
            assertEquals(r3, testList.getRental(1));
            assertEquals(r1, testList.getRental(2));
            assertEquals(r2, testList.getRental(3));
            assertEquals(r4, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortBySizeAscendingEmptyList() {
        try {
            testList.sortBySizeAscending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortBySizeAscending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortBySizeAscending();
            assertEquals(r1, testList.getRental(1));
            assertEquals(r3, testList.getRental(2));
            assertEquals(r4, testList.getRental(3));
            assertEquals(r2, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortBySizeDescendingEmptyList() {
        try {
            testList.sortBySizeDescending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortBySizeDescending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortBySizeDescending();
            assertEquals(r2, testList.getRental(1));
            assertEquals(r3, testList.getRental(2));
            assertEquals(r4, testList.getRental(3));
            assertEquals(r1, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByBedsAscendingEmptyList() {
        try {
            testList.sortByBedsAscending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByBedsAscending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByBedsAscending();
            assertEquals(r1, testList.getRental(1));
            assertEquals(r4, testList.getRental(2));
            assertEquals(r3, testList.getRental(3));
            assertEquals(r2, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByBedsDescendingEmptyList() {
        try {
            testList.sortByBedsDescending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByBedsDescending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByBedsDescending();
            assertEquals(r2, testList.getRental(1));
            assertEquals(r3, testList.getRental(2));
            assertEquals(r4, testList.getRental(3));
            assertEquals(r1, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByBathsAscendingEmptyList() {
        try {
            testList.sortByBathsAscending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByBathsAscending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByBathsAscending();
            assertEquals(r1, testList.getRental(1));
            assertEquals(r3, testList.getRental(2));
            assertEquals(r4, testList.getRental(3));
            assertEquals(r2, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }

    @Test
    public void testSortByBathsDescendingEmptyList() {
        try {
            testList.sortByBathsDescending();
            fail("Empty list exception should have been thrown!");
        } catch (EmptyListException e) {
            System.out.println("Test passed! Empty list exception was thrown as expected!");
        }
    }

    @Test
    public void testSortByBathsDescending() {
        testList.addRental(r1);
        testList.addRental(r2);
        testList.addRental(r3);
        testList.addRental(r4);

        try {
            testList.sortByBathsDescending();
            assertEquals(r2, testList.getRental(1));
            assertEquals(r1, testList.getRental(2));
            assertEquals(r3, testList.getRental(3));
            assertEquals(r4, testList.getRental(4));
        } catch (EmptyListException e) {
            fail("Empty list exception was thrown when it shouldn't have been!");
        }
    }
}