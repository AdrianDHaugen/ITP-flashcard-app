package json;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Card;
import core.Deck;

public class JsonHandler {

    public static String serializeDecks(ArrayList<Deck> decks) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(decks);
    }

    public static ArrayList<Deck> deserializeDecks(String json) throws JsonMappingException, JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<ArrayList<Deck>>() {} );
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
        ArrayList<Deck> decks = new ArrayList<>();
        Deck deck1 = new Deck("name");
        deck1.addCard(new Card("front", "back"));
        decks.add(deck1);
        ArrayList<Deck> decks2 = deserializeDecks(serializeDecks(decks));
        System.out.println(decks2.get(0).getName());
    }
}
