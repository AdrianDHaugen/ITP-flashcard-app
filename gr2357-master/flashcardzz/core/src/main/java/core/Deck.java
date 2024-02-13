package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Deck implements Iterable<Card> {

    // List to store Card objects
    private List<Card> cardDeck = new ArrayList<>();
    // Name of the deck
    private String name;
    // Index to keep track of the current card in the deck
    private int currentCardIndex = 0;

    // Constant representing the end of the deck
    public static final Card END_OF_DECK_CARD = new Card("End of Deck", "End of Deck");

    // Enum to represent the side of a card (front or back)
    private CardSide cardSide = CardSide.FRONT;
    private CardSide currentCardSide = CardSide.FRONT;

    // Enum to define card sides
    public enum CardSide {
        FRONT, BACK
    }

    // Constructor for deck with just a name
    public Deck(final String deckName) {
        name = deckName;
    }

    // Constructor for deck with a list of cards and a name
    public Deck(final List<Card> cardDeck, final String deckName) {
        this.name = deckName;
        this.cardDeck = cardDeck;
    }

    // JSON constructor for creating a Deck object from JSON
    @JsonCreator
    public Deck(
            @JsonProperty("cardDeck") List<Card> cardDeck,
            @JsonProperty("name") String name,
            @JsonProperty("currentCardSide") CardSide currentCardSide,
            @JsonProperty("cardDeckSize") int cardDeckSize,
            @JsonProperty("currentCard") Card currentCard) {
        this.cardDeck = cardDeck;
        this.name = name;
        this.currentCardSide = currentCardSide;
    }

    // Getters and setters for deck properties
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardSide getCurrentCardSide() {
        return currentCardSide;
    }

    public List<Card> getCardDeck() {
        return new ArrayList<>(cardDeck);
    }

    public int getCardDeckSize() {
        return cardDeck.size();
    }

    // Methods to manipulate the deck
    public void addCard(final Card card) {
        cardDeck.add(card);
    }

    public void removeCard(final Card card) {
        cardDeck.remove(card);
    }

    // Flips the current card from front to back or vice versa
    public void flipCard() {
        currentCardSide = currentCardSide == CardSide.FRONT ? CardSide.BACK : CardSide.FRONT;
    }

    // Toggles the default card side for new cards
    public void toggleCardSide() {
        cardSide = cardSide == CardSide.FRONT ? CardSide.BACK : CardSide.FRONT;
        currentCardSide = cardSide;
    }

    // Resets the current card side to default
    public void resetCardSide() {
        currentCardSide = cardSide;
    }

    // Clears all cards from the deck
    public void clearDeck() {
        cardDeck.clear();
    }

    // Retrieves the current card or the end-of-deck card if no current card
    public Card getCurrentCard() {
        if (currentCardIndex < 0 || cardDeck.isEmpty()) {
            return END_OF_DECK_CARD;
        }
        return cardDeck.get(currentCardIndex);
    }

    // Navigation methods to move through the deck
    public void goToNextCard() {
        currentCardIndex = (currentCardIndex + 1) % cardDeck.size();
        currentCardSide = CardSide.FRONT;
    }

    public void goToPreviousCard() {
        currentCardIndex = (currentCardIndex - 1 + cardDeck.size()) % cardDeck.size();
        currentCardSide = CardSide.FRONT;
    }

    // Override toString method for a meaningful string representation
    @Override
    public String toString() {
        return name + "(" + cardDeck.size() + " Cards)";
    }

    // Makes the deck iterable over its cards
    public Iterator<Card> iterator() {
        return cardDeck.iterator();
    }

    // Main method for testing
    public static void main(final String[] args) {
        // Testing code for the Deck class
    }
}
