package ui;

import exception.EmptyListException;
import model.Rental;
import model.RentalsList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Rental property comparison application
public class ComparisonApp {
    RentalsList rentalsList;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    public static final String JSON_STORE = "./data/rentalslist.json";
    private List<Rental> filteredList;

    // EFFECTS: runs the rental property comparison application
    public ComparisonApp() {
        System.out.println("Welcome to the Rental Comparison App!");
        input = new Scanner(System.in);
        rentalsList = new RentalsList("My rentals list");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runComparison();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Source: TellerApp, CPSC 210
    public void runComparison() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
                System.out.println("\nGoodbye!");
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "all":
                printList();
                break;
            case "add":
                newRental();
                break;
            case "remove":
                eraseRental();
                break;
            case "see":
                getRental();
                break;
            case "edit":
                editRental();
                break;
            case "sort":
                sortRentals();
                break;
            case "filter":
                filterRentals();
                break;
            case "save":
                saveList();
                break;
            case "load":
                loadList();
                break;
            default:
                System.out.println("Please enter a valid command");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nEnter one of the following to start comparing:");
        System.out.println("\tall -> see all rental properties in the list");
        System.out.println("\tadd -> add a new rental property to the list");
        System.out.println("\tremove -> remove a rental property from the list");
        System.out.println("\tsee -> see a rental property in detail");
        System.out.println("\tedit -> change a rental property's detail");
        System.out.println("\tsort -> sort the list by characteristic");
        System.out.println("\tfilter -> filter the list by characteristic");
        System.out.println("\tsave -> save list to file");
        System.out.println("\tload -> load list from file");
        System.out.println("\tquit -> quit the application");
    }

