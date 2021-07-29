package model;

// Represents a rental property with the following features: price (in dollars), location (neighbourhood),
// size (in square feet), # of bedrooms, # of bathrooms, whether laundry on-site, and whether parking available.

import org.json.JSONObject;
import persistence.Writable;

public class Rental implements Writable {
    private String name;
    private int price;
    private String location;
    private int size;
    private int beds;
    private int baths;
    private boolean laundry;
    private boolean parking;

    // EFFECTS: creates new rental object
    public Rental(String name) {
        this.name = name;
        setPrice(price);
        setLocation(location);
        setSize(size);
        setBeds(beds);
        setBaths(baths);
        setLaundry(laundry);
        setParking(parking);
    }

    // setters
    public void setPrice(int price) {
        this.price = price;
    }

    public void setLocation(String location) {
        if (location == null) {
            this.location = "";
        } else {
            this.location = location;
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public void setLaundry(boolean laundry) {
        this.laundry = laundry;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }


    // getters
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getLocation() {
        return this.location;
    }

    public int getSize() {
        return this.size;
    }

    public int getBeds() {
        return this.beds;
    }

    public int getBaths() {
        return this.baths;
    }

    public boolean hasLaundry() {
        return this.laundry;
    }

    public boolean hasParking() {
        return this.parking;
    }

    // Source: JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("location", location);
        json.put("size", size);
        json.put("bedrooms", beds);
        json.put("bathrooms", baths);
        json.put("laundry", laundry);
        json.put("parking", parking);
        return json;
    }
}