package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import core.Card;
import core.Deck;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlashCardController {

	@Autowired
	private FlashCardService service;

	/**
	 * Get all decks currently in the system.
	 * 
	 * @return a {@code ResponseEntity} with an {@code ArrayList} of all the decks
	 *         currently in the system.
	 */
	@GetMapping(path = "/deck")
	public ResponseEntity<ArrayList<Deck>> getAllDecks() {
		return new ResponseEntity<>(service.getAllDecks(), HttpStatus.OK);
	}

	/**
	 * Create a new deck with a given name.
	 * 
	 * @param reqBody a map where the value of the key "name" becomes the name of
	 *                the new deck
	 * @return a {@code ResponseEntity} with the {@code Deck} as its body if the
	 *         deck was successfully added, with {@code null} otherwise
	 */
	@PostMapping(path = "/deck")
	public ResponseEntity<Deck> createDeck(@RequestBody Map<String, String> reqBody) {
		return new ResponseEntity<>(service.addDeck(reqBody.get("name")), HttpStatus.OK);
	}

	/**
	 * Get a deck with a specific name.
	 * 
	 * @param name name of the deck to get
	 * @return a {@code ResponseEntity} with an {@code Optional} containing the deck
	 *         if it was found, an {@code Optional} containing {@code null}
	 *         otherwise
	 */
	@GetMapping(path = "/deck/{name}")
	public ResponseEntity<Optional<Deck>> getDeck(@PathVariable("name") String name) {
		Optional<Deck> deckOpt = service.getDeck(name);
		return new ResponseEntity<Optional<Deck>>(deckOpt, deckOpt.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	/**
	 * Adds a new card to an existing deck.
	 * 
	 * @param deckName name of the deck where the new card will be added
	 * @param card     the card (as a JSON object) to add
	 * @return a {@code ResponseEntity} with {@code true} as its body if the card
	 *         was successfully added, {@code false} otherwise.
	 */
	@PostMapping(path = "/deck/{deckName}/cards")
	public ResponseEntity<Card> addCardToDeck(@PathVariable("deckName") String deckName,
			@RequestBody Map<String, String> card) {
		return new ResponseEntity<>(service.addCardToDeck(deckName, new Card(card.get("front"), card.get("back"))),
				HttpStatus.OK);
	}

	/**
	 * Removes a card from a deck.
	 * 
	 * @param deckName  name of the deck to remove the card from
	 * @param cardFront front of the card to be removed
	 * @return a {@code ResponseEntity} with {@code true} as its body if the card
	 *         was successfully removed, {@code false} otherwise.
	 */
	@DeleteMapping(path = "/deck/{deckName}/cards/{cardFront}")
	public ResponseEntity<Boolean> removeCardFromDeck(@PathVariable("deckName") String deckName,
			@PathVariable("cardFront") String cardFront) {
		boolean success = service.removeCardFromDeck(deckName, cardFront);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	/**
	 * Get all the cards from a deck.
	 * 
	 * @param deckName name of the deck to retrieve the cards from.
	 * @return a {@code ResponseEntity} with a {@code List} containing the cards
	 */
	@GetMapping(path = "/deck/{deckName}/cards")
	public ResponseEntity<List<Card>> getCardsByDeck(@PathVariable("deckName") String deckName) {
		return new ResponseEntity<>(service.getCardsByDeck(deckName), HttpStatus.OK);
	}

	/**
	 * Rename an existing deck.
	 * 
	 * @param deckName    the current name of the deck to rename
	 * @param newDeckName the new name for the existing deck
	 * @return a {@code ResponseEntity} with {@code true} as its body if the deck
	 *         was successfully renamed, {@code false} otherwise.
	 */
	@PostMapping(path = "deck/{deckName}/rename")
	public ResponseEntity<Boolean> renameDeck(@PathVariable("deckName") String deckName,
			@RequestParam("newDeckName") String newDeckName) {
		boolean success = service.renameDeck(deckName, newDeckName);
		return new ResponseEntity<>(success, success ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	/**
	 * Remove an existing deck.
	 * 
	 * @param deckName the name of the deck to remove
	 * @return a {@code ResponseEntity} with {@code true} as its body if the deck
	 *         was successfully removed, {@code false} otherwise.
	 */
	@DeleteMapping(path = "deck/{deckName}")
	public ResponseEntity<Boolean> removeDeck(@PathVariable("deckName") String deckName) {
		boolean success = service.removeDeck(deckName);
		return new ResponseEntity<>(success, success ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
}
