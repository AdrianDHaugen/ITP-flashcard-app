package rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import core.Card;
import core.Context;
// import core.Deck;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest
@ContextConfiguration(classes = { FlashCardController.class, FlashCardService.class, FlashCardApplication.class })
public class FlashCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    FlashCardController flashCardController;

    @Mock
    FlashCardService flashCardService;

    private Context context = Context.getInstance();
    // private String testDeckName = "Sample Test Deck Name";
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TestGetAllDecks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deck"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(context.getDecks().size()));
    }

    
    // @Test
    // void TestRenameDeck() throws Exception {
    //     String deckName = testDeckName;
    //     String newDeckName = "new" + testDeckName;
        
    //     when(flashCardService.renameDeck(deckName, newDeckName)).thenReturn(true);
        
    //     ResponseEntity<Boolean> response = flashCardController.renameDeck(deckName, newDeckName);
        
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(true, response.getBody());
    // }
    
    // @Test
    // void TestCreateDeck() throws Exception {
    //     Map<String, String> requestMap = new HashMap<>();
    //     requestMap.put("name", testDeckName);
        
    //     lenient().when(flashCardService.addDeck(anyString())).thenReturn(new Deck(testDeckName));

    //     mockMvc.perform(MockMvcRequestBuilders.post("/deck")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(requestMap)))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testDeckName));
    // }

    // @Test
    // void TestGetDeck() throws Exception {
    //     context.addDeck(new Deck(testDeckName));
    //     mockMvc.perform(MockMvcRequestBuilders.get("/deck/{name}", testDeckName))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testDeckName));
    // }

    // @Test
    // void TestGetCardsByDeck() throws Exception {
        
    //     lenient().when(flashCardService.getCardsByDeck(anyString())).thenReturn(new ArrayList<Card>());
    //     mockMvc.perform(MockMvcRequestBuilders.get("/deck/{name}/cards", testDeckName))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    // }

    // @Test
    // void TestAddCardsToDeck() throws Exception {
    //     Map<String, String> requestMap = new HashMap<>();
    //     requestMap.put("front", "Front text of sample card.");
    //     requestMap.put("back", "Back text of sample card.");

    //     lenient().when(flashCardService.addCardToDeck(anyString(), any())).thenReturn(new Card(requestMap.get("front"), requestMap.get("back")));

    //     mockMvc.perform(MockMvcRequestBuilders.post("/deck/{name}/cards", testDeckName)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(requestMap)))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.front").value("Front text of sample card."))
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.back").value("Back text of sample card."));
    // }
}
