package persistence;

import model.Rental;
import model.RentalsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RentalsList rentalsList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRentalsList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRentalsList.json");
        try {
            RentalsList rentalsList = reader.read();
            assertEquals("My rentals list", rentalsList.getName());
            assertEquals(0, rentalsList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRentalsList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRentalsList.json");
        try {
            RentalsList rentalsList = reader.read();
            assertEquals("My rentals list", rentalsList.getName());
            List<Rental> rentals = rentalsList.getRentalsList();
            assertEquals(2, rentalsList.size());
            checkRental("DT1", rentalsList.getRental(1));
            checkRental("YT1", rentalsList.getRental(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}