# Flashcards API

## Base endpoint

Methods:

- GET - retrieves a list of decks from the server

  - URI: host:port/deck
    http://localhost:8080/deck
  - parameters: none
  - available: jetty, springboot
  - returns a `ResponseEntity` with an `ArrayList` of all the decks currently in the system

- POST - create a new deck with a given name
  - URI: host:port/deck
  - parameters: a `String` of the deck name to create
  - available: jetty, springboot
  - returns a `ResponseEntity` containing `True` if the deck was created and `False` otherwise

## deck

Methods:

- GET - retrieves the specified deck from the server

  - URI: host:port/deck/{name}
  - parameters: a `String` of the name of the deck to retrieve
  - available: jetty, springboot
  - returns a `ResponseEntity` with an `Optional<Deck>`

- DELETE - removes an existing deck with the name {deckName}

  - URI: host:port/deck/{deckName}
  - parameters: a `String` of the name of the deck to remove
  - available: jetty, springboot
  - returns a `ResponseEntity` containing `True` if the deck was removed and `False` otherwise

- POST - Renames an existing deck
  - URI: host:port/deck/{deckName}/rename
  - parameters:
    - A `String` with the name of the deck to change names
    - A `String` with the new name
  - available: jetty, springboot
  - returns a `ResponseEntity` containing `True` if the name was changed and `False` otherwise

## deck/{deckName}/cards

- GET - retrieves the cards from a specified deck

  - URI: hist:port/deck/{deckName}/cards
  - parameters: a `String` with the name of the deck to retrieve the cards from
  - available: jetty, springboot
  - returns a `ResponseEntity` with `List<Card>`

- DELETE - removes a card with the front text {cardFront} from the deck with name {deckName}

  - URI: host:port/deck/{deckName}/cards/{cardFront}
  - parameters:
    - a `String` with the deck´s name
    - a `String` with the front of the card to be deleted
  - available: jetty, springboot
  - returns a `ResponseEntity` containing `True` if the card was removed and `False` otherwise

- POST - Adds a new card to an existing deck

  - URI: host:port/deck/{deckName}/cards
  - parameters:
    - A `String` with the name of the deck to add the card to
    - A `Card` to be added
  - available: jetty, springboot
  - returns a `ResponseEntity` containing `True` if the card was added and `False` otherwise
