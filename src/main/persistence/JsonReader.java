package persistence;

import model.Rental;
import model.RentalsList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Source: JsonSerializationDemo
// Represents a reader that reads rentalsList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads rentalsList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RentalsList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRentalsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses rentalsList from JSON object and returns it
    private RentalsList parseRentalsList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RentalsList rentalsList = new RentalsList(name);
        addRentals(rentalsList, jsonObject);
        return rentalsList;
    }

    // MODIFIES: rentalsList
    // EFFECTS: parses rentals from JSON object and adds them to rentalsList
    private void addRentals(RentalsList rentalsList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rentals");
        for (Object json : jsonArray) {
            JSONObject nextRental = (JSONObject) json;
            addFeatures(rentalsList, nextRental);
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: parses rental from JSON object and adds it to rentalsList
    private void addFeatures(RentalsList rentalsList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Rental rental = new Rental(name);
        rental.setPrice(jsonObject.getInt("price"));
        rental.setLocation(jsonObject.getString("location"));
        rental.setSize(jsonObject.getInt("size"));
        rental.setBeds(jsonObject.getInt("bedrooms"));
        rental.setBaths(jsonObject.getInt("bathrooms"));
        rental.setLaundry(jsonObject.getBoolean("laundry"));
        rental.setParking(jsonObject.getBoolean("parking"));
        rentalsList.addRental(rental);
    }
}
