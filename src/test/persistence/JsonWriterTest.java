package persistence;

import model.Rental;
import model.RentalsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Source: JsonSerializationDemo, CPSC 210
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RentalsList rentalsList = new RentalsList("My rentals list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RentalsList rentalsList = new RentalsList("My rentals list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRentalsList.json");
            writer.open();
            writer.write(rentalsList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRentalsList.json");
            rentalsList = reader.read();
            assertEquals("My rentals list", rentalsList.getName());
            assertEquals(0, rentalsList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RentalsList rentalsList = new RentalsList("My rentals list");
            rentalsList.addRental(new Rental("DT1"));
            rentalsList.addRental(new Rental("YT1"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRentalsList.json");
            writer.open();
            writer.write(rentalsList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRentalsList.json");
            rentalsList = reader.read();
            assertEquals("My rentals list", rentalsList.getName());
            List<Rental> savedList = rentalsList.getRentalsList();
            assertEquals(2, rentalsList.size());
            checkRental("DT1", rentalsList.getRental(1));
            checkRental("YT1", rentalsList.getRental(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}