package json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import core.Card;
import core.Deck;

public class JsonHandlerTest {

    @Test
    public void testSerializeDeserialize() throws JsonProcessingException {
        ArrayList<Deck> testDecks = new ArrayList<>();
        List<Card> testCards1 = List.of(new Card("first", "b"), new Card("second", "a"));
        testDecks.add(new Deck(testCards1, "firstdeck"));
        String serialized = JsonHandler.serializeDecks(testDecks).toString();
        ArrayList<Deck> deserialized = JsonHandler.deserializeDecks(serialized);
        assertEquals(testDecks.get(0).getCurrentCard().getBack(), deserialized.get(0).getCurrentCard().getBack());
    }

    @Test
    public void testInvalidJson() throws JsonMappingException, JsonProcessingException {
        ArrayList<Deck> empty = new ArrayList<>();
        assertEquals(empty, JsonHandler.deserializeDecks("invalid json"));
    }

}
