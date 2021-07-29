package persistence;

import model.Rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRental(String name, Rental rental) {
        assertEquals(name, rental.getName());
    }
}
