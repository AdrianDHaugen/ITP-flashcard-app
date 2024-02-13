package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck("TestDeck");
    }

    @Test
    public void testConstructorWithName() {
        assertEquals("TestDeck", deck.getName());
        assertEquals(0, deck.getCardDeckSize());
    }

    @Test
    public void testAddCard() {
        Card card = new Card("test", "testDesc");
        deck.addCard(card);

        assertEquals(1, deck.getCardDeckSize());
        assertEquals(card, deck.getCurrentCard());
    }

    @Test
    public void testRemoveCard() {
        Card card = new Card("test", "testDesc");
        deck.addCard(card);
        deck.removeCard(card);

        assertEquals(0, deck.getCardDeckSize());
        // For asserting the current card, you could add another card before removing
        // and then check that card here.
    }

    @Test
    public void testClearDeck() {
        Card card = new Card("test", "testDesc");
        deck.addCard(card);
        deck.clearDeck();

        assertEquals(0, deck.getCardDeckSize());
    }

    @Test
    public void testToString() {
        assertEquals("TestDeck(0 Cards)", deck.toString());
    }

    @Test
    public void testGoToNextCard() {
        Card card1 = new Card("test1", "testDesc1");
        Card card2 = new Card("test2", "testDesc2");

        deck.addCard(card1);
        deck.addCard(card2);

        assertEquals(card1, deck.getCurrentCard());

        deck.goToNextCard();
        assertEquals(card2, deck.getCurrentCard());

        deck.goToNextCard();
        assertEquals(card1, deck.getCurrentCard());
    }

    @Test
    public void testGoToPreviousCard() {
        Card card1 = new Card("test1", "testDesc1");
        Card card2 = new Card("test2", "testDesc2");

        deck.addCard(card1);
        deck.addCard(card2);

        deck.goToNextCard(); // Pointing to card2
        deck.goToPreviousCard();
        assertEquals(card1, deck.getCurrentCard());

        deck.goToPreviousCard();
        assertEquals(card2, deck.getCurrentCard());
    }

    @Test
    public void testFlipCard() {
        assertEquals(Deck.CardSide.FRONT, deck.getCurrentCardSide());

        deck.flipCard();
        assertEquals(Deck.CardSide.BACK, deck.getCurrentCardSide());

        deck.flipCard();
        assertEquals(Deck.CardSide.FRONT, deck.getCurrentCardSide());
    }

    @Test
    public void testToggleCardSide() {
        assertEquals(Deck.CardSide.FRONT, deck.getCurrentCardSide());

        deck.toggleCardSide();
        assertEquals(Deck.CardSide.BACK, deck.getCurrentCardSide());

        deck.toggleCardSide();
        assertEquals(Deck.CardSide.FRONT, deck.getCurrentCardSide());
    }

}