    // MODIFIES: this
    // EFFECTS: prints all rentals in the list
    private void printList() {
        if (rentalsList.size() == 0) {
            System.out.println("List of rental properties is empty.");
        } else {
            for (Rental r : rentalsList.getRentalsList()) {
                System.out.println(r.getLocation() + ", $ " + r.getPrice() + ", " + r.getSize() + " sqft, "
                        + r.getBeds() + " bedrooms, " + r.getBaths() + " bathrooms, has laundry? " + r.hasLaundry()
                        + ", has parking? " + r.hasParking());
            }
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: adds new rental to rentalsList
    protected void newRental() {
        Rental r0 = setRental();
        setLocation(r0);
        setPrice(r0);
        setSize(r0);
        setBedrooms(r0);
        setBathrooms(r0);
        setLaundry(r0);
        setParking(r0);
        rentalsList.addRental(r0);
        System.out.println("New rental property added to the list.");
    }

    private Rental setRental() {
        System.out.println("Create a name for the rental property: ");
        return new Rental(input.next());
    }

    // MODIFIES: new or selected rental
    // EFFECTS: sets location of new or selected rental
    private void setLocation(Rental r0) {
        System.out.println("Enter the location of the rental property: ");
        r0.setLocation(input.next());
    }

    // REQUIRES: price in form of an integer
    // MODIFIES: new or selected rental
    // EFFECTS: sets price of new or selected rental
    private void setPrice(Rental r0) {
        System.out.println("Enter the price of the rental property (without decimals): $");
        try {
            r0.setPrice(input.nextInt());
        } catch (Exception e) {
            System.out.println("Price entered is not an integer. Price recorded as $0.");
            input.next();
        }
    }

    // REQUIRES: size in form of an integer
    // MODIFIES: new or selected rental
    // EFFECTS: sets size of new or selected rental
    private void setSize(Rental r0) {
        System.out.println("Enter the size of the rental property: ");
        try {
            r0.setSize(input.nextInt());
        } catch (Exception e) {
            System.out.println("Size entered is not an integer. Size recorded as 0 sqft.");
            input.next();
        }
    }

    // REQUIRES: number of bedrooms in form of an integer
    // MODIFIES: new or selected rental
    // EFFECTS: sets number of bedrooms of new or selected rental
    private void setBedrooms(Rental r0) {
        System.out.println("Enter the number of bedrooms in the rental property: ");
        try {
            r0.setBeds(input.nextInt());
        } catch (Exception e) {
            System.out.println("Number of bedrooms entered is not an integer. Number of bedrooms recorded as 0.");
            input.next();
        }
    }

    // REQUIRES: number of bathrooms in form of an integer
    // MODIFIES: new or selected rental
    // EFFECTS: sets number of bathrooms of new or selected rental
    private void setBathrooms(Rental r0) {
        System.out.println("Enter the number of bathrooms in the rental property: ");
        try {
            r0.setBaths(input.nextInt());
        } catch (Exception e) {
            System.out.println("Number of bathrooms entered is not an integer. Number of bathrooms recorded as 0.");
            input.next();
        }
    }

    // REQUIRES: laundry in form of a boolean
    // MODIFIES: new or selected rental
    // EFFECTS: sets laundry feature of new or selected rental
    private void setLaundry(Rental r0) {
        System.out.println("Does the rental property have laundry? Enter yes or no: ");
        switch (input.next()) {
            case "yes":
                r0.setLaundry(true);
                break;
            case "no":
                r0.setLaundry(false);
                break;
            default:
                System.out.println("Entry does not match 'yes' or 'no'. Rental recorded as not having laundry.");
                break;
        }
    }

    // REQUIRES: parking in form of a boolean
    // MODIFIES: new or selected rental
    // EFFECTS: sets parking feature of new or selected rental
    private void setParking(Rental r0) {
        System.out.println("Does the rental property have parking? Enter yes or no: ");
        switch (input.next()) {
            case "yes":
                r0.setParking(true);
                break;
            case "no":
                r0.setParking(false);
                break;
            default:
                System.out.println("Entry does not match 'yes' or 'no'. Rental recorded as not having parking.");
                break;
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: removes a rental from the rentalsList
    public void eraseRental() {
        if (rentalsList.size() > 0) {
            printList();
            System.out.println("Which rental would you like to remove? Enter 1 for the first property in the list,"
                    + " 2 for the second, and so on: ");
            try {
                int entry = input.nextInt();
                if (entry > rentalsList.size()) {
                    System.out.println("No rental property recorded in that position of the list.");
                } else {
                    try {
                        rentalsList.removeRental(entry);
                        System.out.println("Rental deleted.");
                    } catch (EmptyListException e) {
                        System.out.println("List is empty, nothing to remove.");
                    }
                }
            } catch (Exception exception) {
                System.out.println("Please enter a valid command");
                input.next();
            }
        } else {
            printList();
        }
    }

    // MODIFIES: this
    // EFFECTS: finds and returns selected rental property from list
    private void getRental() {
        if (rentalsList.size() == 0) {
            printList();
        } else {
            for (Rental r : rentalsList.getRentalsList()) {
                System.out.println(r.getLocation() + ", $ " + r.getPrice());
            }
            System.out.println("Which rental would you like to see? Enter 1 for the first property in the list,"
                    + " 2 for the second, and so on: ");
            try {
                getSelection();
            } catch (Exception e) {
                System.out.println("Please enter a valid command");
                input.next();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: finds and returns selected rental property from list
    private void getSelection() {
        int entry;
        entry = input.nextInt();
        if (entry > rentalsList.size()) {
            System.out.println("No rental property recorded in that position of the list.");
        } else {
            Rental r0 = rentalsList.getRental(entry);
            System.out.println(r0.getLocation() + ", $ " + r0.getPrice() + ", " + r0.getSize() + " sqft, "
                    + r0.getBeds() + " bedrooms, " + r0.getBaths() + " bathrooms, has laundry? " + r0.hasLaundry()
                    + ", has parking? " + r0.hasParking());
        }
    }

    // MODIFIES: this, selected rental
    // EFFECTS: finds selected rental property from list and updates the rental's selected field
    private void editRental() {
        if (rentalsList.size() == 0) {
            printList();
        } else {
            printList();
            System.out.println("Which rental would you like to edit? Enter 1 for the first property in the list,"
                    + " 2 for the second, and so on: ");
            try {
                editSelection();
            } catch (Exception e) {
                System.out.println("Please enter a valid command");
                input.next();
            }
        }
    }

    // MODIFIES: this, selected rental
    // EFFECTS: updates selected rental's selected field
    private void editSelection() {
        int entry;
        entry = input.nextInt();
        if (entry > rentalsList.size()) {
            System.out.println("No rental property recorded in that position of the list.");
        } else {
            Rental r0 = rentalsList.getRental(entry);
            System.out.println(r0.getLocation() + ", $ " + r0.getPrice() + ", " + r0.getSize() + " sqft, "
                    + r0.getBeds() + " bedrooms, " + r0.getBaths() + " bathrooms, has laundry? " + r0.hasLaundry()
                    + ", has parking? " + r0.hasParking());
            displayEditMenu();
            String command = input.next();
            processEditCommand(command, r0);
            System.out.println("Rental property updated.");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays menu of edit options to user
    private void displayEditMenu() {
        System.out.println("\nWhat field would you like to change?");
        System.out.println("\tprice");
        System.out.println("\tlocation");
        System.out.println("\tsize");
        System.out.println("\tbedrooms");
        System.out.println("\tbathrooms");
        System.out.println("\tlaundry");
        System.out.println("\tparking");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for editing function
    private void processEditCommand(String command, Rental selected) {
        switch (command) {
            case "price":
                setPrice(selected);
                break;
            case "location":
                setLocation(selected);
                break;
            case "size":
                setSize(selected);
                break;
            case "bedrooms":
                setBedrooms(selected);
                break;
            case "bathrooms":
                setBathrooms(selected);
                break;
            case "laundry":
                setLaundry(selected);
                break;
            case "parking":
                setParking(selected);
                break;
            default:
                System.out.println("Please enter a valid command");
                break;
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentalsList by selected characteristic
    protected void sortRentals() {
        displaySortMenu();
        String command = input.next();
        processSortCommand(command);
    }

    // MODIFIES: this
    // EFFECTS: processes user command for sorting function
    private void processSortCommand(String command) {
        if ("priceLH".equals(command)) {
            sortPriceLH();
        } else if ("priceHL".equals(command)) {
            sortPriceHL();
        } else if ("locationAZ".equals(command)) {
            sortLocationAZ();
        } else if ("locationZA".equals(command)) {
            sortLocationZA();
        } else if ("sizeSL".equals(command)) {
            sortSizeSL();
        } else if ("sizeLS".equals(command)) {
            sortSizeLS();
        } else if ("bedroomsLM".equals(command)) {
            sortBedsLM();
        } else if ("bedroomsML".equals(command)) {
            sortBedsML();
        } else if ("bathroomsLM".equals(command)) {
            sortBathsLM();
        } else if ("bathroomsML".equals(command)) {
            sortBathsML();
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by price from lowest to highest
    private void sortPriceLH() {
        try {
            rentalsList.sortByPriceAscending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by price from highest to lowest
    private void sortPriceHL() {
        try {
            rentalsList.sortByPriceDescending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by location in alphabetical order
    private void sortLocationAZ() {
        try {
            rentalsList.sortByLocationAZ();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by location in reverse alphabetical order
    private void sortLocationZA() {
        try {
            rentalsList.sortByLocationZA();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by size from smallest to largest
    private void sortSizeSL() {
        try {
            rentalsList.sortBySizeAscending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by size from largest to smallest
    private void sortSizeLS() {
        try {
            rentalsList.sortBySizeDescending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by number of bedrooms from least to most
    private void sortBedsLM() {
        try {
            rentalsList.sortByBedsAscending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by number of bedrooms from most to least
    private void sortBedsML() {
        try {
            rentalsList.sortByBedsDescending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by number of bathrooms from least to most
    private void sortBathsLM() {
        try {
            rentalsList.sortByBathsAscending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this, rentalsList
    // EFFECTS: sorts rentals in list by number of bathrooms from most to least
    private void sortBathsML() {
        try {
            rentalsList.sortByBathsDescending();
            printList();
        } catch (EmptyListException e) {
            System.out.println("List is empty, nothing to sort.");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays menu of sort options to user
    private void displaySortMenu() {
        System.out.println("\nSort by:");
        System.out.println("\tpriceLH -> lowest to highest price");
        System.out.println("\tpriceHL -> highest to lowest price");
        System.out.println("\tlocationAZ -> location name from A to Z");
        System.out.println("\tlocationZA -> location name from Z to A");
        System.out.println("\tsizeSL -> smallest to largest size");
        System.out.println("\tsizeLS -> largest to smallest size");
        System.out.println("\tbedroomsLM -> least to most bedrooms");
        System.out.println("\tbedroomsML -> most to least bedrooms");
        System.out.println("\tbathroomsLM -> least to most bathrooms");
        System.out.println("\tbathroomsML -> most to least bathrooms");
    }

    // MODIFIES: this
    // EFFECTS: filters rentalsList by selected characteristic
    protected void filterRentals() {
        displayFilterMenu();
        String command = input.next();
        processFilterCommand(command);
    }

    // MODIFIES: this
    // EFFECTS: processes user command for filtering function
    private void processFilterCommand(String command) {
        switch (command) {
            case "price":
                filterPrice();
                break;
            case "location":
                filterLocation();
                break;
            case "size":
                filterSize();
                break;
            case "bedrooms":
                filterBeds();
                break;
            case "bathrooms":
                filterBaths();
                break;
            case "laundry":
                filterLaundry();
                break;
            case "parking":
                filterParking();
                break;
            default:
                System.out.println("Please enter a valid command");
                break;
        }
    }

    // REQUIRES: price in form of an integer
    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals that have a price <= user input
    private void filterPrice() {
        System.out.println("Filter list for rentals with price <=: ");
        try {
            try {
                filteredList = rentalsList.filterByPrice(input.nextInt());
                printFilteredList();
            } catch (EmptyListException e) {
                System.out.println("The list is empty, nothing to filter.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            input.next();
        }
    }

    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals that match location from user input
    private void filterLocation() {
        System.out.println("Filter list for rentals matching the location name: ");
        try {
            filteredList = rentalsList.filterByLocation(input.next());
            printFilteredList();
        } catch (EmptyListException e) {
            System.out.println("The list is empty, nothing to filter.");
        }
    }

    // REQUIRES: size in form of an integer
    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals that have a size >= user input
    private void filterSize() {
        System.out.println("Filter list for rentals with size >=: ");
        try {
            try {
                filteredList = rentalsList.filterBySize(input.nextInt());
                printFilteredList();
            } catch (EmptyListException e) {
                System.out.println("The list is empty, nothing to filter.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            input.next();
        }
    }

    // REQUIRES: number of bedrooms in form of an integer
    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals that have a number of bedrooms >= user input
    private void filterBeds() {
        System.out.println("Filter list for rentals with number of bedrooms >=: ");
        try {
            try {
                filteredList = rentalsList.filterByBeds(input.nextInt());
                printFilteredList();
            } catch (EmptyListException e) {
                System.out.println("The list is empty, nothing to filter.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            input.next();
        }
    }

    // REQUIRES: number of bathrooms in form of an integer
    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals that have a number of bathrooms >= user input
    private void filterBaths() {
        System.out.println("Filter list for rentals with number of bathrooms >=: ");
        try {
            try {
                filteredList = rentalsList.filterByBaths(input.nextInt());
                printFilteredList();
            } catch (EmptyListException e) {
                System.out.println("The list is empty, nothing to filter.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            input.next();
        }
    }

    // REQUIRES: laundry in form of a boolean
    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals with user specified laundry feature
    private void filterLaundry() {
        System.out.println("Filter list for rentals with laundry (enter true) or without laundry (enter false): ");
        try {
            try {
                filteredList = rentalsList.filterByLaundry(input.nextBoolean());
                printFilteredList();
            } catch (EmptyListException e) {
                System.out.println("The list is empty, nothing to filter.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            input.next();
        }
    }

    // REQUIRES: parking in form of a boolean
    // MODIFIES: this, filteredList
    // EFFECTS: filters list leaving only rentals with user specified parking feature
    private void filterParking() {
        System.out.println("Filter list for rentals with parking (enter true) or without parking (enter false): ");
        try {
            try {
                filteredList = rentalsList.filterByParking(input.nextBoolean());
                printFilteredList();
            } catch (EmptyListException e) {
                System.out.println("The list is empty, nothing to filter");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid command");
            input.next();
        }
    }

    // MODIFIES: this
    // EFFECTS: prints filtered list of rentals, or if filtered list is empty,
    // indicates that no rentals match filter criteria
    private void printFilteredList() {
        if (filteredList.size() == 0) {
            System.out.println("No rentals match filter criteria.");
        } else {
            for (Rental r : filteredList) {
                System.out.println(r.getLocation() + ", $ " + r.getPrice() + ", " + r.getSize() + " sqft, "
                        + r.getBeds() + " bedrooms, " + r.getBaths() + " bathrooms, has laundry? " + r.hasLaundry()
                        + ", has parking? " + r.hasParking());
            }
        }
    }

    // EFFECTS: displays menu of filter options to user
    private void displayFilterMenu() {
        System.out.println("\nFilter by:");
        System.out.println("\tprice -> filter by a specific price");
        System.out.println("\tlocation -> filter by a specific location name");
        System.out.println("\tsize -> filter by a specific size");
        System.out.println("\tbedrooms -> filter by a specific number of bedrooms");
        System.out.println("\tbathrooms -> filter by a specific number of bathrooms");
        System.out.println("\tlaundry -> filter by having or no laundry");
        System.out.println("\tparking -> filter by having or no parking");
    }

    // EFFECTS: saves rentalsList to file
    // Source: JsonSerializationDemo, CPSC 210
    public void saveList() {
        try {
            jsonWriter.open();
            jsonWriter.write(rentalsList);
            jsonWriter.close();
            System.out.println("Saved " + rentalsList.getName() + " to " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads rentalsList from file
    // Source: JsonSerializationDemo, CPSC 210
    public void loadList() {
        try {
            rentalsList = jsonReader.read();
            System.out.println("Loaded " + rentalsList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}