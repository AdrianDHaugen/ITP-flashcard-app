package core;

import java.io.IOException;
import java.util.ArrayList;

import json.FileHandler;
import json.JsonHandler;

public class Context {
    // Singleton instance of the class
    private static Context instance = new Context();

    // Static method to get the singleton instance
    public static Context getInstance() {
        return instance;
    }

    // Method to reset the singleton instance
    public static void reset() {
        instance = new Context();
    }

    // List to store Deck objects
    private ArrayList<Deck> decks = new ArrayList<>();
    // Reference to currently active Deck
    private Deck currentDeck;

    // Loads a deck from a save file
    public void loadDeck() throws IOException {
        FileHandler fileHandler = new FileHandler();
        String content = fileHandler.loadSavefile();

        this.decks = JsonHandler.deserializeDecks(content);
    }

    // Saves the current state of decks to a file
    public void saveDeck() throws IOException {
        String jo = JsonHandler.serializeDecks(decks);

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeToFile(jo.toString());
    }

    // Adds a new Deck to the list
    public void addDeck(Deck d) {
        decks.add(d);
    }

    // Removes a Deck from the list
    public void removeDeck(Deck d) {
        decks.remove(d);
    }

    // Checks if the deck list is empty
    public boolean isEmpty() {
        return decks.isEmpty();
    }

    // Getter for the current deck
    public Deck getCurrentDeck() {
        return currentDeck;
    }

    // Setter for the current deck
    public void setCurrentDeck(Deck deck) {
        this.currentDeck = deck;
    }

    // Getter for the list of all decks
    public ArrayList<Deck> getDecks() {
        return decks;
    }
}
