package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContextTest {

    private Deck testDeck;
    private Deck secondDeck;

    @BeforeEach
    public void setUp() {
        Context.reset();
        testDeck = new Deck("test");
        testDeck.addCard(new Card("front1", "back1"));
        testDeck.addCard(new Card("front2", "back2"));
        secondDeck = new Deck("test");
    }

    @Test
    public void testSetCurrentDeck() {
        Context.getInstance().setCurrentDeck(testDeck);
        assertEquals(Context.getInstance().getCurrentDeck(), testDeck);
    }

    @Test
    public void testAddDeck() {
        Context.getInstance().addDeck(testDeck);
        Context.getInstance().addDeck(secondDeck);
        ArrayList<Deck> getDecks = Context.getInstance().getDecks();
        ArrayList<Deck> testDecks = new ArrayList<>(Arrays.asList(testDeck, secondDeck));
        assertEquals(getDecks, testDecks);
    }

    @Test
    public void testEncapsulation() {
        Context.getInstance().addDeck(testDeck);
        Context.getInstance().addDeck(secondDeck);
        ArrayList<Deck> getDecks = Context.getInstance().getDecks();
        getDecks.remove(testDeck);
        assertEquals(Context.getInstance().getDecks(), getDecks);
    }

}
