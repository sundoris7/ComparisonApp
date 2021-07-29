package model;

import exception.EmptyListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RentalsList implements Writable {
    private List<Rental> rentalsList;
    private List<Rental> filteredList;
    private String name;

    // EFFECTS: constructs an empty list of rental objects and assigns name to the list
    public RentalsList(String name) {
        rentalsList = new ArrayList<>();
        this.name = name;
    }

    // EFFECTS: returns name of rental list
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns list of rental objects
    public List<Rental> getRentalsList() {
        return rentalsList;
    }

    // MODIFIES: rentalsList
    // EFFECTS: adds given rental object to the list
    public void addRental(Rental rental) {
        rentalsList.add(rental);
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, removes given rental object from the list;
    //          otherwise throws EmptyListException
    public void removeRental(int index) throws EmptyListException {
        if (!rentalsList.isEmpty()) {
            rentalsList.remove(index - 1);
        } else {
            throw new EmptyListException();
        }
    }

    // EFFECTS: returns size of the list
    public int size() {
        return rentalsList.size();
    }

    // EFFECTS: returns true if given rental object is in the list, false otherwise
    public boolean contains(Rental rental) {
        return rentalsList.contains(rental);
    }


    // EFFECTS: returns rental in list based on given index
    public Rental getRental(int index) {
        return rentalsList.get(index - 1);
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals <= given price;
    //          otherwise throws EmptyListException
    public List<Rental> filterByPrice(int price) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental : rentalsList) {
                if (rental.getPrice() <= price) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals that have given location name;
    //          otherwise throws EmptyListException
    public List<Rental> filterByLocation(String string) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental : rentalsList) {
                if (rental.getLocation().equals(string)) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals with size >= given size;
    //          otherwise throws EmptyListException
    public List<Rental> filterBySize(int size) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental: rentalsList) {
                if (rental.getSize() >= size) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals with beds >= given beds;
    //          otherwise throws EmptyListException
    public List<Rental> filterByBeds(int beds) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental: rentalsList) {
                if (rental.getBeds() >= beds) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals with baths >= given baths;
    //          otherwise throws EmptyListException
    public List<Rental> filterByBaths(int baths) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental: rentalsList) {
                if (rental.getBaths() >= baths) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals with or without laundry;
    //          otherwise throws EmptyListException
    public List<Rental> filterByLaundry(boolean laundry) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental: rentalsList) {
                if (rental.hasLaundry() == laundry) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: filteredList
    // EFFECTS: if rentalsList is not empty, returns filtered list of rentals with or without laundry;
    //          otherwise throws EmptyListException
    public List<Rental> filterByParking(boolean parking) throws EmptyListException {
        filteredList = new ArrayList<>();
        if (!rentalsList.isEmpty()) {
            for (Rental rental: rentalsList) {
                if (rental.hasParking() == parking) {
                    filteredList.add(rental);
                }
            }
        } else {
            throw new EmptyListException();
        }
        return filteredList;
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in ascending order of price;
    //          otherwise throws EmptyListException
    public void sortByPriceAscending() throws EmptyListException {
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(Comparator.comparing(Rental::getPrice));
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in descending order of price;
    //          otherwise throws EmptyListException
    public void sortByPriceDescending() throws EmptyListException {
        Comparator<Rental> comparator = Comparator.comparing(Rental::getPrice);
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(comparator.reversed());
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in alphabetical order by location;
    //          otherwise throws EmptyListException
    public void sortByLocationAZ() throws EmptyListException {
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(Comparator.comparing(Rental::getLocation));
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in reverse alphabetical order by location;
    //          otherwise throws EmptyListException
    public void sortByLocationZA() throws EmptyListException {
        Comparator<Rental> comparator = Comparator.comparing(Rental::getLocation);
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(comparator.reversed());
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in ascending order of size;
    //          otherwise throws EmptyListException
    public void sortBySizeAscending() throws EmptyListException {
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(Comparator.comparing(Rental::getSize));
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in descending order of size;
    //          otherwise throws EmptyListException
    public void sortBySizeDescending() throws EmptyListException {
        Comparator<Rental> comparator = Comparator.comparing(Rental::getSize);
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(comparator.reversed());
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in ascending order of beds;
    //          otherwise throws EmptyListException
    public void sortByBedsAscending() throws EmptyListException {
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(Comparator.comparing(Rental::getBeds));
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in descending order of beds;
    //          otherwise throws EmptyListException
    public void sortByBedsDescending() throws EmptyListException {
        Comparator<Rental> comparator = Comparator.comparing(Rental::getBeds);
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(comparator.reversed());
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in ascending order of baths;
    //          otherwise throws EmptyListException
    public void sortByBathsAscending() throws EmptyListException {
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(Comparator.comparing(Rental::getBaths));
        } else {
            throw new EmptyListException();
        }
    }

    // MODIFIES: rentalsList
    // EFFECTS: if rentalsList is not empty, returns sorted list of rentals in descending order of baths;
    //          otherwise throws EmptyListException
    public void sortByBathsDescending() throws EmptyListException {
        Comparator<Rental> comparator = Comparator.comparing(Rental::getBaths);
        if (!rentalsList.isEmpty()) {
            rentalsList.sort(comparator.reversed());
        } else {
            throw new EmptyListException();
        }
    }

    // Source: JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rentals", rentalsToJson());
        return json;
    }

    // Source: JsonSerializationDemo
    // EFFECTS: returns rentals in this rentalsList as a JSON array
    private JSONArray rentalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Rental r : rentalsList) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

}