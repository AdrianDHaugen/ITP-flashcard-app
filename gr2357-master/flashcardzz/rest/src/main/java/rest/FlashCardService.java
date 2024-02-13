package rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import core.Card;
import core.Context;
import core.Deck;

@Service
public class FlashCardService {

    // Singleton instance of the Context class
    private Context context = Context.getInstance();

    // Constructor that attempts to load the deck upon instantiation
    public FlashCardService() {
        try {
            context.loadDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save the current state of the deck to the context
    private void autoSaveModel() {
        try {
            context.saveDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Adds a new deck to the context and saves the state
    public Deck addDeck(String name) {
        Deck newDeck = new Deck(name);
        context.addDeck(newDeck);
        autoSaveModel();
        return newDeck;
    }

    // Retrieves all decks from the context
    public ArrayList<Deck> getAllDecks() {
        return context.getDecks();
    }

    // Retrieves a deck by name, if present
    public Optional<Deck> getDeck(String name) {
        return Optional.ofNullable(getDeckByName(name));
    }

    // Adds a card to a specific deck and saves the state
    public Card addCardToDeck(String deckName, Card card) {
        Deck deck = getDeckByName(deckName);
        if (deck == null)
            return null;
        deck.addCard(card);
        autoSaveModel();
        return card;
    }

    // Removes a card from a deck based on the card's front text
    public boolean removeCardFromDeck(String deckName, String cardFrontText) {
        Deck deck = getDeckByName(deckName);
        if (deck == null)
            return false;

        Optional<Card> cardOpt = deck.getCardDeck().stream()
                .filter(c -> c.getFront().equals(cardFrontText))
                .findFirst();
        if (cardOpt.isPresent()) {
            deck.removeCard(cardOpt.get());
            autoSaveModel();
            return true;
        }
        return false;
    }

    // Renames a deck and saves the state
    public boolean renameDeck(String deckName, String newDeckName) {
        Deck deck = getDeckByName(deckName);
        if (deck == null)
            return false;

        deck.setName(newDeckName);
        autoSaveModel();
        return true;
    }

    // Removes a deck by name and saves the state
    public boolean removeDeck(String deckName) {
        Deck deck = getDeckByName(deckName);
        if (deck == null)
            return false;

        context.removeDeck(deck);
        autoSaveModel();
        return true;
    }

    // Retrieves cards of a specific deck
    public List<Card> getCardsByDeck(String deckName) {
        Deck deck = getDeckByName(deckName);
        return deck != null ? deck.getCardDeck() : null;
    }

    // Helper method to find a deck by name
    private Deck getDeckByName(String deckName) {
        return context.getDecks().stream()
                .filter(d -> d.getName().equals(deckName))
                .findFirst()
                .orElse(null);
    }

}
